package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.dto.AttendanceDTO;
import com.company_management.model.entity.Attendance;
import com.company_management.model.entity.UserCustom;
import com.company_management.model.request.SearchAttendanceRequest;
import com.company_management.model.response.AttendanceExportExcelResponse;
import com.company_management.model.response.AttendanceResponse;
import com.company_management.model.response.DataPage;
import com.company_management.repository.AttendanceRepository;
import com.company_management.repository.UserCustomRepository;
import com.company_management.repository.UserDetailRepository;
import com.company_management.service.AttendanceService;
import com.company_management.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final UserCustomRepository userCustomRepository;

    private final UserDetailRepository userDetailRepository;

    @Override
    @Transactional(readOnly = true)
    public DataPage<AttendanceResponse> search(SearchAttendanceRequest searchAttendanceRequest, Pageable pageable) {
        return attendanceRepository.search(searchAttendanceRequest, pageable);
    }

    @Override
    @Transactional
    public void createOrUpdate(AttendanceDTO attendanceDTO) {
        Attendance attendance;
        if (attendanceDTO.getId() == null) {
            log.debug("// Bắt đầu chấm công");
            attendance = new Attendance();
            attendance.setWorkingDay(attendanceDTO.getWorkingDay());
            attendance.setCheckInTime(toNearestWholeHour(attendanceDTO.getCheckInTime()));
            UserCustom userCustom = userCustomRepository.findById(CommonUtils.getUserLoginName()).orElseThrow(
                    () -> new AppException("ERR01", "Không tìm thấy tài khoản này")
            );
            attendance.setEmployeeId(userCustom.getUserDetailId());
            attendance.setIsActive(1);
            Calendar eightAM = Calendar.getInstance();
            eightAM.set(Calendar.HOUR_OF_DAY, 8);
            eightAM.set(Calendar.MINUTE, 0);
            eightAM.set(Calendar.SECOND, 0);
            Calendar currentTime = Calendar.getInstance();
            currentTime.setTime(attendanceDTO.getCheckInTime());
            if (currentTime.after(eightAM)) {
                long diffInMillis = currentTime.getTimeInMillis() - eightAM.getTimeInMillis();
                long diffInMinutes = diffInMillis / (60 * 1000);
                attendance.setTotalPenalty(diffInMinutes);
            } else {
                attendance.setTotalPenalty(0L);
            }
            if(userDetailRepository.updateIsActiveById(userCustom.getUserDetailId(), CommonUtils.getUserLoginName()) <= 0){
                throw new AppException("ERR01", "Không tìm thấy nhân viên này!");
            }
        } else {
            attendance = attendanceRepository.findById(attendanceDTO.getId())
                    .orElseThrow(() -> new AppException("ERO01", "Mã chấm công không tồn tại"));
            log.debug("// Đăng ký thời gian ra");
            UserCustom userCustom = userCustomRepository.findById(CommonUtils.getUserLoginName()).orElseThrow(
                    () -> new AppException("ERR01", "Không tìm thấy tài khoản này")
            );

            Date checkOutTime = attendanceDTO.getCheckOutTime();
            Date checkInTime = attendance.getCheckInTime();
            LocalDateTime checkOutLocalDateTime = LocalDateTime.ofInstant(checkOutTime.toInstant(), ZoneId.systemDefault());
            LocalDateTime checkInLocalDateTime = LocalDateTime.ofInstant(checkInTime.toInstant(), ZoneId.systemDefault());

            Duration duration = Duration.between(checkInLocalDateTime, checkOutLocalDateTime);
            double hoursDifference = duration.toHours(); // Số giờ trả về dưới dạng double
            log.info("Difference in hours: " + hoursDifference);
            attendance.setWorkingPoint(hoursDifference);
            if (hoursDifference >= 6) {
                attendance.setWorkingPoint(1.0);
                attendance.setWorkingTime(8.0);
            }else {
                attendance.setWorkingPoint(0.5);
                attendance.setWorkingTime(4.0);
            }
            Calendar sixPM = Calendar.getInstance();
            sixPM.set(Calendar.HOUR_OF_DAY, 18);
            sixPM.set(Calendar.MINUTE, 0);
            sixPM.set(Calendar.SECOND, 0);
            Calendar currentTime = Calendar.getInstance();
            currentTime.setTime(attendanceDTO.getCheckOutTime());
            if (currentTime.before(sixPM)) {
                long diffInMillis = sixPM.getTimeInMillis() - currentTime.getTimeInMillis();
                long diffInMinutes = diffInMillis / (60 * 1000);
                attendance.setTotalPenalty(attendance.getTotalPenalty() + diffInMinutes);
            }
            attendance.setCheckOutTime(toNearestWholeHour(attendanceDTO.getCheckOutTime()));
        }
        attendanceRepository.save(attendance);
    }

    @Override
    public ByteArrayInputStream exportExcel(SearchAttendanceRequest searchAttendanceRequest, Pageable pageable) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Long detailAttendanceId(AttendanceDTO attendanceDTO) {
        UserCustom userCustom = userCustomRepository.findById(CommonUtils.getUserLoginName()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy tài khoản này")
        );
        return attendanceRepository.findIdAllWithEmployeeId(userCustom.getUserDetailId(), attendanceDTO.getWorkingDay());
    }

    @Override
    public ByteArrayInputStream exportListFollowMonth(SearchAttendanceRequest searchAttendanceRequest) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Date date = searchAttendanceRequest.getWorkingDay();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        if(daysInMonth==31){
            try (InputStream in = CommonUtils.getInputStreamByFileName("export-attendance-month-template.xlsx")) {
                List<AttendanceExportExcelResponse> attendanceExportExcelResponses = attendanceRepository.searchExport(searchAttendanceRequest);
                AtomicInteger index = new AtomicInteger();
                for (AttendanceExportExcelResponse item : attendanceExportExcelResponses) {
                    item.setIndex(index.incrementAndGet());
                }
                Map<String, Object> beans = new HashMap<>();
                beans.put("posLst", attendanceExportExcelResponses);
                beans.put("dateMonth", year);
                beans.put("dateYear", month);
                beans.put("total", attendanceExportExcelResponses.size());
                XLSTransformer transformer = new XLSTransformer();
                Workbook workbook = transformer.transformXLS(in, beans);
                workbook.write(byteArrayOutputStream);
                byte[] exportInputStream = byteArrayOutputStream.toByteArray();
                return new ByteArrayInputStream(exportInputStream);
            }  catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                throw new AppException("ERR01", "Xuất file excel bị lỗi");
            }
        }
        return new ByteArrayInputStream(null);
    }
    static Date toNearestWholeMinute(Date d) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);

        if (c.get(Calendar.SECOND) >= 30)
            c.add(Calendar.MINUTE, 1);

        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    static Date toNearestWholeHour(Date d) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        if (c.get(Calendar.MINUTE) >= 45){
            c.add(Calendar.HOUR, 1);
            c.set(Calendar.MINUTE, 0);
        }else if(c.get(Calendar.MINUTE) >= 30){
            c.set(Calendar.MINUTE, 30);
        }else if(c.get(Calendar.MINUTE) >= 15){
            c.set(Calendar.MINUTE, 30);
        }else{
            c.set(Calendar.MINUTE, 0);
        }
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

}
