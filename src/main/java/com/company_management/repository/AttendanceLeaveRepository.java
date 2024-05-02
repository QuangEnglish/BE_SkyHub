package com.company_management.repository;

import com.company_management.model.entity.AttendanceLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceLeaveRepository extends JpaRepository<AttendanceLeave, Long>, AttendanceLeaveRepositoryCustom {

    @Modifying
    @Query(value = "update AttendanceLeave p set p.isActive = 0, p.updatedDate = now(), p.updatedUser = :user where p.id = :id ")
    int deleteById(Long id, Long user);

}
