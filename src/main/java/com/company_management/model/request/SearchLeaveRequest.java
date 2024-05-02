package com.company_management.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchLeaveRequest {

    private Date startDay;
    private Date endDay;
    private Integer isActive;

}
