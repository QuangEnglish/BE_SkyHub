package com.company_management.model.mapper;

import com.company_management.model.dto.UserCustomDTO;
import com.company_management.model.entity.UserCustom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserCustomMapper extends EntityMapper<UserCustom, UserCustomDTO> {
}
