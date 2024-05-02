package com.company_management.repository;

import com.company_management.model.entity.Wage;
import com.company_management.repository.impl.ContractCustomRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WageRepository extends JpaRepository<Wage, Long>, WageCustomRepository {
    List<Wage> findAllByUserDetailId (Long id);
}
