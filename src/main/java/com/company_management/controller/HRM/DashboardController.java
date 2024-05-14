package com.company_management.controller.HRM;

import com.company_management.common.ErrorCode;
import com.company_management.common.ResultResp;
import com.company_management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@Slf4j
@RequiredArgsConstructor
public class DashboardController {

    private final DepartmentService departmentService;

    @GetMapping("/statisticalDepartment/")
    public ResultResp<Object> getStatisticalDepartment(){
        return ResultResp.success(ErrorCode.CREATED_OK, departmentService.getStatisticalDepartment());
    }

    @GetMapping("/statisticalHeader/{employeeId}")
    public ResultResp<Object> getStatisticalDepartment(@PathVariable("employeeId") Long employeeId){
        return ResultResp.success(ErrorCode.CREATED_OK, departmentService.getStatisticalHeader(employeeId));
    }

    @GetMapping("/statisticalContract/")
    public ResultResp<Object> getStatisticalContract(){
        return ResultResp.success(ErrorCode.CREATED_OK, departmentService.getStatisticalContract());
    }


//    @PostMapping(value = "/create")
//    public ResultResp<Object> create(@ModelAttribute("file") MultipartFile file,
//                                     @ModelAttribute @Valid ContractDTO contractDTO
//                                     ) {
//        contractService.add(file, contractDTO);
//        return ResultResp.success(ErrorCode.CREATED_OK);
//    }
//
//    @PostMapping(value = "/createForEmployee")
//    public ResultResp<Object> createForEmployee(@RequestBody  @Valid UserDetailContractDTO userDetailContractDTO
//    ) {
//        contractService.addForEmployee(userDetailContractDTO);
//        return ResultResp.success(ErrorCode.CREATED_OK);
//    }
//
//    @PostMapping(value = "/search")
//    public ResultResp<Object> search(@RequestBody ContractDTO contractDTO, Pageable pageable) {
//        return ResultResp.success(contractService.search(contractDTO, pageable));
//    }
//    @PostMapping(value = "/searchForEmployee")
//    public ResultResp<Object> searchForEmployee(@RequestBody ContractDTO contractDTO, Pageable pageable) {
//        return ResultResp.success(contractService.searchForEmployee(contractDTO, pageable));
//    }
//
//    @GetMapping(value = "/detail/{id}")
//    public ResultResp<Object> detail(@PathVariable Long id) {
//        return ResultResp.success(contractService.detail(id));
//    }
//
//    @PutMapping
//    public ResultResp<Object> update(@ModelAttribute("file") MultipartFile file,
//                                     @ModelAttribute @Valid ContractDTO contractDTO) {
//        contractService.update(file, contractDTO);
//        return ResultResp.success(null);
//    }
//
//    @PutMapping("/updateForEmployee")
//    public ResultResp<Object> updateForEmployee(@RequestBody @Valid UserDetailContractDTO userDetailContractDTO) {
//        contractService.updateForEmployee(userDetailContractDTO);
//        return ResultResp.success(null);
//    }
//
//    @DeleteMapping("delete/{id}")
//    public ResultResp<Object> delete(@PathVariable Long id) {
//        contractService.deleteByIds(id);
//        return ResultResp.success(null);
//    }




}
