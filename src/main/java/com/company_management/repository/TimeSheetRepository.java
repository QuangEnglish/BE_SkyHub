package com.company_management.repository;

import com.company_management.model.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {

    List<TimeSheet> findAllByTaskId(Long taskId);

    @Modifying
    @Query(value = "update TimeSheet u set u.isActive = 0, u.updatedDate = now(), u.updatedUser = :user where u.id = :id and u.isActive = 1")
    int deleteTimeSheet(Long id, Long user);

    @Query(value = "SELECT SUM(durationTimeSheet) as totalDuration\n" +
            "FROM time_sheet\n" +
            "where is_active = 1 and task_id in (select t.id\n" +
            "                  from task t\n" +
            "                  where :projectId = t.project_id)", nativeQuery = true)
    Double findTotalDurationByProjectId(@Param("projectId") Long projectId);

}
