package com.company_management.service;

import com.company_management.model.dto.SocialInsuranceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SocialInsuranceService {
    Page<SocialInsuranceDTO> search(Long userDetailId, Pageable pageable);

    SocialInsuranceDTO detail(Long id);

    void update(SocialInsuranceDTO socialInsuranceDTO);

    void add(SocialInsuranceDTO socialInsuranceDTO);

    void deleteByIds(Long id);

}
