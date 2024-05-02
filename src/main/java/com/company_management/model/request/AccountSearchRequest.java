package com.company_management.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountSearchRequest {
    private String fullName;
    private String email;
    private Integer status;
    private Integer active;
}
