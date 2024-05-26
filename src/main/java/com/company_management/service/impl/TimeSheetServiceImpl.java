package com.company_management.service.impl;


import com.company_management.common.Constants;
import com.company_management.exception.AppException;
import com.company_management.model.dto.TimeSheetDTO;
import com.company_management.model.entity.Project;
import com.company_management.model.entity.Task;
import com.company_management.model.entity.TimeSheet;
import com.company_management.repository.ProjectRepository;
import com.company_management.repository.TaskRepository;
import com.company_management.repository.TimeSheetRepository;
import com.company_management.service.TimeSheetService;
import com.company_management.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class TimeSheetServiceImpl implements TimeSheetService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TimeSheetRepository timeSheetRepository;

    @Override
    public List<TimeSheet> listTimeSheetDtoByTaskId(Long taskId) {
        List<TimeSheet> allByTaskId = timeSheetRepository.findAllByTaskId(taskId);
        return allByTaskId.stream().filter(res -> res.getIsActive() == 1).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createTimeSheet(TimeSheetDTO timeSheetDTO) {
        log.debug("// Create time sheet");
        Task task = new Task();
        if (timeSheetDTO.getTaskId() != null) {
            task = taskRepository.findById(timeSheetDTO.getTaskId()).orElseThrow(() ->
                    new AppException("ERO01", "Task không tồn tại"));
        }
        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setTaskId(task.getId());
        timeSheet.setDayTimeSheet(timeSheetDTO.getDayTimeSheet());
        timeSheet.setDurationTimeSheet(timeSheetDTO.getDurationTimeSheet());
        timeSheet.setTimeSheetDescription(timeSheetDTO.getTimeSheetDescription());
        timeSheet.setIsActive(Constants.STATUS_ACTIVE_INT);
        timeSheetRepository.save(timeSheet);
        Project project = projectRepository.findById(task.getProjectId()).orElseThrow(() ->
                new AppException("ERO01", "Dự án không tồn tại"));
        project.setTimesheet(timeSheetRepository.findTotalDurationByProjectId(project.getId()));
        projectRepository.save(project);
    }


    @Override
    @Transactional
    public void deleteTimeSheet(Long id) {
        log.debug("// Cập nhật xóa time sheet: {}", id);
        if (timeSheetRepository.deleteTimeSheet(id,CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Không tìm thấy time sheet này!");
        }
        TimeSheet timeSheet = timeSheetRepository.findById(id).orElseThrow(() -> new AppException("ERR01", "TimeSheet không tồn tại"));
        Task task = taskRepository.findById(timeSheet.getTaskId()).orElseThrow(() ->
                new AppException("ERO01", "Task không tồn tại"));
        Project project = projectRepository.findById(task.getProjectId()).orElseThrow(() ->
                new AppException("ERO01", "Dự án không tồn tại"));
        project.setTimesheet(timeSheetRepository.findTotalDurationByProjectId(project.getId()));
        projectRepository.save(project);
    }
}
