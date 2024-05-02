package com.company_management.service;

import com.company_management.model.dto.QualificationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QualificationService {
    Page<QualificationDTO> search(Long userDetailId, Pageable pageable);

    QualificationDTO detail(Long id);

    void update(QualificationDTO qualificationDTO);

    void addQualification(QualificationDTO qualificationDTO);

    void deleteByIds(Long id);

}
