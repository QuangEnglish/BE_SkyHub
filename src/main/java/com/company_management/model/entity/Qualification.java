package com.company_management.model.entity;

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
@Table(name = "QUALIFICATION") // Bằng cấp chứng chỉ
public class Qualification extends EntBase{

    @Column(name = "LEVEL")
    private String level;  //loại, trình độ bằng cấp (, cử nhân, thạc sĩ, tiến sĩ, )
    @Column(name = "NAME")
    private String name; //tên bằng cấp
    @Column(name = "MAJOR")
    private String major; // chuyên ngành
    @Column(name = "DESCRIPTION")
    private String description;  //mô tả
    @Column(name = "LICENSE_DATE")
    private Date licenseDate; //ngày cấp bằng
    @Column(name = "USER_DETAIL_ID")
    private Long userDetailId;  //ma nhan vien
}
