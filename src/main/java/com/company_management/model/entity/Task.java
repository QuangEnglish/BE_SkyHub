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
@Table(name = "TASK")  // bang cong viec
public class Task extends  EntBase{

    @Basic
    @Column(name = "TASK_NAME")
    private String taskName;  // ten cong viec
    @Basic
    @Column(name = "TASK_CODE")
    private String taskCode;  // ma cong viec
    @Basic
    @Column(name = "TASK_DESCRIPTION")
    private String taskDescription; // mô ta cong viec
    @Basic
    @Column(name = "TASK_STATUS")
    private int taskStatus; // trang thai cong viec
    @Basic
    @Column(name = "START_DAY")
    private Date startDay; // ngay bat dau
    @Basic
    @Column(name = "END_DAY")
    private Date endDay;  // han ket thuc
    @Basic
    @Column(name = "PROJECT_ID")
    private Long projectId; // ma du an
    @Basic
    @Column(name = "FOLLOW_ID")
    private Long followId;  // nguoi theo doi
    @Basic
    @Column(name = "PRIORITY")
    private int priority;  //do uu tien
    @Basic
    @Column(name = "DURATION")
    private double duration;  // khoảng thoi gian làm
    @Basic
    @Column(name = "COMMUNICATION")
    private String communication; // trao đổi thông tin

}
