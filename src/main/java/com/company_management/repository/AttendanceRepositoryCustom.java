package com.company_management.repository;

import com.company_management.model.request.SearchAttendanceRequest;
import com.company_management.model.response.AttendanceResponse;
import com.company_management.model.response.DataPage;
import org.springframework.data.domain.Pageable;

public interface AttendanceRepositoryCustom {
    DataPage<AttendanceResponse> search(SearchAttendanceRequest searchAttendanceRequest, Pageable pageable);

}
