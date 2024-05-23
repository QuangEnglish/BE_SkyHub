package com.company_management.service;

import com.company_management.model.dto.TaskDTO;
import com.company_management.model.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> listTaskFindAll();

    List<Task> listTaskFindById(Long id);

    TaskDTO detailTask(Long id);

    void createTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO);



}
