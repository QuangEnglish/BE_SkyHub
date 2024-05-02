package com.company_management.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchEmployeeRequest {

    private String employeeCode;
    private String employeeName;
    private String employeeEmail;
    private Integer employeeGender;
    private Integer positionId;
    private Integer departmentId;

}
