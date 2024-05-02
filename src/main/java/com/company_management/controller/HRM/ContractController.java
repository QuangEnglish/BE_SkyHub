package com.company_management.controller.HRM;

import com.company_management.common.ErrorCode;
import com.company_management.common.ObjectError;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.ContractDTO;
import com.company_management.model.dto.UserDetailContractDTO;
import com.company_management.service.ContractService;
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
@RequestMapping("/api/v1/contract")
@Slf4j
@RequiredArgsConstructor
public class ContractController {

    final ContractService contractService;

    @Value("${upload.path}")
    private String fileUpload;
    @PostMapping(value = "/create")
    public ResultResp<Object> create(@ModelAttribute("file") MultipartFile file,
                                     @ModelAttribute @Valid ContractDTO contractDTO
                                     ) {
        contractService.add(file, contractDTO);
        return ResultResp.success(ErrorCode.CREATED_OK);
    }

    @PostMapping(value = "/createForEmployee")
    public ResultResp<Object> createForEmployee(@RequestBody  @Valid UserDetailContractDTO userDetailContractDTO
    ) {
        contractService.addForEmployee(userDetailContractDTO);
        return ResultResp.success(ErrorCode.CREATED_OK);
    }

    @PostMapping(value = "/search")
    public ResultResp<Object> search(@RequestBody ContractDTO contractDTO, Pageable pageable) {
        return ResultResp.success(contractService.search(contractDTO, pageable));
    }
    @PostMapping(value = "/searchForEmployee")
    public ResultResp<Object> searchForEmployee(@RequestBody ContractDTO contractDTO, Pageable pageable) {
        return ResultResp.success(contractService.searchForEmployee(contractDTO, pageable));
    }

    @GetMapping(value = "/detail/{id}")
    public ResultResp<Object> detail(@PathVariable Long id) {
        return ResultResp.success(contractService.detail(id));
    }

    @PutMapping
    public ResultResp<Object> update(@ModelAttribute("file") MultipartFile file,
                                     @ModelAttribute @Valid ContractDTO contractDTO) {
        contractService.update(file, contractDTO);
        return ResultResp.success(null);
    }

    @PutMapping("/updateForEmployee")
    public ResultResp<Object> updateForEmployee(@RequestBody @Valid UserDetailContractDTO userDetailContractDTO) {
        contractService.updateForEmployee(userDetailContractDTO);
        return ResultResp.success(null);
    }

    @DeleteMapping("delete/{id}")
    public ResultResp<Object> delete(@PathVariable Long id) {
        contractService.deleteByIds(id);
        return ResultResp.success(null);
    }

    @DeleteMapping("deleteForEmployee/{id}")
    public ResultResp<Object> deleteForEmployee(@PathVariable Long id) {
        contractService.deleteForEmployeeByIds(id);
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
