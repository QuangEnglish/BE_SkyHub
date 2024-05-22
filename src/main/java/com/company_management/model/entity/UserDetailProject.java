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
@Table(name = "USER_DETAIL_PROJECT")  //Dự án với nhân viên
public class UserDetailProject extends EntBase{

    @Column(name = "USER_DETAIL_ID")
    private Long userDetailId;
    @Column(name = "PROJECT_ID")
    private Long projectId;

}
