package com.company_management.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountSearchResponse {
    private Long id;
    private String fullName;
    private String email;
    private Integer status;
    private Integer active;
    private String roles;
    private Date createdDate;
    private Date updatedDate;
}
