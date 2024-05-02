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
@Table(name = "USER_DETAIL_WAGE")  //Lương phụ cấp với nhân viên
public class UserDetailWage extends EntBase{

    @Column(name = "USER_DETAIL_ID")
    private Long userDetailId;
    @Column(name = "WAGE_ID")
    private Long contractId;  //mã phụ cấp

}
