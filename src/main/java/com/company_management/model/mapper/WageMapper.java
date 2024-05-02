package com.company_management.model.mapper;

import com.company_management.model.dto.WageDTO;
import com.company_management.model.entity.Wage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface WageMapper extends EntityMapper<Wage, WageDTO> {
}
