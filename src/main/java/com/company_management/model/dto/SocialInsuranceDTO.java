package com.company_management.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
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
public class SocialInsuranceDTO {
    private Long socialInsuranceId;
    @NotEmpty(message = "Không được để trống mã số bảo hiểm")
    private String socialInsuranceCode; // ma so BHXH
    @NotNull(message = "Không được để trống mức đóng ban đầu")
    private Double initialPayment;  //mức dong ban dau
    @NotNull(message = "Không được để trống mức giảm bao nhiêu %")
    private Double percent;
    private Double actualPayment;  // mức dong thuc te
    private String placement; // noi kham chua benh
    private Date licenseDate; // ngay hieu luc
    private Date expiredDate; // ngay het han
    private Long userDetailId;
}
