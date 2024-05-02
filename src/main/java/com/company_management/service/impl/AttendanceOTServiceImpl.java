package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.dto.AttendanceOTDTO;
import com.company_management.model.entity.AttendanceOt;
import com.company_management.model.mapper.AttendanceOTMapper;
import com.company_management.model.request.SearchAttendanceOTRequest;
import com.company_management.model.response.DataPage;
import com.company_management.repository.AttendanceOTRepository;
import com.company_management.service.AttendanceOTService;
import com.company_management.utils.CommonUtils;
import com.company_management.utils.DataUtils;
import com.company_management.utils.DateTimeUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceOTServiceImpl implements AttendanceOTService {

    private final AttendanceOTRepository attendanceOTRepository;

    private final AttendanceOTMapper attendanceOTMapper;
    @Override
    public DataPage<AttendanceOTDTO> search(SearchAttendanceOTRequest searchOTRequest, Pageable pageable) {
        return attendanceOTRepository.search(searchOTRequest, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public AttendanceOTDTO detailOT(Long id) {
        AttendanceOt attendanceOT = attendanceOTRepository.findById(id).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy đơn nghỉ phép này!"));
        return attendanceOTMapper.toDto(attendanceOT);
    }

    @Override
    @Transactional
    public void createOrUpdate(AttendanceOTDTO attendanceOTDTO) {
        AttendanceOt attendanceOT;
        if (attendanceOTDTO.getAttendanceOtID() == null) {
            log.debug("// Them moi đơn ot");
            attendanceOT = new AttendanceOt();
            attendanceOT = attendanceOTMapper.toEntity(attendanceOTDTO);
            attendanceOT.setIsActive(2);
        } else {
            attendanceOT = attendanceOTRepository.findById(attendanceOTDTO.getAttendanceOtID())
                    .orElseThrow(() -> new AppException("ERO01", "Đơn đăng ký lịch ot không tồn tại"));
            log.debug("// Cap nhat đơn ot");
            if(!DataUtils.isNullOrEmpty(attendanceOTDTO.getStartDay())){
                attendanceOT.setStartDay(attendanceOTDTO.getStartDay());
            }
            if(!DataUtils.isNullOrEmpty(attendanceOTDTO.getStartTime())){
                attendanceOT.setStartTime(attendanceOTDTO.getStartTime());
            }
            if(!DataUtils.isNullOrEmpty(attendanceOTDTO.getEndTime())){
                attendanceOT.setEndTime(attendanceOTDTO.getEndTime());
            }
            if(!DataUtils.isNullOrEmpty(attendanceOTDTO.getDescriptionOt())){
                attendanceOT.setDescriptionOt(attendanceOTDTO.getDescriptionOt());
            }
            if(!DataUtils.isNullOrEmpty(attendanceOTDTO.getTotalTime())){
                attendanceOT.setTotalTime(attendanceOTDTO.getTotalTime());
            }
            if(!DataUtils.isNullOrEmpty(attendanceOTDTO.getFollowId())){
                attendanceOT.setFollowId(attendanceOTDTO.getFollowId());
            }
            if(!DataUtils.isNullOrEmpty(attendanceOTDTO.getIsActive())){
                attendanceOT.setIsActive(attendanceOTDTO.getIsActive());
            }
        }
        attendanceOTRepository.save(attendanceOT);
    }

    @Override
    @Transactional
    public void deleteOT(Long id) {
        log.debug("// Xóa đơn nghỉ phép: {}", id);
        if (attendanceOTRepository.deleteById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Không tìm thấy đơn nghỉ phép này!");
        }
    }

    @Override
    public ByteArrayInputStream exportExcel(SearchAttendanceOTRequest searchOTRequest, Pageable pageable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream in = CommonUtils.getInputStreamByFileName("export-leave-template.xlsx")) {
            List<AttendanceOTDTO> attendanceOTDTOList = attendanceOTRepository.searchExport(searchOTRequest, pageable);
            AtomicInteger index = new AtomicInteger();
            for (AttendanceOTDTO item : attendanceOTDTOList) {
                item.setIndex(index.incrementAndGet());
            }
            Map<String, Object> beans = new HashMap<>();
            beans.put("posLst", attendanceOTDTOList);
            beans.put("date", DateTimeUtils.convertDateToStringByPattern(new Date(), "dd/MM/yyyy HH:mm:ss"));
            beans.put("total", attendanceOTDTOList.size());
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
