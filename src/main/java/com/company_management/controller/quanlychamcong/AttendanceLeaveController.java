package com.company_management.controller.quanlychamcong;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.AttendanceLeaveDTO;
import com.company_management.model.request.SearchLeaveRequest;
import com.company_management.service.AttendanceLeaveService;
import com.company_management.utils.CommonUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/leave")
public class AttendanceLeaveController {

    private final AttendanceLeaveService attendanceLeaveService;

    @PostMapping("/search")
    public ResultResp<Object> searchLeave(@RequestBody SearchLeaveRequest searchLeaveRequest,
                                             Pageable pageable) {
        return ResultResp.success(attendanceLeaveService.search(searchLeaveRequest, pageable));
    }

    @PostMapping
    public ResultResp<Object> createLeave(@Valid @RequestBody AttendanceLeaveDTO leaveDTO) {
        attendanceLeaveService.createOrUpdate(leaveDTO);
        return ResultResp.success(ErrorCode.CREATED_OK, null);
    }

    @GetMapping("/detail/{id}")
    public ResultResp<Object> getByIdLeave(@PathVariable("id") Long id) {
        return ResultResp.success(ErrorCode.CREATED_OK, attendanceLeaveService.detailLeave(id));
    }

    @PutMapping
    public ResultResp<Object> updateLeave(@Valid @RequestBody AttendanceLeaveDTO leaveDTO) {
        attendanceLeaveService.createOrUpdate(leaveDTO);
        return ResultResp.success(ErrorCode.UPDATED_OK, null);
    }

    @DeleteMapping("/delete/{id}")
    public ResultResp<Object> deleteLeave(@PathVariable("id") Long id) {
        attendanceLeaveService.deleteLeave(id);
        return ResultResp.success(ErrorCode.DELETED_OK, null);
    }

    @PostMapping(value = "/export")
    public ResponseEntity<Object> exportExcel(@RequestBody SearchLeaveRequest searchLeaveRequest, Pageable pageable) {
        ByteArrayInputStream result = attendanceLeaveService.exportExcel(searchLeaveRequest, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = CommonUtils.getFileNameReportUpdate("EXPORT_POSITION");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<>(new InputStreamResource(result), headers, HttpStatus.OK);
    }

}
