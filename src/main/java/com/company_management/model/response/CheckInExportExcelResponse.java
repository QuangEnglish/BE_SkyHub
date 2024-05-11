package com.company_management.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CheckInExportExcelResponse {

    private String checkIn1;
    private String checkIn2;
    private String checkIn3;
    private String checkIn4;
    private String checkIn5;
    private String checkIn6;
    private String checkIn7;
    private String checkIn8;
    private String checkIn9;
    private String checkIn10;
    private String checkIn11;
    private String checkIn12;
    private String checkIn13;
    private String checkIn14;
    private String checkIn15;
    private String checkIn16;
    private String checkIn17;
    private String checkIn18;
    private String checkIn19;
    private String checkIn20;
    private String checkIn21;
    private String checkIn22;
    private String checkIn23;

}
