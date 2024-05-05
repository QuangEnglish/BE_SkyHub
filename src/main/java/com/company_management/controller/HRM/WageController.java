package com.company_management.controller.HRM;

import com.company_management.common.ErrorCode;
import com.company_management.common.ObjectError;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.UserDetailWageDTO;
import com.company_management.model.dto.WageDTO;
import com.company_management.service.WageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/wage")
@Slf4j
@RequiredArgsConstructor
public class WageController {

    private final WageService wageService;

    @Value("${upload.path}")
    private String fileUpload;
    @PostMapping(value = "/create")
    public ResultResp<Object> create(@ModelAttribute("file") MultipartFile file,
                                     @ModelAttribute @Valid WageDTO wageDTO
                                     ) {
        wageService.add(file, wageDTO);
        return ResultResp.success(ErrorCode.CREATED_OK);
    }

    @PostMapping(value = "/createForEmployee")
    public ResultResp<Object> createForEmployee(@RequestBody  @Valid UserDetailWageDTO userDetailWageDTO
    ) {
        wageService.addForEmployee(userDetailWageDTO);
        return ResultResp.success(ErrorCode.CREATED_OK);
    }

    @PostMapping(value = "/search")
    public ResultResp<Object> search(@RequestBody WageDTO wageDTO, Pageable pageable) {
        return ResultResp.success(wageService.search(wageDTO, pageable));
    }
    @PostMapping(value = "/searchForEmployee")
    public ResultResp<Object> searchForEmployee(@RequestBody WageDTO wageDTO, Pageable pageable) {
        return ResultResp.success(wageService.searchForEmployee(wageDTO, pageable));
    }

    @GetMapping(value = "/detail/{id}")
    public ResultResp<Object> detail(@PathVariable Long id) {
        return ResultResp.success(wageService.detail(id));
    }

    @PutMapping
    public ResultResp<Object> update(@ModelAttribute("file") MultipartFile file,
                                     @ModelAttribute @Valid WageDTO wageDTO) {
        wageService.update(file, wageDTO);
        return ResultResp.success(null);
    }

    @PutMapping("/updateForEmployee")
    public ResultResp<Object> updateForEmployee(@RequestBody @Valid UserDetailWageDTO userDetailWageDTO) {
        wageService.updateForEmployee(userDetailWageDTO);
        return ResultResp.success(null);
    }

    @DeleteMapping("delete/{id}")
    public ResultResp<Object> delete(@PathVariable Long id) {
        wageService.deleteByIds(id);
        return ResultResp.success(null);
    }

    @DeleteMapping("deleteForEmployee/{id}")
    public ResultResp<Object> deleteForEmployee(@PathVariable Long id) {
        wageService.deleteForEmployeeByIds(id);
        return ResultResp.success(null);
    }

    @PostMapping("/download")
    public ResponseEntity<Object> downloadWordFile(@RequestParam("fileName") String fileName) {
        try {
            // Đọc tệp Word từ máy
            Path filePath = Paths.get(this.fileUpload + fileName);
            byte[] fileContent = Files.readAllBytes(filePath);
            ByteArrayResource resource = new ByteArrayResource(fileContent);

            // Thiết lập các header cho phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(fileContent.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException ex) {
            log.error("{Error export file}: " + ex.getMessage());
            return ResultResp.badRequest(new ObjectError(ErrorCode.SELECT_FAIL.getCode(), ex.getMessage()));
        }
    }


}
