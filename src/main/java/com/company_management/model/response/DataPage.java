package com.company_management.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class DataPage<T> implements Serializable {
    private int pageIndex;
    private int pageSize;
    private long pageCount;
    private long dataCount;
    private List<T> data;

    public static DataPage buildDataPage(List data, PageRequest paging, long dataCount) {
        DataPage dataPage = new DataPage();
        dataPage.setData(data);
        dataPage.setPageIndex(paging.getPageNumber());
        dataPage.setPageSize(paging.getPageSize());
        dataPage.setDataCount(dataCount);
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        return dataPage;
    }
}
