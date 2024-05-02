package com.company_management.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class QualificationDTO {

    private Long id;
    @NotEmpty(message = "Không được để trống trường loại trình độ bằng cấp")
    private String level;  //loại, trình độ bằng cấp (, cử nhân, thạc sĩ, tiến sĩ, )
    @NotEmpty(message = "Không được để trống tên bằng cấp")
    private String name; //tên bằng cấp
    @NotEmpty(message = "Không được để trống tên chuyên ngành")
    private String major; // chuyên ngành
    private String description;  //mô tả
    private Date licenseDate; //ngày cấp bằng
    private Long userDetailId;  //ma nhan vien
    private Integer index;

}
