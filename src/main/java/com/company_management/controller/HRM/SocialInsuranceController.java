package com.company_management.controller.HRM;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.model.dto.SocialInsuranceDTO;
import com.company_management.service.SocialInsuranceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/socialinsurance")
@Slf4j
@RequiredArgsConstructor
public class SocialInsuranceController {

    final SocialInsuranceService socialInsuranceService;

    @PostMapping(value = "/create")
    public ResultResp<Object> create(@RequestBody @Valid SocialInsuranceDTO socialInsuranceDTO) {
        socialInsuranceService.add(socialInsuranceDTO);
        return ResultResp.success(ErrorCode.CREATED_OK);
    }

    @PostMapping(value = "/search/{userDetailId}")
    public ResultResp<Object> search(@PathVariable("userDetailId") Long userDetailId, Pageable pageable) {
        return ResultResp.success(socialInsuranceService.search(userDetailId, pageable));
    }

    @GetMapping(value = "/detail/{id}")
    public ResultResp<Object> detail(@PathVariable Long id) {
        return ResultResp.success(socialInsuranceService.detail(id));
    }

    @PutMapping
    public ResultResp<Object> update(@RequestBody @Valid SocialInsuranceDTO socialInsuranceDTO) {
        socialInsuranceService.update(socialInsuranceDTO);
        return ResultResp.success(null);
    }

    @DeleteMapping("/{id}")
    public ResultResp<Object> delete(@PathVariable Long id) {
        socialInsuranceService.deleteByIds(id);
        return ResultResp.success(null);
    }


}
