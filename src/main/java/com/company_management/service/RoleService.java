package com.company_management.service;

import com.company_management.model.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRole();

    RoleDTO getRoleByRoleName(String roleName);
}
