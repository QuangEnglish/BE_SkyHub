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
@Table(name = "POSITION")
public class Position extends  EntBase{

    @Column(name = "POSITION_CODE")
    private String positionCode;
    @Column(name = "POSITION_NAME")
    private String positionName;
    @Column(name = "POSITION_DESCRIPTION")
    private String positionDescription;
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

}
