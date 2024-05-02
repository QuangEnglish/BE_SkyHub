package com.company_management.model.mapper;

import com.company_management.model.dto.MenuItemDTO;
import com.company_management.model.entity.MenuItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface MenuItemMapper extends EntityMapper<MenuItem, MenuItemDTO> {
}
