package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.entity.MenuItem;
import com.company_management.model.entity.RoleMenuItem;
import com.company_management.repository.MenuItemRepository;
import com.company_management.repository.RoleMenuItemRepository;
import com.company_management.service.RoleMenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleMenuItemServiceImpl implements RoleMenuItemService {

    private final RoleMenuItemRepository roleMenuItemRepository;

    private final MenuItemRepository menuItemRepository;

    @Override
    @Transactional
    public void createOrUpdate(Long roleId, List<Long> menuItemIds) {

        roleMenuItemRepository.deleteByRoleId(roleId);

        for (Long menuItemId : menuItemIds) {
            MenuItem menuItem = menuItemRepository.findById(menuItemId)
                    .orElseThrow(() -> new AppException("ERR01", "Mã menu không tồn tại!"));

            RoleMenuItem roleMenuItem = new RoleMenuItem();
            roleMenuItem.setRoleId(roleId);
            roleMenuItem.setMenuItemId(menuItem.getId());

            roleMenuItemRepository.save(roleMenuItem);
        }
    }
}
