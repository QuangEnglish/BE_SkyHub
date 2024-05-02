package com.company_management.repository;

import com.company_management.model.entity.SocialInsurance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialInsuranceRepository extends JpaRepository<SocialInsurance, Long> {

    Page<SocialInsurance> findByUserDetailId(Long userDetailId, Pageable pageable);
    @Modifying
    @Query(value = "update SocialInsurance s set s.isActive = 0, s.updatedDate = now(), s.updatedUser = :user where s.id = :id and s.isActive = 1")
    int updateById(Long id, Long user);

}
