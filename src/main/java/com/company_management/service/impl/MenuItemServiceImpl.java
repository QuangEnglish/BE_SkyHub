package com.company_management.service.impl;

import com.company_management.common.Constants;
import com.company_management.exception.BadRequestException;
import com.company_management.model.dto.MenuItemDTO;
import com.company_management.model.entity.MenuItem;
import com.company_management.model.entity.Permission;
import com.company_management.model.mapper.MenuItemMapper;
import com.company_management.model.response.BasicResponse;
import com.company_management.repository.MenuItemRepository;
import com.company_management.repository.PermissionRepository;
import com.company_management.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final PermissionRepository permissionRepository;
    private final MenuItemMapper menuItemMapper;

    @Override
    public BasicResponse addMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem();
        menuItem.setMenuItemCode(menuItemDTO.getMenuItemCode());
        menuItem.setMenuItemName(menuItemDTO.getMenuItemName());
        menuItem.setStatus(menuItemDTO.getStatus());
        menuItemRepository.save(menuItem);
        return new BasicResponse(200, "Thêm mới thành công");
    }

    @Override
    public BasicResponse updateMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = menuItemRepository.findById(menuItemDTO.getId()).orElseThrow(() -> new BadRequestException("Không tìm thấy Menu theo id: " + menuItemDTO.getId()));
        return new BasicResponse(200, "Cập nhật thành công");
    }

    @Override
    public List<MenuItemDTO> getAll() {
        return menuItemMapper.toDto(menuItemRepository.findAll());
    }
}
