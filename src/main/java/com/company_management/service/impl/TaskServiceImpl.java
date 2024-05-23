package com.company_management.service.impl;


import com.company_management.common.Constants;
import com.company_management.exception.AppException;
import com.company_management.model.dto.TaskDTO;
import com.company_management.model.entity.Task;
import com.company_management.model.entity.TaskAssignment;
import com.company_management.model.entity.UserDetail;
import com.company_management.repository.EmployeeRepository;
import com.company_management.repository.TaskAssignRepository;
import com.company_management.repository.TaskRepository;
import com.company_management.service.TaskService;
import com.company_management.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final TaskAssignRepository taskAssignRepository;

    @Override
    public List<Task> listTaskFindAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> listTaskFindById(Long id) {
        List<TaskAssignment> lstTaskAssignment = taskAssignRepository.findAllByEmployeeId(id);
        List<Task> lstTask = new ArrayList<>();
        lstTaskAssignment.forEach(res -> {
            Task task = taskRepository.findById(res.getTaskId()).orElseThrow(
                    () -> new AppException("ERR01", "Task không tồn tại")
            );
            lstTask.add(task);
        });
        return lstTask;
    }

    @Override
    public TaskDTO detailTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy task này!"));
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTaskCode(task.getTaskCode());
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setTaskDescription(task.getTaskDescription());
        taskDTO.setTaskStatus(task.getTaskStatus());
        taskDTO.setStartDay(task.getStartDay());
        taskDTO.setEndDay(task.getEndDay());
        taskDTO.setProjectId(task.getProjectId());
        taskDTO.setPriority(task.getPriority());
        UserDetail userDetail = new UserDetail();
        if (task.getFollowId() != null) {
            userDetail = employeeRepository.findById(task.getFollowId()).orElseThrow(() ->
                    new AppException("ERO01", "Người theo dõi không tồn tại"));
        }
        taskDTO.setFollowId(userDetail.getId());
        List<TaskAssignment> allByTaskId = taskAssignRepository.findAllByTaskId(id);
        List<String> lstUserDetail = new ArrayList<>();
        allByTaskId.forEach(res -> {
            UserDetail getUserDetail = employeeRepository.findById(res.getEmployeeId())
                    .orElseThrow(() -> new AppException("ERR01", "Không tồn tại nhân viên tham gia"));

            lstUserDetail.add(getUserDetail.getEmployeeName() + " - " + getUserDetail.getEmployeeCode());
        });
        taskDTO.setEmployees(lstUserDetail);
        return taskDTO;
    }

    @Override
    public void createTask(TaskDTO taskDTO) {
        log.debug("// Create task");
        UserDetail userDetail = new UserDetail();
        if (taskDTO.getFollowId() != null) {
            userDetail = employeeRepository.findById(taskDTO.getFollowId()).orElseThrow(() ->
                    new AppException("ERO01", "Người theo dõi không tồn tại"));
        }
        Task task = new Task();
        task.setTaskCode(taskDTO.getTaskCode());
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskStatus(taskDTO.getTaskStatus());
        task.setStartDay(taskDTO.getStartDay());
        task.setEndDay(taskDTO.getEndDay());
        task.setProjectId(taskDTO.getProjectId());
        task.setFollowId(userDetail.getId());
        task.setPriority(taskDTO.getPriority());
        task.setIsActive(Constants.STATUS_ACTIVE_INT);
        taskRepository.save(task);
        List<String> collect = taskDTO.getEmployees().stream().map(res -> res.split(" - ")).map(res -> res[1].trim()).toList();
        for (int i = 0; i < collect.size(); i++) {
            UserDetail byEmployeeCode = employeeRepository.findByEmployeeCode(collect.get(i));
            if (byEmployeeCode == null) {
                throw new AppException("ERO01", "Thành viên không tồn tại");
            }
            TaskAssignment taskAssignment = new TaskAssignment();
            taskAssignment.setTaskId(task.getId());
            taskAssignment.setEmployeeId(byEmployeeCode.getId());
            taskAssignRepository.save(taskAssignment);
        }
        log.info("// Lưu task thành công!");
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskDTO.getId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy task này!"));
        log.debug("// Update task");
        UserDetail userDetail;
        if (taskDTO.getFollowId() != null) {
            userDetail = employeeRepository.findById(taskDTO.getFollowId()).orElseThrow(() ->
                    new AppException("ERO01", "Người theo dõi không tồn tại"));
            task.setFollowId(userDetail.getId());
        }
        if (!DataUtils.isNullOrEmpty(taskDTO.getTaskName())) {
            task.setTaskName(taskDTO.getTaskName());
        }
        if (!DataUtils.isNullOrEmpty(taskDTO.getTaskDescription())) {
            task.setTaskDescription(taskDTO.getTaskDescription());
        }
        if (!DataUtils.isNullOrEmpty(taskDTO.getStartDay())) {
            task.setStartDay(taskDTO.getStartDay());
        }
        if (!DataUtils.isNullOrEmpty(taskDTO.getEndDay())) {
            task.setEndDay(taskDTO.getEndDay());
        }
        task.setPriority(taskDTO.getPriority());
        taskRepository.save(task);
        taskAssignRepository.deleteByTaskId(task.getId());
        List<String> collect = taskDTO.getEmployees().stream().map(res -> res.split(" - ")).map(res -> res[1].trim()).toList();
        for (int i = 0; i < collect.size(); i++) {
            UserDetail byEmployeeCode = employeeRepository.findByEmployeeCode(collect.get(i));
            if (byEmployeeCode == null) {
                throw new AppException("ERO01", "Thành viên không tồn tại");
            }
            TaskAssignment taskAssignment = new TaskAssignment();
            taskAssignment.setTaskId(task.getId());
            taskAssignment.setEmployeeId(byEmployeeCode.getId());
            taskAssignRepository.save(taskAssignment);
        }
        log.info("// cập nhật task thành công!");
    }
}
