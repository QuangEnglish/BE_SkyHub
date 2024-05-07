package com.company_management.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ExportPdfEmployeeResponse {

    private Long attendanceId;
    private Long employeeId;
    private String employeeCode;
    private String employeeName;
    private String departmentName;
    private Date workingDay;
    private Date checkInTime;
    private Date checkOutTime;
    private Double workingTime;
    private Double workingPoint;
    private Long totalPenalty;
    private Integer isActive;

}
