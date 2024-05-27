package com.company_management.service.impl;


import com.company_management.common.Constants;
import com.company_management.common.enums.TaskStatusType;
import com.company_management.exception.AppException;
import com.company_management.model.dto.TaskDTO;
import com.company_management.model.entity.Project;
import com.company_management.model.entity.Task;
import com.company_management.model.entity.TaskAssignment;
import com.company_management.model.entity.UserDetail;
import com.company_management.model.response.TaskResponse;
import com.company_management.repository.EmployeeRepository;
import com.company_management.repository.ProjectRepository;
import com.company_management.repository.TaskAssignRepository;
import com.company_management.repository.TaskRepository;
import com.company_management.service.TaskService;
import com.company_management.utils.CommonUtils;
import com.company_management.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final TaskAssignRepository taskAssignRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<TaskDTO> listTaskFindAll() {
        List<Task> all = taskRepository.findAll();
        List<TaskDTO> taskDTOList = new ArrayList<>();
        all.forEach(res -> {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTaskCode(res.getTaskCode());
            taskDTO.setTaskName(res.getTaskName());
            taskDTO.setTaskStatus(res.getTaskStatus());
            taskDTO.setTaskDescription(res.getTaskDescription());
            taskDTO.setId(res.getId());
            taskDTO.setStartDay(res.getStartDay());
            taskDTO.setEndDay(res.getEndDay());
            taskDTO.setPriority(res.getPriority());
            taskDTO.setPriorityName(res.getPriority() == 1 ? "Low" : res.getPriority() == 2 ? "Normal" :  "Hight");
            taskDTO.setDuration(res.getDuration());
            if(res.getProjectId()!=null){
                Project project = projectRepository.findById(res.getProjectId()).orElseThrow(() -> new AppException("ERR01", "Dự án không tồn tại!"));
                taskDTO.setProjectName(project.getProjectName());
            }
            taskDTO.setProjectId(res.getProjectId());
            taskDTOList.add(taskDTO);
        });
        return taskDTOList;
    }

    @Override
    public List<TaskResponse> listTaskFindByEmployeeAndProject(Long userDetailId, Long projectID) {
        List<TaskResponse> lstResult = new ArrayList<>();
        lstResult.add(taskResponse(userDetailId, projectID, TaskStatusType.MOI.getTypeName(), TaskStatusType.MOI.getType()));
        lstResult.add(taskResponse(userDetailId, projectID, TaskStatusType.DANGXULY.getTypeName(), TaskStatusType.DANGXULY.getType()));
        lstResult.add(taskResponse(userDetailId, projectID, TaskStatusType.REVIEW.getTypeName(), TaskStatusType.REVIEW.getType()));
        lstResult.add(taskResponse(userDetailId, projectID, TaskStatusType.REOPEN.getTypeName(), TaskStatusType.REOPEN.getType()));
        lstResult.add(taskResponse(userDetailId, projectID, TaskStatusType.HOANTHANH.getTypeName(), TaskStatusType.HOANTHANH.getType()));
        return lstResult;
    }

    @Override
    public List<TaskDTO> listTaskDtoByEmployeeAndProject(Long userDetailId, Long projectID) {
        List<Task> allByProjectId= taskRepository.findAllByProjectId(projectID);
        List<TaskDTO> lstTaskDto = allByProjectId.stream().map(
                res -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setId(res.getId());
                    taskDTO.setTaskCode(res.getTaskCode());
                    taskDTO.setTaskName(res.getTaskName());
                    taskDTO.setTaskDescription(res.getTaskDescription());
                    taskDTO.setTaskStatus(res.getTaskStatus());
                    taskDTO.setTaskStatusName(TaskStatusType.getNameByType(res.getTaskStatus()));
                    taskDTO.setStartDay(res.getStartDay());
                    taskDTO.setEndDay(res.getEndDay());
                    taskDTO.setProjectId(res.getProjectId());
                    Project project = projectRepository.findById(res.getProjectId()).orElseThrow(() -> new AppException("ERR01", "Dự án không tồn tại"));
                    taskDTO.setProjectName(project.getProjectName());
                    taskDTO.setFollowId(res.getFollowId());
                    taskDTO.setPriority(res.getPriority());
                    taskDTO.setDuration(res.getDuration());
                    taskDTO.setCommunication(res.getCommunication());
                    return taskDTO;
                }
        ).toList();
        List<TaskDTO> collect = new ArrayList<>();
        List<TaskAssignment> allByEmployeeId = taskAssignRepository.findAllByEmployeeId(userDetailId);
        allByEmployeeId.forEach(
                tak ->  {
                    List<TaskDTO> filteredList = lstTaskDto.stream().filter(res -> Objects.equals(res.getId(), tak.getTaskId())).toList();
                    collect.addAll(filteredList);
                }
        );
        return collect;
    }

    public TaskResponse taskResponse(Long userDetailId, Long projectID, String taskStatusName, int taskStatusId){
        List<Task> allByProjectIdAndTaskStatus = taskRepository.findAllByProjectIdAndTaskStatus(projectID, taskStatusId);
        List<TaskDTO> lstTaskDto = allByProjectIdAndTaskStatus.stream().map(
                res -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setId(res.getId());
                    taskDTO.setTaskCode(res.getTaskCode());
                    taskDTO.setTaskName(res.getTaskName());
                    taskDTO.setTaskDescription(res.getTaskDescription());
                    taskDTO.setTaskStatus(res.getTaskStatus());
                    taskDTO.setTaskStatusName(taskStatusName);
                    taskDTO.setStartDay(res.getStartDay());
                    taskDTO.setEndDay(res.getEndDay());
                    taskDTO.setProjectId(res.getProjectId());
                    Project project = projectRepository.findById(res.getProjectId()).orElseThrow(() -> new AppException("ERR01", "Dự án không tồn tại"));
                    taskDTO.setProjectName(project.getProjectName());
                    taskDTO.setFollowId(res.getFollowId());
                    taskDTO.setPriority(res.getPriority());
                    taskDTO.setDuration(res.getDuration());
                    taskDTO.setCommunication(res.getCommunication());
                    return taskDTO;
                }
        ).toList();
        List<TaskDTO> collect = new ArrayList<>();
        List<TaskAssignment> allByEmployeeId = taskAssignRepository.findAllByEmployeeId(userDetailId);
        allByEmployeeId.forEach(
              tak ->  {
                  List<TaskDTO> filteredList = lstTaskDto.stream().filter(res -> Objects.equals(res.getId(), tak.getTaskId())).toList();
                  collect.addAll(filteredList);
              }
        );
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setName(taskStatusName);
        taskResponse.setTaskForm(collect);
        return taskResponse;
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
        taskDTO.setDuration(task.getDuration());
        taskDTO.setCommunication(task.getCommunication());
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
        task.setDuration(taskDTO.getDuration());
        task.setCommunication(taskDTO.getCommunication());
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
        Double numberDuration = taskRepository.countDurationByProjectId(task.getProjectId());
        Project project = projectRepository.findById(task.getProjectId()).orElseThrow(() -> new AppException("ERR01", "Dự án không tồn tại"));
        project.setEtimate(numberDuration);
        projectRepository.save(project);
        log.info("// Lưu task thành công!");
    }

    @Override
    @Transactional
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
        task.setDuration(taskDTO.getDuration());
        task.setPriority(taskDTO.getPriority());
        if (!DataUtils.isNullOrEmpty(taskDTO.getCommunication())) {
            task.setCommunication(taskDTO.getCommunication());
        }
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
        Double numberDuration = taskRepository.countDurationByProjectId(task.getProjectId());
        Project project = projectRepository.findById(task.getProjectId()).orElseThrow(() -> new AppException("ERR01", "Dự án không tồn tại"));
        project.setEtimate(numberDuration);
        projectRepository.save(project);
        log.info("// cập nhật task thành công!");
    }

    @Override
    @Transactional
    public void updateTaskStatus(Long id, int taskStatus) {
        log.debug("// Cập nhật trạng thái: {}", id);
        if (taskRepository.updateStatusById(id, taskStatus, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Không tìm thấy task công việc này!");
        }
    }
}
