package com.company_management.service;

import com.company_management.model.dto.UserDetailWageDTO;
import com.company_management.model.dto.WageDTO;
import com.company_management.model.response.DataPage;
import com.company_management.model.response.WageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface WageService {
    DataPage<WageDTO> searchForEmployee(WageDTO wageDTO, Pageable pageable);
    DataPage<WageDTO> search(WageDTO wageDTO, Pageable pageable);

    WageResponse detail(Long id);

    void update(MultipartFile file, WageDTO wageDTO);
    void updateForEmployee(UserDetailWageDTO userDetailWageDTO);

    void add(MultipartFile file, WageDTO contractDTO);
    void addForEmployee(UserDetailWageDTO userDetailWageDTO);

    void deleteByIds(Long id);
    void deleteForEmployeeByIds(Long id);

}
