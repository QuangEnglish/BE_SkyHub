package com.company_management.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequest {
    private Long id;
    private String fullName;
    private Integer gender;
    private String birthday;
    private Long provinceId;
    private Long departmentId;
    private String contract_createdate;
}
