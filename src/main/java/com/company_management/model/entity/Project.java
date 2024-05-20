package com.company_management.model.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROJECT")  // bang du an cong ty
public class Project extends EntBase{

    @Basic
    @Column(name = "PROJECT_CODE")
    private String projectCode;  //ma du an
    @Basic
    @Column(name = "PROJECT_NAME")
    private String projectName;  //ten du an
    @Basic
    @Column(name = "PROJECT_DESCRIPTION")
    private String projectDescription; // mo ta du an
    @Basic
    @Column(name = "PROJECT_MANAGER_ID")
    private Long projectManagerId;  // nguoi quan ly du an
    @Basic
    @Column(name = "START_DAY")
    private Date startDay;  // ngay bat dau
    @Basic
    @Column(name = "END_DAY")
    private Date endDay;  // han ket thuc
    @Basic
    @Column(name = "CUSTOMER_NAME")
    private String customerName;  //ten khach hang
    @Basic
    @Column(name = "CUSTOMER_AVATAR")
    private String customerAvatar;  //anh dai dien du an
    @Basic
    @Column(name = "ETIMATE")
    private Double etimate;  // so gio du kien
    @Basic
    @Column(name = "TIMESHEET")
    private Double timesheet;  // so gio thuc hien dang đc khai bao
    @Basic
    @Column(name = "TASK_NUMBER")
    private Long taskNumber;  // so luong task

}
