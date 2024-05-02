package com.company_management.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ContractResponse {

    private Long contractId;
    private String contractCode; // ma hop dong
    private Integer contractType; // loai hop dong: thu viec, chinh thuc,....
    private String attachfile;
    private Date signDate; // ngay ky HD
    private Date activeDate; // ngay hieu luc
    private Date expiredDate; // thoi han hop dong
    private List<Long> userDetailId;
    private Integer isActive; // la hop dong hien tai

}
