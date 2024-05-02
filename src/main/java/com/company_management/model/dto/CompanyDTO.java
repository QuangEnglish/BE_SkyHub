package com.company_management.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Long companyId;
    private String companyViName;
    private String companyEnName;
    private String legalType;
    private String taxCode;
    private String address;
    private String foundingDate;
    private String representative;
    private String website;
    private String phone;
    private String logo_url;
}
