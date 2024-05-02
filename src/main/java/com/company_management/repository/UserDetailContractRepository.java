package com.company_management.repository;

import com.company_management.model.entity.UserDetailContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailContractRepository extends JpaRepository<UserDetailContract, Long> {

    List<UserDetailContract> findByUserDetailId (Long id);

    List<UserDetailContract> findByContractId (Long id);

    @Modifying
    @Query(value = "update UserDetailContract c set c.isActive = 0, c.updatedDate = now(), c.updatedUser = :user where c.id = :id and c.isActive = 1")
    int updateById(Long id, Long user);
}
