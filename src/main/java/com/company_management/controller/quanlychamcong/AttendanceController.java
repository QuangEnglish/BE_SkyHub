package com.company_management.controller.quanlychamcong;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.AttendanceDTO;
import com.company_management.model.request.SearchAttendanceRequest;
import com.company_management.service.AttendanceService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping(value = "/exportListFollowMonth")
//    public ResponseEntity<Object> exportListFollowMonth(@RequestBody SearchAttendanceRequest searchAttendanceRequest) {
//        ByteArrayInputStream result = attendanceService.exportListFollowMonth(searchAttendanceRequest);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        String fileName = CommonUtils.getFileNameReportUpdate("EXPORT_CHAM_CONG_THANG");
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
//        return new ResponseEntity<>(new InputStreamResource(result), headers, HttpStatus.OK);
//    }

    @PostMapping("/exportListFollowMonth")
    public ResponseEntity<Object> exportListFollowMonth(HttpServletResponse response,
                                                        @RequestBody SearchAttendanceRequest searchAttendanceRequest) {
        attendanceService.exportServices(searchAttendanceRequest, response);
        return ResultResp.success("Xuất file thành công");
    }

}
