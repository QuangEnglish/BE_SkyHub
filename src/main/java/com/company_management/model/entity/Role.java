package com.company_management.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ROLE")
public class Role extends EntBase{

    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "STATUS")
    private Integer status;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(name = "ROLE_MENU_ITEM", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns =
    @JoinColumn(name = "MENU_ITEM_ID"))
    private Set<MenuItem> menuItems = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "roles")
    private Set<UserCustom> users = new HashSet<>();
}