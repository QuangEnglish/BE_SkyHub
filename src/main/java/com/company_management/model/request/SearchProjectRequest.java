package com.company_management.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchProjectRequest {

    private Long employeeId;
    private Long projectManagerId;

}
