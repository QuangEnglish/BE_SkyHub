package com.company_management.repository;

import com.company_management.model.request.SearchAttendanceRequest;
import com.company_management.model.response.AttendanceExportExcelResponse;
import com.company_management.model.response.AttendanceResponse;
import com.company_management.model.response.DataPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttendanceRepositoryCustom {
    DataPage<AttendanceResponse> search(SearchAttendanceRequest searchAttendanceRequest, Pageable pageable);

    List<AttendanceExportExcelResponse> searchExport(SearchAttendanceRequest searchAttendanceRequest);
}
