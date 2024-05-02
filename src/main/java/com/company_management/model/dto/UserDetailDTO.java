package com.company_management.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {

    private Long id;
    @NotBlank(message = "Mã nhân viên không hợp lệ! (Không được để trống)")
    private String employeeCode;
    @NotBlank(message = "Tên nhân viên không hợp lệ! (Không được để trống)")
    private String employeeName;
    private Integer gender;
    private String genderName;
    private Date birthday;
    private String avatar;
    private String phone;
    private String email;
    private String address;
    private Integer isActive;
    private Long departmentId;
    private String departmentName;
    private Long positionId;
    private String positionName;
    private List<ContractDTO> contracts;
    private List<SocialInsuranceDTO> socialInsurances;
    private List<WageDTO> wages;
}
