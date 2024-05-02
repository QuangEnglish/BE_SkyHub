package com.company_management.service;

import com.company_management.model.dto.MenuItemDTO;
import com.company_management.model.response.BasicResponse;

import java.util.List;

public interface MenuItemService {
    BasicResponse addMenuItem(MenuItemDTO menuItemDTO);
    BasicResponse updateMenuItem(MenuItemDTO menuItemDTO);

    List<MenuItemDTO> getAll();
}
