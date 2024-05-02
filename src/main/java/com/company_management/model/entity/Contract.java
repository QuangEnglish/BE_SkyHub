package com.company_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTRACT")  //Hợp đồng
public class Contract extends EntBase{

    @Column(name = "CONTRACT_CODE")
    private String contractCode; // ma hop dong
    @Column(name = "CONTRACT_TYPE")
    private String contractType; // loai hop dong: thu viec, chinh thuc,....
    @Column(name = "ATTACHFILE")
    private String attachFile;  //file hop dong
}
