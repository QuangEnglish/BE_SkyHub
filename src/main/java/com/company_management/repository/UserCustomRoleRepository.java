package com.company_management.repository;

import com.company_management.model.entity.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCustomRoleRepository extends JpaRepository<UserCustom, Long> {
    Optional<UserCustom> findByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM user_custom_role  WHERE user_id = :userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "INSERT INTO user_custom_role (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

}
