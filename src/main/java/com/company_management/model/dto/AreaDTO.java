package com.company_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaDTO {
    private Long id;
    private String areaCode;
    private String areaName;
    private Long parentId;
    private Long chilldId;
    private String acronyns;
}
