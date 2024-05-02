package com.company_management.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERMISSION")
public class Permission extends EntBase{

    @Column(name = "PERMISSION_CODE")
    private String permissionCode;
    @Column(name = "PERMISSION_NAME")
    private String permissionName;
    @Column(name = "STATUS")
    private Integer status;
}
