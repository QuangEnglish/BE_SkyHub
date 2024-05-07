package com.company_management.repository;

import com.company_management.model.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    @Modifying
    @Query(value = "update UserDetail c set c.isActive = 2, c.updatedDate = now(), c.updatedUser = :user where c.id = :id and c.isActive = 1 or c.isActive = 2 ")
    int updateIsActiveById(Long id, Long user);
}
