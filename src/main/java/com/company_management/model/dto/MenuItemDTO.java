package com.company_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
    private Long id;
    private String menuItemCode;
    private String menuItemName;
    private Integer status;
    private Set<PermissionDTO> permissions;
}
