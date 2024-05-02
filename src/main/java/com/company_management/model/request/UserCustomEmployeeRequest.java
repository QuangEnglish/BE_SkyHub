package com.company_management.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCustomEmployeeRequest {

    @NotNull(message = "Mã tài khoản không được để trống!")
    private Long id;
    @NotNull(message = "Mã nhân viên không được để trống!")
    private Long userDetailId;
}
