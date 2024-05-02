package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.dto.AttendanceDTO;
import com.company_management.model.entity.Attendance;
import com.company_management.model.entity.UserCustom;
import com.company_management.model.request.SearchAttendanceRequest;
import com.company_management.model.response.AttendanceResponse;
import com.company_management.model.response.DataPage;
import com.company_management.repository.AttendanceRepository;
import com.company_management.repository.UserCustomRepository;
import com.company_management.service.AttendanceService;
import com.company_management.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final UserCustomRepository userCustomRepository;

    @Override
    @Transactional(readOnly = true)
    public DataPage<AttendanceResponse> search(SearchAttendanceRequest searchAttendanceRequest, Pageable pageable) {
        return attendanceRepository.search(searchAttendanceRequest, pageable);
    }

    @Override
    public void createOrUpdate(AttendanceDTO attendanceDTO) {
        Attendance attendance;
        if (attendanceDTO.getId() == null) {
            log.debug("// Bắt đầu chấm công");
            attendance = new Attendance();
            attendance.setWorkingDay(attendanceDTO.getWorkingDay());
            attendance.setCheckInTime(attendanceDTO.getCheckInTime());
            UserCustom userCustom = userCustomRepository.findById(CommonUtils.getUserLoginName()).orElseThrow(
                    () -> new AppException("ERR01", "Không tìm thấy tài khoản này")
            );
            attendance.setEmployeeId(userCustom.getUserDetailId());
            attendance.setIsActive(1);
            Calendar nineAM = Calendar.getInstance();
            nineAM.set(Calendar.HOUR_OF_DAY, 8);
            nineAM.set(Calendar.MINUTE, 30);
            nineAM.set(Calendar.SECOND, 0);
            Calendar currentTime = Calendar.getInstance();
            currentTime.setTime(attendanceDTO.getCheckInTime());
            if (currentTime.after(nineAM)) {
                long diffInMillis = currentTime.getTimeInMillis() - nineAM.getTimeInMillis();
                long diffInMinutes = diffInMillis / (60 * 1000);
                attendance.setTotalPenalty(diffInMinutes);
            } else {
                attendance.setTotalPenalty(0L);
            }
        } else {
            attendance = attendanceRepository.findById(attendanceDTO.getId())
                    .orElseThrow(() -> new AppException("ERO01", "Mã chấm công không tồn tại"));
            log.debug("// Đăng ký thời gian ra");
            UserCustom userCustom = userCustomRepository.findById(CommonUtils.getUserLoginName()).orElseThrow(
                    () -> new AppException("ERR01", "Không tìm thấy tài khoản này")
            );
            attendance.setCheckOutTime(attendanceDTO.getCheckOutTime());
            Date checkOutTime = attendanceDTO.getCheckOutTime();
            Date checkInTime = attendance.getCheckInTime();
            LocalDateTime checkOutLocalDateTime = LocalDateTime.ofInstant(checkOutTime.toInstant(), ZoneId.systemDefault());
            LocalDateTime checkInLocalDateTime = LocalDateTime.ofInstant(checkInTime.toInstant(), ZoneId.systemDefault());

            Duration duration = Duration.between(checkInLocalDateTime, checkOutLocalDateTime);
            double hoursDifference = duration.toHours(); // Số giờ trả về dưới dạng double
            log.info("Difference in hours: " + hoursDifference);
            attendance.setWorkingPoint(hoursDifference);
            if (hoursDifference >= 7) {
                attendance.setWorkingPoint(1.0);
                attendance.setWorkingTime(8.0);
            } else if (hoursDifference >= 2.5) {
                attendance.setWorkingPoint(0.5);
                attendance.setWorkingTime(3.0);
            } else {
                attendance.setWorkingPoint(0.0);
                attendance.setWorkingTime(0.0);
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
}
