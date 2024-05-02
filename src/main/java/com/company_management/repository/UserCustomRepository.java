package com.company_management.repository;

import com.company_management.model.entity.UserCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCustomRepository extends JpaRepository<UserCustom, Long>, _UserCustomRepository {
    Optional<UserCustom> findByEmail(String email);

    Optional<UserCustom> findByUserDetailId(Long userDetailId);

    Optional<UserCustom> findByActiveCode(String activeCode);
    Optional<UserCustom> findByForgotPassword(String forgotPassword);
    @Query(value = """
            SELECT *
            FROM user_custom u
            ORDER BY u.created_date DESC
            """,
            nativeQuery = true)
    Page<UserCustom> findAllWithPagination(Pageable pageable);
}
