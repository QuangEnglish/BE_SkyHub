package com.company_management.model.mapper;

import com.company_management.model.dto.DepartmentDTO;
import com.company_management.model.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface DepartmentMapper extends EntityMapper<Department, DepartmentDTO> {
}
