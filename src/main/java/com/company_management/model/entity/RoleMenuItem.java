package com.company_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ROLE_MENU_ITEM")  //vai trò phân quyền
public class RoleMenuItem extends EntBase{

    @Column(name = "ROLE_ID")
    private Long roleId;
    @Column(name = "MENU_ITEM_ID")
    private Long menuItemId;

}
