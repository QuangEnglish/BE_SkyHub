package com.company_management.controller.quanlychamcong;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.AttendanceDTO;
import com.company_management.model.request.SearchAttendanceRequest;
import com.company_management.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendance")
@Slf4j
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    @PostMapping("/search")
    public ResultResp<Object> searchAttendance(@RequestBody SearchAttendanceRequest searchAttendanceRequest,
                                             Pageable pageable) {
        return ResultResp.success(attendanceService.search(searchAttendanceRequest, pageable));
    }

    @PostMapping("/create")
    public ResultResp<Object> createAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        attendanceService.createOrUpdate(attendanceDTO);
        return ResultResp.success(ErrorCode.CREATED_OK, null);
    }

    @PostMapping("/update")
    public ResultResp<Object> updateAttendance(@RequestBody  AttendanceDTO attendanceDTO) {
        attendanceService.createOrUpdate(attendanceDTO);
        return ResultResp.success(ErrorCode.UPDATED_OK, null);
    }

    @PostMapping("/detailAttendanceId")
    public ResultResp<Object> getDetailAttendanceId(@RequestBody  AttendanceDTO attendanceDTO){
        return ResultResp.success(attendanceService.detailAttendanceId(attendanceDTO));
    }

}
