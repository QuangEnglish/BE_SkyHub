package com.company_management.model.response;

import com.company_management.model.dto.CompanyDTO;
import com.company_management.model.dto.UserDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchResponse {
    private Long id;
    private String fullName;
    private String email;
    private Integer gender;
    private String birthday;
    private String birthPlace;
    private String address;
    private String departmentName;
    private String createdAt;
}
