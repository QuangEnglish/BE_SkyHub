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
@Table(name = "WAGE") //Lương phụ cấp
public class Wage extends EntBase{

    @Column(name = "WAGE_NAME")
    private String wageName;
    @Column(name = "WAGE_BASE")
    private Double wageBase; //luong phu cap co ban( số tien)
    @Column(name = "WAGE_DESCRIPTION")
    private String wageDescription;
    @Column(name = "ATTACHFILE")
    private String attachFile;  //file thong tin phu cap

}
