package com.company_management.service;

import com.company_management.model.dto.DepartmentDTO;
import com.company_management.model.request.SearchDepartmentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    Page<DepartmentDTO> findAllPage(SearchDepartmentRequest searchDepartmentRequest, Pageable pageable);

    void addDepartment(DepartmentDTO departmentDTO);

    void editDepartment(DepartmentDTO departmentDTO);

    void deleteDepartment(Long id);

    DepartmentDTO detailDepartment(Long id);
}
