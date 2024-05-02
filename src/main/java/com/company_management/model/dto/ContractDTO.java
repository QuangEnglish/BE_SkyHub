package com.company_management.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractDTO {
    private Long contractId;
    @NotBlank(message = "Mã hợp đồng không hợp lệ! (Không được để trống)")
    private String contractCode; // ma hop dong
    private Integer isActive; // la hop dong hien tai
    @NotBlank(message = "Loại hợp đồng không hợp lệ! (Không được để trống)")
    private String contractType; // loai hop dong: thu viec, chinh thuc,....
    private String attachFile;
    private Date signDate; // ngay ky HD
    private Date activeDate; // ngay hieu luc
    private Date expiredDate; // thoi han hop dong
    private Long userDetailId;
    private String userDetailName;
    private Long userDetailContractId;

}
