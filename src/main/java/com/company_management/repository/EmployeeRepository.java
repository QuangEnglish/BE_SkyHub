package com.company_management.repository;

import com.company_management.model.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<UserDetail, Long>, EmployeeRepositoryCustom {

    @Query("select u from UserDetail u where u.employeeCode = :code and (u.isActive = 1 or u.isActive = 2) ")
    UserDetail findByEmployeeCode(String code);
    @Modifying
    @Query(value = "update UserDetail u set u.isActive = 0, u.updatedDate = now(), u.updatedUser = :user where u.id = :id and u.isActive = 1 or u.isActive = 2 ")
    int deleteById(Long id, Long user);

    @Query("SELECT e FROM UserDetail e WHERE MONTH(e.birthday) = MONTH(CURRENT_DATE) AND DAY(e.birthday) = DAY(CURRENT_DATE)")
    List<UserDetail> findEmployeesWithBirthdaysToday();

    @Query("SELECT e FROM UserDetail e WHERE MONTH(e.birthday) = MONTH(CURRENT_DATE)")
    List<UserDetail> findEmployeesWithBirthdaysInCurrentMonth();

    @Query(value = """
            SELECT
                nv.employee_name as employeeName,
                COUNT(ta.id) AS totalTask,
                SUM(CASE WHEN t.task_status = 5 THEN 1 ELSE 0 END) AS totalTaskDone,
                (SUM(CASE WHEN t.task_status = 5 THEN 1 ELSE 0 END) / NULLIF(COUNT(ta.id), 0)) AS percentage
            FROM
                user_detail nv
                    LEFT JOIN
                task_assignment ta ON nv.id = ta.employee_id
                    LEFT JOIN
                task t ON ta.task_id = t.id
            GROUP BY
                nv.employee_code;
                        """, nativeQuery = true)
    List<Object[]> getStatisticalTask();

}
