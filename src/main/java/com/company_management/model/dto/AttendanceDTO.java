package com.company_management.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class AttendanceDTO {

    private Long id;
    private Long employeeId;
    private Date workingDay;
    private Date checkInTime;
    private Date checkOutTime;
    private Double workingTime;
    private Double workingPoint;
    private Long totalPenalty;
    private Integer isActive;
}
