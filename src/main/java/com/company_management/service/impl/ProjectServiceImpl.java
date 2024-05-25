package com.company_management.service.impl;

import com.company_management.common.Constants;
import com.company_management.exception.AppException;
import com.company_management.model.dto.ProjectDTO;
import com.company_management.model.entity.Project;
import com.company_management.model.entity.UserDetail;
import com.company_management.model.entity.UserDetailProject;
import com.company_management.repository.EmployeeRepository;
import com.company_management.repository.ProjectRepository;
import com.company_management.repository.TaskRepository;
import com.company_management.repository.UserDetailProjectRepository;
import com.company_management.service.ProjectService;
import com.company_management.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final UserDetailProjectRepository userDetailProjectRepository;


    @Value("${upload.path}")
    private String fileUpload;


    @Override
    public List<Project> listProjectFindAll() {
        List<Project> all = projectRepository.findAll();
        List<Project> collect = all.stream().peek(res -> {
            Long l = taskRepository.countTasksByProjectId(res.getId());
            res.setTaskNumber(l);
        }).toList();
        return collect;
    }

    @Override
    public List<Project> projectFindById(Long id) {
        List<UserDetailProject> allByUserDetailId = userDetailProjectRepository.findAllByUserDetailId(id);
        List<Project> lstProjects = new ArrayList<>();
        allByUserDetailId.forEach(res -> {
            Project project = projectRepository.findById(res.getProjectId()).orElseThrow(
                    () -> new AppException("ERR01", "Dự án không tồn tại")
            );
            lstProjects.add(project);
        });
        return lstProjects;
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO detailProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy dự án này!"));
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setProjectCode(project.getProjectCode());
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setProjectDescription(project.getProjectDescription());
        projectDTO.setStartDay(project.getStartDay());
        projectDTO.setEndDay(project.getEndDay());
        projectDTO.setCustomerName(project.getCustomerName());
        projectDTO.setEtimate(project.getEtimate());
        projectDTO.setTimesheet(project.getTimesheet());
        projectDTO.setTaskNumber(project.getTaskNumber());
        UserDetail userDetail = new UserDetail();
        if (project.getProjectManagerId() != null) {
            userDetail = employeeRepository.findById(project.getProjectManagerId()).orElseThrow(() ->
                    new AppException("ERO01", "Quản lý dự án không tồn tại"));
        }
        projectDTO.setProjectManagerId(userDetail.getId());
        projectDTO.setCustomerAvatar(project.getCustomerAvatar());
        List<UserDetailProject> allByProjectId = userDetailProjectRepository.findAllByProjectId(id);
        List<String> lstUserDetail = new ArrayList<>();
        allByProjectId.forEach(userDetailProject -> {
            UserDetail getUserDetail = employeeRepository.findById(userDetailProject.getUserDetailId())
                    .orElseThrow(() -> new AppException("ERR01", "Không tồn tại nhân viên tham gia"));

            lstUserDetail.add(getUserDetail.getEmployeeName() + " - " + getUserDetail.getEmployeeCode());
        });
        projectDTO.setEmployees(lstUserDetail);
        return projectDTO;
    }

    @Override
    @Transactional
    public void createProject(MultipartFile avatarFile, ProjectDTO projectDTO) throws IOException {
        log.debug("// Create project");
        UserDetail userDetail = new UserDetail();
        if (projectDTO.getProjectManagerId() != null) {
            userDetail = employeeRepository.findById(projectDTO.getProjectManagerId()).orElseThrow(() ->
                    new AppException("ERO01", "Quản lý dự án không tồn tại"));
        }
        Project project = new Project();
        project.setProjectCode(projectDTO.getProjectCode());
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectDescription(projectDTO.getProjectDescription());
        project.setProjectManagerId(userDetail.getId());
        project.setProjectDescription(projectDTO.getProjectDescription());
        project.setStartDay(projectDTO.getStartDay());
        project.setEndDay(projectDTO.getEndDay());
        project.setCustomerName(projectDTO.getCustomerName());
        project.setEtimate(0.0);
        project.setTimesheet(0.0);
        project.setTaskNumber(0L);
        project.setIsActive(Constants.STATUS_ACTIVE_INT);
        //upload file ảnh
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(avatarFile.getOriginalFilename()));
        if (fileName.contains("..")) {
            log.debug("File upload không tồn tại!");
            throw new AppException("ERO01", "Tên tệp tin không hợp lệ");
        }
        Path filePath = Paths.get(this.fileUpload + fileName);
        Files.copy(avatarFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        project.setCustomerAvatar(fileName);
        if(projectDTO.getEmployees().isEmpty()){
            throw new AppException("ERO01", "Số lượng thành viên không hợp lệ");
        }
        projectRepository.save(project);
        List<String> collect = projectDTO.getEmployees().stream().map(res -> res.split(" - ")).map(res -> res[1].trim()).toList();
        for(int i =0; i < collect.size(); i++){
            UserDetail byEmployeeCode = employeeRepository.findByEmployeeCode(collect.get(i));
            if(byEmployeeCode == null){
                throw new AppException("ERO01", "Thành viên không tồn tại");
            }
            UserDetailProject userDetailProject = new UserDetailProject();
            userDetailProject.setProjectId(project.getId());
            userDetailProject.setUserDetailId(byEmployeeCode.getId());
            userDetailProjectRepository.save(userDetailProject);
        }
        log.info("// Lưu dự án thành công!");
    }

    @Override
    @Transactional
    public void updateProject(MultipartFile avatarFile, ProjectDTO projectDTO) throws IOException {
        Project project = projectRepository.findById(projectDTO.getId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy dự án này!"));
        log.debug("// Update project");
        UserDetail userDetail = new UserDetail();
        if (projectDTO.getProjectManagerId() != null) {
            userDetail = employeeRepository.findById(projectDTO.getProjectManagerId()).orElseThrow(() ->
                    new AppException("ERO01", "Quản lý dự án không tồn tại"));
            project.setProjectManagerId(userDetail.getId());
        }
        if (!DataUtils.isNullOrEmpty(projectDTO.getProjectName())) {
            project.setProjectName(projectDTO.getProjectName());
        }
        if (!DataUtils.isNullOrEmpty(projectDTO.getProjectDescription())) {
            project.setProjectDescription(projectDTO.getProjectDescription());
        }
        if (!DataUtils.isNullOrEmpty(projectDTO.getStartDay())) {
            project.setStartDay(projectDTO.getStartDay());
        }
        if (!DataUtils.isNullOrEmpty(projectDTO.getEndDay())) {
            project.setEndDay(projectDTO.getEndDay());
        }
        if (!DataUtils.isNullOrEmpty(projectDTO.getCustomerName())) {
            project.setCustomerName(projectDTO.getCustomerName());
        }
        //upload file ảnh
        if (avatarFile != null && avatarFile.getOriginalFilename() != null) {
            try {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(avatarFile.getOriginalFilename()));
                if (fileName.contains("..")) {
                    log.debug("File upload không tồn tại!");
                    throw new AppException("ERO01", "Tên tệp tin không hợp lệ");
                }
                Path filePath = Paths.get(this.fileUpload + fileName);
                Files.copy(avatarFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                if (!project.getCustomerAvatar().equals(filePath.toString())) {
                    project.setCustomerAvatar(fileName);
                }
            } catch (NullPointerException e) {
                log.error("File ảnh là null.", e);
                throw new AppException("ERO02", "File ảnh là null");
            } catch (IOException e) {
                log.error("Lỗi xảy ra khi xử lý file ảnh", e);
                throw new AppException("ERO02", "Lỗi xảy ra khi xử lý file ảnh");
            }
        }
        projectRepository.save(project);
        userDetailProjectRepository.deleteByProjectId(project.getId());
        List<String> collect = projectDTO.getEmployees().stream().map(res -> res.split(" - ")).map(res -> res[1].trim()).toList();
        for(int i =0; i < collect.size(); i++){
            UserDetail byEmployeeCode = employeeRepository.findByEmployeeCode(collect.get(i));
            if(byEmployeeCode == null){
                throw new AppException("ERO01", "Thành viên không tồn tại");
            }
            UserDetailProject userDetailProject = new UserDetailProject();
            userDetailProject.setProjectId(project.getId());
            userDetailProject.setUserDetailId(byEmployeeCode.getId());
            userDetailProjectRepository.save(userDetailProject);
        }
        log.info("// cập nhật dự án thành công!");
    }



}
