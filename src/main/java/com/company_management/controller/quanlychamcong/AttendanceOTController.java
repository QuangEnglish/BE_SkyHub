package com.company_management.controller.quanlychamcong;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.AttendanceOTDTO;
import com.company_management.model.request.SearchAttendanceOTRequest;
import com.company_management.service.AttendanceOTService;
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
@RequestMapping("/api/v1/attendanceOt")
public class AttendanceOTController {

    private final AttendanceOTService attendanceOTService;

    @PostMapping("/search")
    public ResultResp<Object> searchOT(@RequestBody SearchAttendanceOTRequest searchAttendanceOTRequest,
                                             Pageable pageable) {
        return ResultResp.success(attendanceOTService.search(searchAttendanceOTRequest, pageable));
    }

    @PostMapping
    public ResultResp<Object> createOT(@Valid @RequestBody AttendanceOTDTO attendanceOTDTO) {
        attendanceOTService.createOrUpdate(attendanceOTDTO);
        return ResultResp.success(ErrorCode.CREATED_OK, null);
    }

    @GetMapping("/detail/{id}")
    public ResultResp<Object> getByIdOT(@PathVariable("id") Long id) {
        return ResultResp.success(ErrorCode.CREATED_OK, attendanceOTService.detailOT(id));
    }

    @PutMapping
    public ResultResp<Object> updateOT(@Valid @RequestBody AttendanceOTDTO leaveDTO) {
        attendanceOTService.createOrUpdate(leaveDTO);
        return ResultResp.success(ErrorCode.UPDATED_OK, null);
    }

    @DeleteMapping("/delete/{id}")
    public ResultResp<Object> deleteOT(@PathVariable("id") Long id) {
        attendanceOTService.deleteOT(id);
        return ResultResp.success(ErrorCode.DELETED_OK, null);
    }

    @PostMapping(value = "/export")
    public ResponseEntity<Object> exportExcel(@RequestBody SearchAttendanceOTRequest searchAttendanceOTRequest, Pageable pageable) {
        ByteArrayInputStream result = attendanceOTService.exportExcel(searchAttendanceOTRequest, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = CommonUtils.getFileNameReportUpdate("EXPORT_OT");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<>(new InputStreamResource(result), headers, HttpStatus.OK);
    }

}
