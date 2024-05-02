package com.company_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "USER_DETAIL_CONTRACT")  //Hợp đồng với nhân viên
public class UserDetailContract extends EntBase{

    @Column(name = "USER_DETAIL_ID")
    private Long userDetailId;
    @Column(name = "CONTRACT_ID")
    private Long contractId;
    @Column(name = "SIGN_DATE")
    private Date signDate; // ngay ky HD
    @Column(name = "ACTIVE_DATE")
    private Date activeDate; // ngay hieu luc
    @Column(name = "EXPIRED_DATE")
    private Date expiredDate; // thoi han hop dong

}
