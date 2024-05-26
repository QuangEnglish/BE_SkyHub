package com.company_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetDTO {

    private Long id;
    private Long taskId;
    private Date dayTimeSheet;
    private double durationTimeSheet;
    private String timeSheetDescription;
}
