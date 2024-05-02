package com.company_management.repository;

import com.company_management.model.dto.WageDTO;
import com.company_management.model.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface WageCustomRepository {
    PageResponse<WageDTO> getAllWage(WageDTO wageDTO, Pageable pageable);
}
