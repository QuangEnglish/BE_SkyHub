package com.company_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ATTENDANCE") // bảng chấm công
public class Attendance extends EntBase{

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;   //mã nhân viên
    @Column(name = "WORKING_DAY")
    private Date workingDay; //ngày làm
    @Column(name = "CHECK_IN_TIME")
    private Date checkInTime; //đăng ký giờ vào
    @Column(name = "CHECK_OUT_TIME")
    private Date checkOutTime; //đăng ky giờ ra
    @Column(name = "WORKING_TIME")
    private Double workingTime;  //sô giờ
    @Column(name = "WORKING_POINT")
    private Double workingPoint;  //hệ số công
    @Column(name = "TOTAL_PENALTY")
    private Long totalPenalty;  //tổng phút phạt

}
