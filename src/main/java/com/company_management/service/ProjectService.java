package com.company_management.service;

import com.company_management.model.dto.ProjectDTO;
import com.company_management.model.entity.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> listProjectFindAll();

    Optional<Project> projectFindById(Long id);

    ProjectDTO detailProject(Long id);

    void createProject(MultipartFile avatarFile, ProjectDTO projectDTO) throws IOException;

    void updateProject(MultipartFile avatarFile, ProjectDTO projectDTO) throws IOException;



}
