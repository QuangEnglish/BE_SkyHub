package com.company_management.repository;

import com.company_management.model.entity.UserDetailWage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailWageRepository extends JpaRepository<UserDetailWage, Long> {

    List<UserDetailWage> findByUserDetailId (Long id);

    List<UserDetailWage> findByWageId (Long id);

    @Modifying
    @Query(value = "update UserDetailWage c set c.isActive = 0, c.updatedDate = now(), c.updatedUser = :user where c.id = :id and c.isActive = 1 or c.isActive = 2 ")
    int updateById(Long id, Long user);
}
