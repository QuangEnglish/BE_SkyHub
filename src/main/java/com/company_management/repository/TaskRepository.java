package com.company_management.repository;

import com.company_management.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByProjectIdAndTaskStatus(Long projectId, int statusId);

    List<Task> findAllByProjectId(Long projectId);

}
