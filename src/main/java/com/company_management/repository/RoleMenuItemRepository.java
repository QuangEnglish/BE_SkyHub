package com.company_management.repository;

import com.company_management.model.entity.RoleMenuItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuItemRepository extends JpaRepository<RoleMenuItem, Long> {

    @Modifying
    @Query(value = "update UserDetailContract c set c.isActive = 0, c.updatedDate = now(), c.updatedUser = :user where c.id = :id and c.isActive = 1 or c.isActive = 2 ")
    int updateById(Long id, Long user);

    @Transactional
    void deleteByRoleId(Long roleId);

}
