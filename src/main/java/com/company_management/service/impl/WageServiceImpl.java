package com.company_management.service.impl;

import com.company_management.common.Constants;
import com.company_management.common.DataUtil;
import com.company_management.exception.AppException;
import com.company_management.model.dto.WageDTO;
import com.company_management.model.entity.Wage;
import com.company_management.model.response.BasicResponse;
import com.company_management.model.response.PageResponse;
import com.company_management.repository.WageRepository;
import com.company_management.service.WageService;
import lombok.RequiredArgsConstructor;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WageServiceImpl implements WageService {

    public final WageRepository wageRepository;
    @Override
    public PageResponse getAllWage(WageDTO wageDTO, Pageable pageable) {
        try {
            PageResponse<WageDTO> response = wageRepository.getAllWage(wageDTO, pageable);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BasicResponse addNewWage(WageDTO wageDTO) {
        Wage wage = new Wage();
        List<Wage> wageList = wageRepository.findAllByUserDetailId(wageDTO.getUserDetailId());
        if (!DataUtil.isNullOrEmpty(wageList)){
            for (Wage w : wageList) {
                w.setIsActive(Constants.STATUS_INACTIVE_INT);
            }
        }
        wage.setId(wageDTO.getWageId());
        wage.setWageBase(wageDTO.getWageBase());
        wage.setIsActive(Constants.STATUS_ACTIVE_INT);
        wage.setLicenseDate(wageDTO.getLicenseDate());
        wageRepository.save(wage);
        return new BasicResponse(200, "Thêm mới thành công");
    }

    @Override
    public ByteArrayInputStream exportExcel(WageDTO wageDTO) throws Exception {
        String pattern = "MM/YYYY";
        DateFormat dtf = new SimpleDateFormat(pattern);

        PageResponse<WageDTO> wageDTOPageResponse = getAllWage(wageDTO, PageRequest.of(0, Integer.MAX_VALUE));
        List<WageDTO> data = wageDTOPageResponse.getDataList();
        HashMap beans = new HashMap<>();
        beans.put("lsData", data);
        beans.put("date", dtf.format(new Date()));
        return generateExcelData(data, "wage_template_excel.xlsx", beans);
    }
    public <T> ByteArrayInputStream generateExcelData(List<T> data, String tempFileName, Map beans) throws IOException {
        InputStream is = getInputStreamByFileName(tempFileName);
        try {
            XLSTransformer transformer = new XLSTransformer();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Workbook hssfWorkbook = WorkbookFactory.create(is);
            transformer.transformWorkbook(hssfWorkbook, beans);
            hssfWorkbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new IOException("Error generating Excel data", e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public InputStream getInputStreamByFileName(String fileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream("template/" + fileName);
        if (is == null) {
            throw new RuntimeException("Template file not found: " + fileName);
        }
        return is;
    }
    @Override
    public void deleteWage(Long id) {
        Wage w = wageRepository.findById(id).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy" + id)
        );
        if (w.getIsActive() == 1) {
            w.setIsActive(0);
        }
        wageRepository.save(w);

    }
}
