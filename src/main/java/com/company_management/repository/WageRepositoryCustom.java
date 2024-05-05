package com.company_management.repository;

import com.company_management.model.dto.WageDTO;
import com.company_management.model.response.DataPage;
import org.springframework.data.domain.Pageable;

public interface WageRepositoryCustom {
    DataPage<WageDTO> search(WageDTO wageDTO, Pageable pageable);

}
