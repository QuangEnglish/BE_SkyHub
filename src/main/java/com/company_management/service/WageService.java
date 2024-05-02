package com.company_management.service;

import com.company_management.model.dto.WageDTO;
import com.company_management.model.response.BasicResponse;
import com.company_management.model.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.io.ByteArrayInputStream;

public interface WageService {
    PageResponse<WageDTO> getAllWage(WageDTO wageDTO, Pageable pageable);
    BasicResponse addNewWage (WageDTO wageDTO);
    ByteArrayInputStream exportExcel(WageDTO wageDTO) throws Exception;
    void deleteWage (Long id);
}
