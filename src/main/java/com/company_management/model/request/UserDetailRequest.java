package com.company_management.model.request;

import com.company_management.model.dto.ContractDTO;
import com.company_management.model.dto.SocialInsuranceDTO;
import com.company_management.model.dto.WageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailRequest {
    private Long userId;
    private String fullName;
    private Integer gender;
    private String birthday;
    private String address;
    private Long departmentId;
    private Long positionId;
    private ContractDTO contract;
    private SocialInsuranceDTO socialInsurance;
    private WageDTO wage;
}
