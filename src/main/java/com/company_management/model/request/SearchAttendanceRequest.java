package com.company_management.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAttendanceRequest {

    private String employeeCode;
    private Long employeeId;
    private String employeeName;
    private Long departmentId;
    private Date workingDay;
}
