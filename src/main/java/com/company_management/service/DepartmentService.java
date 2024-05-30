package com.company_management.service;

import com.company_management.model.dto.DepartmentDTO;
import com.company_management.model.request.SearchDepartmentRequest;
import com.company_management.model.response.StatisticalContractResponse;
import com.company_management.model.response.StatisticalDepartmentResponse;
import com.company_management.model.response.StatisticalHeaderResponse;
import com.company_management.model.response.StatisticalTaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {

    Page<DepartmentDTO> findAllPage(SearchDepartmentRequest searchDepartmentRequest, Pageable pageable);

    void addDepartment(DepartmentDTO departmentDTO);

    void editDepartment(DepartmentDTO departmentDTO);

    void deleteDepartment(Long id);

    DepartmentDTO detailDepartment(Long id);

    List<StatisticalDepartmentResponse> getStatisticalDepartment();
    List<StatisticalTaskResponse> getStatisticalTask();
    List<StatisticalContractResponse> getStatisticalContract();

    StatisticalHeaderResponse getStatisticalHeader(Long employeeId);
}
