package com.company_management.repository;

import com.company_management.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long>, PositionRepositoryCustom {

    @Query(value = "SELECT p FROM Position p WHERE LOWER(p.positionName) = :name")
    Optional<Position> findByName(String name);

    @Query(value = "SELECT p FROM Position p WHERE p.id = :id AND p.departmentId = :departmentId AND p.isActive = 1")
    Optional<Position> findByIdAndDepartmentId(Long id, Long departmentId);

    @Modifying
    @Query(value = "update Position p set p.isActive = 0, p.updatedDate = now(), p.updatedUser = :user where p.id = :id and p.isActive = 1")
    int deleteById(Long id, Long user);

}
