package com.company_management.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER_DETAIL")
public class UserDetail extends  EntBase{

        @Column(name = "EMPLOYEE_CODE")
        private String employeeCode;
        @Column(name = "EMPLOYEE_NAME")
        private String employeeName;
        @Column(name = "GENDER")
        private Integer gender;
        @Column(name = "BIRTHDAY")
        private Date birthday;
        @Column(name = "ADDRESS")
        private String address;
        @Column(name = "AVATAR")
        private String avatar;
        @Column(name = "PHONE")
        private String phone;
        @Column(name = "DEPARTMENT_ID")
        private Long departmentId;
        @Column(name = "POSITION_ID")
        private Long positionId;
}
