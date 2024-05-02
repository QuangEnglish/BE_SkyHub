package com.company_management.repository;

import com.company_management.model.dto.AttendanceOTDTO;
import com.company_management.model.request.SearchAttendanceOTRequest;
import com.company_management.model.response.DataPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttendanceOTRepositoryCustom {
    DataPage<AttendanceOTDTO> search(SearchAttendanceOTRequest searchOTRequest, Pageable pageable);

    List<AttendanceOTDTO> searchExport(SearchAttendanceOTRequest searchOTRequest, Pageable pageable);

}
