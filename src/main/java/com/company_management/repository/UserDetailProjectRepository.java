package com.company_management.repository;

import com.company_management.model.entity.UserDetailProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailProjectRepository extends JpaRepository<UserDetailProject, Long> {

    List<UserDetailProject> findAllByUserDetailId (Long id);

    List<UserDetailProject> findAllByProjectId (Long id);

    @Modifying
    @Query(value = "DELETE FROM user_detail_project  WHERE project_id = :projectId", nativeQuery = true)
    void deleteByProjectId(@Param("projectId") Long projectId);


}
