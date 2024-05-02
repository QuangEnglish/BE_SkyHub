package com.company_management.model.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CustomerDTO {
    Long customerId;
    String customerCode;
    String name;
    String sapCode;
    String signAcc;
    String province;
    String district;
    String precinct;
    String areaCode;
    String street;
    String address;
    String tel;
    String email;
    String bankCode;
    String branchBankCode;
    String description;
    String status;
    Date createDate;
    String createUser;
    Date upDateDate;
    String upDateUser;
    String representativeName;
    String representativeIdNo;
    String representativeIssuePlace;
    String representativeTel;
    String representativeEmail;
    Date representativeExpireDate;
    String partnerType;
    String fax;
//    Long unitId;
}
