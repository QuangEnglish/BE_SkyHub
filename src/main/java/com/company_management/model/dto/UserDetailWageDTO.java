package com.company_management.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserDetailWageDTO {
    private Long id;
    @NotNull(message = "Không được để trống mã hợp đồng")
    private Long wageId;
    @NotNull(message = "Không được để trống mã nhân viên")
    private Long userDetailId;
    @NotNull(message = "Không được để trống ngày bắt đầu hiệu lực")
    private Date licenseDate; // ngay hieu luc
    @NotNull(message = "Không được để trống người ký kết")
    private String empSign; //nguoi ky quyet dinh
}
