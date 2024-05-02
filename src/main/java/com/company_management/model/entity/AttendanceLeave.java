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
@Table(name = "ATTENDANCE_LEAVE") // bảng xin nghỉ phép
public class AttendanceLeave extends EntBase {

    @Column(name = "LEAVE_CATEGORY")
    private String leaveCategory; // loại nghỉ phép nghỉ tính phép, nghỉ không tính phép)
    @Column(name = "START_DAY")
    private Date startDay; // tu ngày
    @Column(name = "END_DAY")
    private Date endDay;  //đến ngày
    @Column(name = "TOTAL_TIME")
    private Long totalTime;  //số giờ
    @Column(name = "DESCRIPTION")
    private String description; //Nội dung
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;
    @Column(name = "REVIEWER_ID")
    private Long reviewerId;  //người phe duyệt
    @Column(name = "TRACKER_ID")
    private Long trackerId; //người theo dõi

}
