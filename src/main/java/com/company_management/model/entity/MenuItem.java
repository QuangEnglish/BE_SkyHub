package com.company_management.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MENU_ITEM")
public class MenuItem extends EntBase{

    @Column(name = "MENU_ITEM_CODE")
    private String menuItemCode;
    @Column(name = "MENU_ITEM_NAME")
    private String menuItemName;
    @Column(name = "STATUS")
    private Integer status;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(name = "MENU_ITEM_PERMISSION", joinColumns = @JoinColumn(name = "MENU_ITEM_ID"), inverseJoinColumns =
    @JoinColumn(name = "PERMISSION_ID"))
    private Set<Permission> permissions;
}
