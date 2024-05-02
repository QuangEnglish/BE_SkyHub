package com.company_management.controller.HRM;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.UserDetailDTO;
import com.company_management.model.request.SearchEmployeeRequest;
import com.company_management.service.EmployeeService;
import com.company_management.utils.CommonUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Value("${upload.path}")
    private String fileUpload;

    @PostMapping("/create")
    public ResultResp<Object> addEmployee(@ModelAttribute("avatarFile") MultipartFile avatarFile,
                                          @ModelAttribute @Valid UserDetailDTO userDetailDTO) throws IOException {
        employeeService.createEmployee(avatarFile, userDetailDTO);
        return ResultResp.success(ErrorCode.CREATED_OK, null);
    }

    @PostMapping("/search")
    public ResultResp<Object> searchEmployee(@RequestBody SearchEmployeeRequest searchEmployeeRequest,
                                             Pageable pageable){
        return ResultResp.success(employeeService.search(searchEmployeeRequest, pageable));
    }

    @GetMapping("/detail/{id}")
    public ResultResp<Object> getByIdEmployee(@PathVariable("id") Long id) {
        return ResultResp.success(employeeService.detailEmployee(id));
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
    public ResultResp<Object> updateEmployee(@ModelAttribute("avatarFile") MultipartFile avatarFile,
                                             @ModelAttribute UserDetailDTO userDetailDTO) throws IOException {
        employeeService.updateEmployee(avatarFile, userDetailDTO);
        return ResultResp.success(ErrorCode.UPDATED_OK, null);
    }

    @DeleteMapping("/delete/{id}")
    public ResultResp<Object> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return ResultResp.success(ErrorCode.DELETED_OK, null);
    }

    @PostMapping(value = "/export")
    public ResponseEntity<Object> exportExcel(@RequestBody SearchEmployeeRequest searchEmployeeRequest, Pageable pageable) {
        ByteArrayInputStream result = employeeService.exportExcel(searchEmployeeRequest, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = CommonUtils.getFileNameReportUpdate("EXPORT_EMPLOYEE");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<>(new InputStreamResource(result), headers, HttpStatus.OK);
    }

}
