package com.company_management.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAttendanceOTRequest {

    private Date startDay;
    private Long employeeId;
    private Integer isActive;

}
