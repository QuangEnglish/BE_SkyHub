package com.company_management.repository;

import com.company_management.model.entity.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskAssignRepository extends JpaRepository<TaskAssignment, Long> {

    List<TaskAssignment> findAllByEmployeeId (Long id);

    List<TaskAssignment> findAllByTaskId (Long id);

    @Modifying
    @Query(value = "DELETE FROM task_assignment  WHERE task_id = :taskId", nativeQuery = true)
    void deleteByTaskId(@Param("taskId") Long taskId);


}
