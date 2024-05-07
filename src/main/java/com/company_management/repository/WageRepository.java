package com.company_management.repository;

import com.company_management.model.entity.Wage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface WageRepository extends JpaRepository<Wage, Long>, WageRepositoryCustom {

    String sqlSearch = "select\n" +
            "    we.id as wageId,\n" +
            "    we.wage_name as wageName,\n" +
            "    we.wage_base as wageBase,\n" +
            "    we.wage_description as wageDescription,\n" +
            "    we.attachfile as attachFile,\n" +
            "    we.created_date as createdDate \n" +
            "from wage we\n" +
            "where 1 = 1\n" +
            "and we.is_active = 1\n" +
            "and (:wageName IS NULL OR LOWER(we.wage_name) LIKE LOWER(CONCAT('%', :wageName, '%')))\n" +
            "and (:createdDate IS NULL OR we.created_date >= :createdDate ) ";
    @Query(nativeQuery = true, value = sqlSearch, countQuery = "select count(*) from ( " + sqlSearch + " ) tmp" )
    Page<Object[]> findAllWithPagination(
            @Param("wageName") String wageName,
            @Param("createdDate") Date createdDate,
            Pageable pageable);

    @Modifying
    @Query(value = "update Wage c set c.isActive = 0, c.updatedDate = now(), c.updatedUser = :user where c.id = :id and c.isActive = 1 or c.isActive = 2 ")
    int updateById(Long id, Long user);
}
