package com.company_management.service.impl;

import com.company_management.exception.BadRequestException;
import com.company_management.model.dto.RoleDTO;
import com.company_management.model.entity.Role;
import com.company_management.model.mapper.RoleMapper;
import com.company_management.repository.RoleRepository;
import com.company_management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDTO> getAllRole() {
        List<Role> roleList = roleRepository.findAll();
        return roleMapper.toDto(roleList);
    }

    @Override
    public RoleDTO getRoleByRoleName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        if(role.isEmpty()){
            throw new BadRequestException("Không tồn tại role: " + roleName);
        }
        return roleMapper.toDto(role.get());
    }
}
