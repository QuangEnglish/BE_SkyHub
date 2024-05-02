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
public class UserDetailContractDTO {
    private Long id;
    @NotNull(message = "Không được để trống mã hợp đồng")
    private Long contractId;
    @NotNull(message = "Không được để trống mã nhân viên")
    private Long userDetailId;
    @NotNull(message = "Không được để trống ngày bắt đầu hiệu lực")
    private Date activeDate; // ngay băt đầu hiệu lực
    @NotNull(message = "Không được để trống ngày hệt hạn")
    private Date expiredDate; // ngay het han
    @NotNull(message = "Không được để trống ngày ký kết")
    private Date signDate; // ngay ký ket
}
