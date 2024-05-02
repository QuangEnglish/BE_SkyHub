package com.company_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceLeaveDTO {

    private int index;
    private Long leaveID;
    private String leaveCategory; // loại nghỉ phép nghỉ tính phép, nghỉ không tính phép)
    private Long employeeId;
    private String employeeCode;
    private String employeeName;
    private Date startDay; // tu ngày
    private Date endDay;  //đến ngày
    private String description; //Nội dung
    private Long totalTime;  //số giờ
    private Long trackerId; //người theo dõi
    private String trackerCode;
    private String trackerName;
    private Long reviewerId;  //người phe duyệt
    private String reviewerCode;
    private String reviewerName;
    private Integer isActive;  // trạng thái đã duyệt, chờ duyệt, từ chối
}
