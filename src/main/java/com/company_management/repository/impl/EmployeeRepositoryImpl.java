package com.company_management.repository.impl;

import com.company_management.model.dto.UserDetailDTO;
import com.company_management.repository.EmployeeRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @Override
    public void insertBatch(List<UserDetailDTO> employeeExcelLst, String staffCode) {

    }
}
