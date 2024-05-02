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
    private Long wageId;
    private String wageName;
    private Double wageBase; //so tiền phu cap
    private String wageDescription;
    private Integer isActive;
    private Date licenseDate; // ngay hieu luc
    private String empSign; //nguoi ky quyet dinh
    private Long userDetailId;
    private String userDetailName;
    private int stt;
}
