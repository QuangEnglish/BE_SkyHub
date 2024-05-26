package com.company_management.repository;

import com.company_management.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByProjectIdAndTaskStatus(Long projectId, int statusId);

    List<Task> findAllByProjectId(Long projectId);

    @Modifying
    @Query(value = "update Task u set u.taskStatus = :taskStatus, u.updatedDate = now(), u.updatedUser = :user where u.id = :id and u.isActive = 1")
    int updateStatusById(Long id,int taskStatus, Long user);

    @Query(value = "SELECT COUNT(*) FROM Task WHERE project_id = :projectId", nativeQuery = true)
    Long countTasksByProjectId(@Param("projectId") Long projectId);

    @Query(value = "SELECT SUM(duration) FROM Task WHERE project_id = :projectId GROUP BY project_id", nativeQuery = true)
    Double countDurationByProjectId(@Param("projectId") Long projectId);

}
