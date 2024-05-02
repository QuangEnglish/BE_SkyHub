package com.company_management.repository;

import com.company_management.model.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<UserDetail, Long>, EmployeeRepositoryCustom {

    @Query("select u from UserDetail u where u.employeeCode = :code and u.isActive = 1")
    UserDetail findByEmployeeCode(String code);
    @Modifying
    @Query(value = "update UserDetail u set u.isActive = 0, u.updatedDate = now(), u.updatedUser = :user where u.id = :id and u.isActive = 1")
    int deleteById(Long id, Long user);

}
