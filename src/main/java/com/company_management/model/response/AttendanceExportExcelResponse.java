package com.company_management.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class AttendanceExportExcelResponse {

    private int index;
    private Long id;
    private Long employeeId;
    private String employeeCode;
    private String employeeName;
    private Date workingDay;
    private Double workingTime;
    private Double workingPoint;
    private Long totalPenalty;
    private Double totalTimeLeave;
    private Double totalTimeOt;
    private Integer isActive;
    private List<CheckInExportExcelResponse> checkInExportExcelResponse;
    private List<CheckOutExportExcelResponse> checkOutExportExcelResponse;
}
