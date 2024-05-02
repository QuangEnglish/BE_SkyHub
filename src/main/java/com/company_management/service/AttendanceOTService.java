package com.company_management.service;

import com.company_management.model.dto.AttendanceOTDTO;
import com.company_management.model.request.SearchAttendanceOTRequest;
import com.company_management.model.response.DataPage;
import org.springframework.data.domain.Pageable;

import java.io.ByteArrayInputStream;

public interface AttendanceOTService {

    DataPage<AttendanceOTDTO> search(SearchAttendanceOTRequest searchAttendanceOTRequest, Pageable pageable);

    AttendanceOTDTO detailOT(Long id);

    void createOrUpdate(AttendanceOTDTO attendanceOTDTO);


    void deleteOT(Long id);

    ByteArrayInputStream exportExcel(SearchAttendanceOTRequest searchAttendanceOTRequest, Pageable pageable);


}
