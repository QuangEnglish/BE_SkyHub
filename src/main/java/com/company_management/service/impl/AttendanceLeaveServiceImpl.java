package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.dto.AttendanceLeaveDTO;
import com.company_management.model.entity.AttendanceLeave;
import com.company_management.model.entity.UserCustom;
import com.company_management.model.entity.UserDetail;
import com.company_management.model.mapper.AttendanceLeaveMapper;
import com.company_management.model.request.MailRequest;
import com.company_management.model.request.SearchLeaveRequest;
import com.company_management.model.response.DataPage;
import com.company_management.repository.AttendanceLeaveRepository;
import com.company_management.repository.UserCustomRepository;
import com.company_management.repository.UserDetailRepository;
import com.company_management.service.AttendanceLeaveService;
import com.company_management.service.EmailService;
import com.company_management.utils.CommonUtils;
import com.company_management.utils.DataUtils;
import com.company_management.utils.DateTimeUtils;
import com.company_management.utils.LogisticsMailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceLeaveServiceImpl implements AttendanceLeaveService {

    private final AttendanceLeaveRepository attendanceLeaveRepository;

    private final UserCustomRepository userCustomRepository;

    private final AttendanceLeaveMapper attendanceLeaveMapper;

    private final UserDetailRepository userDetailRepository;

    private final EmailService emailService;

    private final ITemplateEngine templateEngine;

    @Override
    public DataPage<AttendanceLeaveDTO> search(SearchLeaveRequest searchLeaveRequest, Pageable pageable) {
        return attendanceLeaveRepository.search(searchLeaveRequest, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public AttendanceLeaveDTO detailLeave(Long id) {
        AttendanceLeave attendanceLeave = attendanceLeaveRepository.findById(id).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy đơn nghỉ phép này!"));
        return attendanceLeaveMapper.toDto(attendanceLeave);
    }

    @Override
    @Transactional
    public void createOrUpdate(AttendanceLeaveDTO attendanceLeaveDTO) {
        AttendanceLeave attendanceLeave;
        if (attendanceLeaveDTO.getLeaveID() == null) {
            log.debug("// Them moi đơn nghỉ phép");
            attendanceLeave = new AttendanceLeave();
            attendanceLeave = attendanceLeaveMapper.toEntity(attendanceLeaveDTO);
            attendanceLeave.setIsActive(2);
        } else {
            attendanceLeave = attendanceLeaveRepository.findById(attendanceLeaveDTO.getLeaveID())
                    .orElseThrow(() -> new AppException("ERO01", "Đơn nghỉ phép không tồn tại"));
            log.debug("// Cap nhat đơn nghỉ phép");
            if(!DataUtils.isNullOrEmpty(attendanceLeaveDTO.getLeaveCategory())){
                attendanceLeave.setLeaveCategory(attendanceLeaveDTO.getLeaveCategory());
            }
            if(!DataUtils.isNullOrEmpty(attendanceLeaveDTO.getTrackerId())){
                attendanceLeave.setTrackerId(attendanceLeaveDTO.getTrackerId());
            }
            if(!DataUtils.isNullOrEmpty(attendanceLeaveDTO.getReviewerId())){
                attendanceLeave.setReviewerId(attendanceLeaveDTO.getReviewerId());
            }
            if(!DataUtils.isNullOrEmpty(attendanceLeaveDTO.getDescription())){
                attendanceLeave.setDescription(attendanceLeaveDTO.getDescription());
            }
            if(!DataUtils.isNullOrEmpty(attendanceLeaveDTO.getStartDay())){
                attendanceLeave.setStartDay(attendanceLeaveDTO.getStartDay());
            }
            if(!DataUtils.isNullOrEmpty(attendanceLeaveDTO.getEndDay())){
                attendanceLeave.setEndDay(attendanceLeaveDTO.getEndDay());
            }
            if(!DataUtils.isNullOrEmpty(attendanceLeaveDTO.getIsActive())){
                attendanceLeave.setIsActive(attendanceLeaveDTO.getIsActive());
            }
        }
        attendanceLeaveRepository.save(attendanceLeave);
        UserCustom userCustom = userCustomRepository.findByUserDetailId(attendanceLeaveDTO.getReviewerId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy tài khoản người phê duyệt!")
        );
        AttendanceLeaveDTO dto = attendanceLeaveMapper.toDto(attendanceLeave);
        UserDetail userDetail = userDetailRepository.findById(attendanceLeave.getReviewerId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy tài khoản người phê duyệt!")
        );
        UserDetail userDetail2 = userDetailRepository.findById(attendanceLeave.getTrackerId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy tài khoản người theo dõi!")
        );
        UserDetail userDetail3 = userDetailRepository.findById(attendanceLeave.getEmployeeId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy tài khoản người gửi đơn!")
        );
        dto.setReviewerName(userDetail.getEmployeeName());
        dto.setTrackerName(userDetail2.getEmployeeName());
        dto.setEmployeeName(userDetail3.getEmployeeName());
        dto.setStartDayConvert(DateTimeUtils.convertDateTimeToString(dto.getStartDay(), "dd/MM/yyyy"));
        dto.setEndDayConvert(DateTimeUtils.convertDateTimeToString(dto.getEndDay(), "dd/MM/yyyy"));
        Map<String, Object> params = LogisticsMailUtils.sendMailToAttendanceLeave(dto);
        Context context = new Context();
        context.setVariables(params);
        MailRequest mailRequest = MailRequest.builder()
                .toMail(userCustom.getEmail())
                .html(true)
                .title("Công ty cổ phần truyền thông và dịch vụ Nodo")
                .content(templateEngine.process(MailRequest.ATTENDANCE_LEAVE_PROVIDER_TEMPLATE, context))
                .build();
        emailService.send(mailRequest);
    }

    @Override
    @Transactional
    public void deleteLeave(Long id) {
        log.debug("// Xóa đơn nghỉ phép: {}", id);
        if (attendanceLeaveRepository.deleteById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Không tìm thấy đơn nghỉ phép này!");
        }
    }

    @Override
    public ByteArrayInputStream exportExcel(SearchLeaveRequest searchLeaveRequest, Pageable pageable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream in = CommonUtils.getInputStreamByFileName("export-leave-template.xlsx")) {
            List<AttendanceLeaveDTO> attendanceLeaveDTOList = attendanceLeaveRepository.searchExport(searchLeaveRequest, pageable);
            AtomicInteger index = new AtomicInteger();
            for (AttendanceLeaveDTO item : attendanceLeaveDTOList) {
                item.setIndex(index.incrementAndGet());
                if (item.getIsActive() == 1) {
                    item.setIsActiveName("Đã duyệt");
                } else if(item.getIsActive() == 2) {
                    item.setIsActiveName("Chờ duyệt");
                }else{
                    item.setIsActiveName("Từ chối");
                }
                item.setStartDayConvert(DateTimeUtils.convertDateTimeToString(item.getStartDay(), "dd/MM/yyyy"));
                item.setEndDayConvert(DateTimeUtils.convertDateTimeToString(item.getEndDay(), "dd/MM/yyyy"));
            }

            Map<String, Object> beans = new HashMap<>();
            beans.put("posLst", attendanceLeaveDTOList);
            beans.put("date", DateTimeUtils.convertDateToStringByPattern(new Date(), "dd/MM/yyyy HH:mm:ss"));
            beans.put("total", attendanceLeaveDTOList.size());
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
}
