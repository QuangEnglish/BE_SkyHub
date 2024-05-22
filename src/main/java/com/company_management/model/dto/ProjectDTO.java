package com.company_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;
    private String projectCode;  //ma du an
    private String projectName;  //ten du an
    private String projectDescription; // mo ta du an
    private Long projectManagerId;
    private Date startDay;  // ngay bat dau
    private Date endDay;  // han ket thuc
    private String customerName;
    private String customerAvatar;
    private Double etimate;  // so gio du kien
    private Double timesheet;  // so gio thuc hien dang đc khai bao
    private Long taskNumber;  // so luong task
    private List<String> employees;   //danh sách nhân viên tham gia
}
