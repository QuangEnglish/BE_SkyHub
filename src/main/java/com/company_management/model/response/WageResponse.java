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
public class WageResponse {

    private Long wageId;
    private String wageName;
    private Double wageBase; //so tiền phu cap
    private String wageDescription;
    private Date licenseDate; // ngay hieu luc
    private String empSign; //nguoi ky quyet dinh
    private String userDetailName;
    private String attachFile;  //file thong tin phu cap
    private List<Long> userDetailId;
    private Integer isActive; // la hop dong hien tai

}
