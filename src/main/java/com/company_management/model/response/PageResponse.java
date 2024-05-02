package com.company_management.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> dataList;
    private Long totalElements;
    private Integer totalPages;
    private Integer pageSize;
    private Integer pageIndex;
    private Double totalValue;
}
