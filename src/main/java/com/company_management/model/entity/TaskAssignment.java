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
@Table(name = "TASK_ASSIGNMENT")  //bảng phân công công việc
public class TaskAssignment extends  EntBase{

    @Basic
    @Column(name = "TASK_ID")
    private Long taskId;  //mã công việc
    @Basic
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;  //mã nhân viên
    @Basic
    @Column(name = "START_DATE")
    private Date startDate;  // ngay khoi tao

}
