package com.company_management.controller.quanlycongviec;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.ProjectDTO;
import com.company_management.model.entity.Project;
import com.company_management.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @Value("${upload.path}")
    private String fileUpload;

    @PostMapping("/create")
    public ResultResp<Object> addProject(@ModelAttribute("avatarFile") MultipartFile avatarFile,
                                          @ModelAttribute @Valid ProjectDTO projectDTO) throws IOException {
        projectService.createProject(avatarFile, projectDTO);
        return ResultResp.success(ErrorCode.CREATED_OK, null);
    }

    @PostMapping("/search")
    public ResultResp<Object> getAllProject(@RequestParam(required = false) Long id){
        if (id != null) {
            Optional<Project> project = projectService.projectFindById(id);
            if(project.isPresent()){
                return ResultResp.success(project);
            }
            return ResultResp.success(null);
        }
        return ResultResp.success(projectService.listProjectFindAll());
    }

    @GetMapping("/detail/{id}")
    public ResultResp<Object> getByIdProject(@PathVariable("id") Long id) {
        return ResultResp.success(projectService.detailProject(id));
    }

    @GetMapping("/{urlAvatar}")
    public ResponseEntity<byte[]> getImageByUrl(@PathVariable("urlAvatar") String urlAvatar) throws IOException {
        if (urlAvatar != null) {
            Path imagePath = Paths.get(fileUpload + urlAvatar);
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResultResp<Object> updateProject(@ModelAttribute("avatarFile") MultipartFile avatarFile,
                                            @ModelAttribute ProjectDTO projectDTO) throws IOException {
        projectService.updateProject(avatarFile, projectDTO);
        return ResultResp.success(ErrorCode.UPDATED_OK, null);
    }



}
