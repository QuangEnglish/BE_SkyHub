package com.company_management.repository;

import com.company_management.model.entity.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {

    Page<Qualification> findByUserDetailId(Long userDetailId, Pageable pageable);
    @Modifying
    @Query(value = "update Qualification q set q.isActive = 0, q.updatedDate = now(), q.updatedUser = :user where q.id = :id and q.isActive = 1 or q.isActive = 2 ")
    int updateById(Long id, Long user);
}
