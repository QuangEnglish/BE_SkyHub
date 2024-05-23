package com.company_management.controller.quanlycongviec;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.TaskDTO;
import com.company_management.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @Value("${upload.path}")
    private String fileUpload;

    @PostMapping("/create")
    public ResultResp<Object> addTask(@RequestBody @Valid TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
        return ResultResp.success(ErrorCode.CREATED_OK, null);
    }

    @PostMapping("/search")
    public ResultResp<Object> getAllTask(@RequestParam(value = "userDetailId", required = false) Long userDetailId){
        if (userDetailId != null) {
            return ResultResp.success(taskService.listTaskFindById(userDetailId));
        }
        return ResultResp.success(taskService.listTaskFindAll());
    }

    @GetMapping("/detail/{id}")
    public ResultResp<Object> getByIdTask(@PathVariable("id") Long id) {
        return ResultResp.success(taskService.detailTask(id));
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
    public ResultResp<Object> updateTask(@RequestBody TaskDTO taskDTO) {
        taskService.updateTask(taskDTO);
        return ResultResp.success(ErrorCode.UPDATED_OK, null);
    }



}
