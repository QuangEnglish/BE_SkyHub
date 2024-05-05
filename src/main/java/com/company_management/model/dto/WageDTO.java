package com.company_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WageDTO {

    private int index;
    private Long wageId;
    private Long userDetailId;
    private String wageName;
    private Double wageBase; //so tiền phu cap
    private String wageDescription;
    private Integer isActive;
    private String attachFile;  //file thong tin phu cap
    private Long empSign;
    private Long userDetailWageId;
    private Date licenseDate;
    private Date createdDate;
}
