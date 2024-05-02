package com.company_management.model.mapper;

import com.company_management.model.dto.PositionDTO;
import com.company_management.model.entity.Position;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PositionMapper extends EntityMapper<Position, PositionDTO> {
}
