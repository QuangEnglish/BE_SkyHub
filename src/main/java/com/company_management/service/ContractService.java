package com.company_management.service;

import com.company_management.model.dto.ContractDTO;
import com.company_management.model.dto.UserDetailContractDTO;
import com.company_management.model.response.ContractResponse;
import com.company_management.model.response.DataPage;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ContractService {
    DataPage<ContractDTO> searchForEmployee(ContractDTO contractDTO, Pageable pageable);
    DataPage<ContractDTO> search(ContractDTO contractDTO, Pageable pageable);

    ContractResponse detail(Long id);

    void update(MultipartFile file, ContractDTO contractDTO);
    void updateForEmployee(UserDetailContractDTO userDetailContractDTO);

    void add(MultipartFile file, ContractDTO contractDTO);
    void addForEmployee(UserDetailContractDTO userDetailContractDTO);

    void deleteByIds(Long id);
    void deleteForEmployeeByIds(Long id);

}
