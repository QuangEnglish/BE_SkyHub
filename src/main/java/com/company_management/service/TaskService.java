package com.company_management.service;

import com.company_management.model.dto.TaskDTO;
import com.company_management.model.response.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskDTO> listTaskFindAll();

    List<TaskResponse> listTaskFindByEmployeeAndProject(Long userDetailId, Long projectID);
    List<TaskDTO> listTaskDtoByEmployeeAndProject(Long userDetailId, Long projectID);

    TaskDTO detailTask(Long id);

    void createTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO);

    void updateTaskStatus(Long id, int taskStatus);



}
