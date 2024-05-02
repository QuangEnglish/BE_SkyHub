package com.company_management.model.response;

import com.company_management.model.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String username;
    private String token;
    private Set<RoleDTO> roles;
}
