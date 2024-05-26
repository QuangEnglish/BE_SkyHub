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
@Table(name = "TIME_SHEET")  // bang khai bao time sheet
public class TimeSheet extends  EntBase{

    @Basic
    @Column(name = "TASK_ID")
    private Long taskId;
    @Basic
    @Column(name = "DAYTIMESHEET")
    private Date dayTimeSheet;
    @Basic
    @Column(name = "DURATIONTIMESHEET")
    private double durationTimeSheet;
    @Basic
    @Column(name = "TIMESHEETDESCRIPTION")
    private String timeSheetDescription;

}
