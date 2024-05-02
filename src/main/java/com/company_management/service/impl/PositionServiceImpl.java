package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.dto.PositionDTO;
import com.company_management.model.entity.Position;
import com.company_management.model.mapper.PositionMapper;
import com.company_management.model.request.SearchPositionRequest;
import com.company_management.model.response.DataPage;
import com.company_management.repository.PositionRepository;
import com.company_management.service.PositionService;
import com.company_management.utils.CommonUtils;
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
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    private final PositionMapper positionMapper;


    @Override
    @Transactional(readOnly = true)
    public DataPage<PositionDTO> search(SearchPositionRequest searchPositionRequest, Pageable pageable) {
        return positionRepository.search(searchPositionRequest, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public PositionDTO detailPosition(Long id) {
        Position position = positionRepository.findById(id).orElseThrow(() -> new AppException("ERR01", "Không tìm thấy chức vụ này!"));
        return positionMapper.toDto(position);
    }

    @Override
    @Transactional
    public void createOrUpdate(PositionDTO positionDTO) {
        Position position;
        if (positionDTO.getId() == null) {
            log.debug("// Them moi chức vụ");
            position = new Position();
            position = positionMapper.toEntity(positionDTO);
        } else {
            position = positionRepository.findById(positionDTO.getId())
                    .orElseThrow(() -> new AppException("ERO01", "Chức vụ không tồn tại"));
            log.debug("// Cap nhat chức vụ");
            position.setPositionName(positionDTO.getPositionName());
            position.setPositionDescription(positionDTO.getPositionDescription());
            position.setDepartmentId(positionDTO.getDepartmentId());
            position.setIsActive(positionDTO.getIsActive());
        }
        positionRepository.save(position);
    }

    @Transactional
    public void deletePosition(Long id) {
        log.debug("// Xóa chức vụ: {}", id);
        if (positionRepository.deleteById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Không tìm thấy chức vụ!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ByteArrayInputStream exportExcel(SearchPositionRequest searchPositionRequest, Pageable pageable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream in = CommonUtils.getInputStreamByFileName("export-position-template.xlsx")) {
            List<PositionDTO> positionDTOList = positionRepository.searchExport(searchPositionRequest, pageable);
            AtomicInteger index = new AtomicInteger();
            for (PositionDTO item : positionDTOList) {
                item.setIndex(index.incrementAndGet());
            }
            Map<String, Object> beans = new HashMap<>();
            beans.put("posLst", positionDTOList);
            beans.put("date", DateTimeUtils.convertDateToStringByPattern(new Date(), "dd/MM/yyyy HH:mm:ss"));
            beans.put("total", positionDTOList.size());
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
