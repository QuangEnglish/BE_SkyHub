package com.company_management.controller;

import com.company_management.model.dto.WageDTO;
import com.company_management.model.response.BasicResponse;
import com.company_management.model.response.PageResponse;
import com.company_management.service.WageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/v1/Wage")
@RequiredArgsConstructor
public class WageController {

    public final WageService wageService;
    @PostMapping("/getAllWage")
    ResponseEntity<PageResponse<WageDTO>> getAllWage(WageDTO wageDTO, Pageable pageable) {
        return ResponseEntity.ok(wageService.getAllWage(wageDTO, pageable));
    }

    @PutMapping("/addNewWage")
    ResponseEntity<BasicResponse> addWage(WageDTO wageDTO) {
        return ResponseEntity.ok(wageService.addNewWage(wageDTO));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        wageService.deleteWage(id);
        String successMessage = "Huỷ thành công với ID: " + id;
        return ResponseEntity.ok(successMessage);
    }

    @PostMapping("/exportExcel")
    public ResponseEntity<?> exportExcel(@Valid @RequestBody WageDTO wageDTO) throws Exception {
        ByteArrayInputStream byteArrayInputStream = wageService.exportExcel(wageDTO);
        InputStreamResource resource = new InputStreamResource(byteArrayInputStream);
        byte[] bytes = IOUtils.toByteArray(resource.getInputStream());
        byteArrayInputStream.read(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "wage_template_excel.xlsx")
                .contentLength(bytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(bytes));
    }
}
