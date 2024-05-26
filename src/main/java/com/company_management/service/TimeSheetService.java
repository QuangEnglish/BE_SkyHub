package com.company_management.service;

import com.company_management.model.dto.TimeSheetDTO;
import com.company_management.model.entity.TimeSheet;

import java.util.List;

public interface TimeSheetService {
    List<TimeSheet> listTimeSheetDtoByTaskId(Long taskId);

    void createTimeSheet(TimeSheetDTO timeSheetDTO);

    void deleteTimeSheet(Long id);



}
