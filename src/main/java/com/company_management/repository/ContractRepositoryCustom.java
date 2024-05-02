package com.company_management.repository;

import com.company_management.model.dto.ContractDTO;
import com.company_management.model.response.DataPage;
import org.springframework.data.domain.Pageable;

public interface ContractRepositoryCustom {
    DataPage<ContractDTO> search(ContractDTO contractDTO, Pageable pageable);

}
