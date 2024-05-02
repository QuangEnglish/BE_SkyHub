package com.company_management.model.mapper;

import com.company_management.model.dto.UserDetailDTO;
import com.company_management.model.entity.UserDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface EmployeeMapper extends EntityMapper<UserDetail, UserDetailDTO> {
}
