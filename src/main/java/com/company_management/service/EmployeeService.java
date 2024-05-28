package com.company_management.service;

import com.company_management.model.dto.UserDetailDTO;
import com.company_management.model.entity.UserDetail;
import com.company_management.model.request.SearchEmployeeRequest;
import com.company_management.model.response.DataPage;
import com.company_management.model.response.ExportPdfEmployeeResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    DataPage<UserDetailDTO> search(SearchEmployeeRequest searchEmployeeRequest, Pageable pageable);

    UserDetailDTO detailEmployee(Long id);

    void createEmployee(MultipartFile avatarFile, UserDetailDTO userDetailDTO) throws IOException;

    void updateEmployee(MultipartFile avatarFile, UserDetailDTO userDetailDTO) throws IOException;

    void deleteEmployee(Long id);

    ByteArrayInputStream exportExcel(SearchEmployeeRequest searchEmployeeRequest, Pageable pageable);

    void updateEmployeeStatus();

    ExportPdfEmployeeResponse exportPdf(Long userDetailId);

    List<UserDetail> getTodayBirthdayEmployees();

    List<UserDetail> getEmployeesWithBirthdaysInCurrentMonth();

}
