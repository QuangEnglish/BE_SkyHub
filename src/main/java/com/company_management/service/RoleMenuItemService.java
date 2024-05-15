package com.company_management.service;

import java.util.List;

public interface RoleMenuItemService {

    void createOrUpdate(Long roleId, List<Long> menuItemIds);
}
