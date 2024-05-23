package com.company_management.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TaskDTO {

    private Long id;
    @NotBlank(message = "Tên công việc không hợp lệ! (Không được để trống)")
    private String taskName;  // ten cong viec
    @NotBlank(message = "Mã công việc không hợp lệ! (Không được để trống)")
    private String taskCode;  // ma cong viec
    private String taskDescription; // mô ta cong viec
    @NotNull(message = "Không được để trống trạng thái công việc")
    private Integer taskStatus; // trang thai cong viec
    @NotNull(message = "Không được để trống ngày bắt đầu")
    private Date startDay; // ngay bat dau
    @NotNull(message = "Không được để trống hạn kết thúc")
    private Date endDay;  // han ket thuc
    @NotNull(message = "Không được để trống mã dự án")
    private Long projectId; // ma du an
    @NotNull(message = "Không được để trống người theo dõi")
    private Long followId;  // nguoi theo doi
    @NotNull(message = "Không được để trống độ ưu tiên")
    private Integer priority;  //do uu tien
    private List<String> employees;   //danh sách nhân viên tham gia
}
