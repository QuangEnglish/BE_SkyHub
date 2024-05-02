package com.company_management.model.mapper;

import com.company_management.model.entity.Role;
import com.company_management.model.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<Role, RoleDTO>{
}
