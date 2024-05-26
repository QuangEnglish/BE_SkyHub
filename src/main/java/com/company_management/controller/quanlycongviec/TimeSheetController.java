package com.company_management.controller.quanlycongviec;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.TimeSheetDTO;
import com.company_management.service.TimeSheetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/timesheet")
public class TimeSheetController {

    private final TimeSheetService timeSheetService;

    @PostMapping("/create")
    public ResultResp<Object> addTimeSheet(@RequestBody TimeSheetDTO timeSheetDTO) {
        timeSheetService.createTimeSheet(timeSheetDTO);
        return ResultResp.success(ErrorCode.CREATED_OK, null);
    }

    @PostMapping("/search")
    public ResultResp<Object> getAllTimeSheet(@RequestParam(value = "taskId", required = false) Long taskId){
        return ResultResp.success(timeSheetService.listTimeSheetDtoByTaskId(taskId));
    }

    @PostMapping("/deleteTimeSheet/{id}")
    public ResultResp<Object> deleteTimeSheet(@PathVariable("id") Long id) {
        try{
            timeSheetService.deleteTimeSheet(id);
            return ResultResp.success(ErrorCode.DELETED_OK, null);
        }catch (Exception ex){
            return ResultResp.badRequest(ErrorCode.DELETED_FAIL);
        }
    }



}
