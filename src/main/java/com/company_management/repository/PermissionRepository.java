package com.company_management.repository;

import com.company_management.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
