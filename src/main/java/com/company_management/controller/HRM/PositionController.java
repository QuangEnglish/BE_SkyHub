package com.company_management.controller.HRM;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.PositionDTO;
import com.company_management.model.request.SearchPositionRequest;
import com.company_management.service.PositionService;
import com.company_management.utils.CommonUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/position")
public class PositionController {

    private final PositionService positionService;

    @PostMapping("/search")
    public ResultResp<Object> searchPosition(@RequestBody SearchPositionRequest searchPositionRequest,
                                             Pageable pageable) {
        return ResultResp.success(positionService.search(searchPositionRequest, pageable));
    }

    @PostMapping
    public ResultResp<Object> createPosition(@Valid @RequestBody PositionDTO positionDTO) {
        positionService.createOrUpdate(positionDTO);
        return ResultResp.success(ErrorCode.CREATED_OK, null);
    }

    @GetMapping("/detail/{id}")
    public ResultResp<Object> getByIdPosition(@PathVariable("id") Long id) {
        return ResultResp.success(ErrorCode.CREATED_OK, positionService.detailPosition(id));
    }

    @PutMapping
    public ResultResp<Object> updatePosition(@Valid @RequestBody PositionDTO positionDTO) {
        positionService.createOrUpdate(positionDTO);
        return ResultResp.success(ErrorCode.UPDATED_OK, null);
    }

    @DeleteMapping("/delete/{id}")
    public ResultResp<Object> deletePosition(@PathVariable("id") Long id) {
        positionService.deletePosition(id);
        return ResultResp.success(ErrorCode.DELETED_OK, null);
    }

    @PostMapping(value = "/export")
    public ResponseEntity<Object> exportExcel(@RequestBody SearchPositionRequest searchPositionRequest, Pageable pageable) {
        ByteArrayInputStream result = positionService.exportExcel(searchPositionRequest, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = CommonUtils.getFileNameReportUpdate("EXPORT_POSITION");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<>(new InputStreamResource(result), headers, HttpStatus.OK);
    }

}
