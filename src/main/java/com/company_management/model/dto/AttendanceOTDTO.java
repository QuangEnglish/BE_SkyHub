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
public class AttendanceOTDTO {

    private int index;
    private Long id;
    private Date startDay;  // ngày chấm công ot
    private Date startTime; //thời gian bắt đầu
    private Date endTime; //thời gian kết thúc
    private Double totalTime; //tổng số giờ
    private Long employeeId;
    private String employeeCode;
    private String employeeName;
    private Long followId;  // người theo dõi
    private String followCode;
    private String followName;
    private String descriptionOt;  //mô tả công việc cần ot
    private Integer isActive;  // trạng thái đã duyệt, chờ duyệt, từ chối
}
