package com.company_management.repository;

import com.company_management.model.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "SELECT d FROM Department d WHERE LOWER(d.departmentCode) = :departmentCode")
    Optional<Department> findByCode(String departmentCode);

    @Query(value = """
        SELECT d.department_code
        FROM department d 
        WHERE 1 = 1
        """, nativeQuery = true)
    List<String> listCodeDepartment();

    @Query(value = """
            SELECT *
            FROM department d
            where (:name is null or lower(d.department_name) LIKE lower(concat('%', :name, '%')))
            and d.status in :status
            ORDER BY d.created_date DESC
            """,
            nativeQuery = true)
    Page<Department> findAllWithPagination(@Param("status") List<String> status,
                                         @Param("name") String name,
                                         Pageable pageable);

    @Modifying
    @Query(value = "update Department u set u.isActive = 0, u.updatedDate = now(), u.updatedUser = :user where u.id = :id and u.isActive = 1")
    int deleteById(Long id, Long user);

    @Query(value = """
            select de.department_name as name, COUNT(ud.id) as vale
                  from department de
                  left join user_detail ud
                  on de.id = ud.department_id
                  group by de.department_name; 
            """, nativeQuery = true)
    List<Object[]> getStatisticalDepartment();

    @Query(value = """
            select count(id) as totalEmployee
            from user_detail ud
                        """, nativeQuery = true)
    Long getStatisticalTotalEmployee();


    @Query(value = """
            SELECT COUNT(*) AS totalEmployees
            FROM user_detail
            WHERE MONTH(birthday) = MONTH(CURRENT_DATE);
                                    """, nativeQuery = true)
    Long getStatisticalTotalBirthDayMonth();

    @Query(value = "select COUNT(at.totalPenalty) as totalLateWork\n" +
            "from UserDetail ud\n" +
            "left join  Attendance at\n" +
            "on ud.id = at.employeeId\n" +
            "where (:employeeId is null or ud.id = :employeeId)\n" +
            "and MONTH(at.workingDay) = MONTH(CURRENT_DATE)")
    Long getStatisticalTotalLateWork(Long employeeId);

    @Query(value = "SELECT COALESCE(SUM(al.totalTime), 0) AS  totalLeaveWork\n" +
            "from UserDetail ud\n" +
            "         left join AttendanceLeave al\n" +
            "                   on ud.id = al.employeeId\n" +
            "WHERE MONTH(al.startDay) = MONTH(CURRENT_DATE)\n" +
            "  and  (:employeeId is null or ud.id = :employeeId)")
    Long getStatisticalTotalLeaveWork(Long employeeId);

}
