package com.company_management.repository;

import com.company_management.model.dto.ContractDTO;
import com.company_management.model.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ContractCustomRepository {
    PageResponse<ContractDTO> getAllContract(ContractDTO contractDTO, Pageable pageable);
}
