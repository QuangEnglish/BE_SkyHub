package com.company_management.model.dto;

import com.company_management.utils.annotation.StrongPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCustomDTO {

    private Long id;
    @NotBlank(message = "Tên không hợp lệ! (Không được để trống)")
    @NotNull(message = "Tên không hợp lệ! Tên là NULL")
    @Size(min = 3, max = 30, message = "Tên chỉ được phép khai báo từ 3 đến 30 ký tự")
    private String userName;
    @StrongPassword
    private String password;
    private String confirmPassword;
    private String email;
    private Long userDetailId;
    private Long companyId;
    private String companyName;
    private Set<RoleDTO> roles;
}
