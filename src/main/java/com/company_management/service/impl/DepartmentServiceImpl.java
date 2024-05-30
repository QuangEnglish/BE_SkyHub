package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.dto.DepartmentDTO;
import com.company_management.model.entity.Department;
import com.company_management.model.mapper.DepartmentMapper;
import com.company_management.model.request.SearchDepartmentRequest;
import com.company_management.model.response.StatisticalContractResponse;
import com.company_management.model.response.StatisticalDepartmentResponse;
import com.company_management.model.response.StatisticalHeaderResponse;
import com.company_management.model.response.StatisticalTaskResponse;
import com.company_management.repository.ContractRepository;
import com.company_management.repository.DepartmentRepository;
import com.company_management.repository.EmployeeRepository;
import com.company_management.service.DepartmentService;
import com.company_management.utils.CommonUtils;
import com.company_management.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    private final ContractRepository contractRepository;

    private final DepartmentMapper departmentMapper;

    @Override
    public Page<DepartmentDTO> findAllPage(SearchDepartmentRequest searchDepartmentRequest, Pageable pageable) {
        String name = (searchDepartmentRequest.getName() != null &&
                !searchDepartmentRequest.getName().isEmpty()) ? searchDepartmentRequest.getName() : null;
        List<String> statuses = new ArrayList<>();
        if (searchDepartmentRequest.getStatus() == null) {
            statuses = List.of("0", "1");
        } else {
            statuses.add(searchDepartmentRequest.getStatus().toString());
        }
        Page<Department> pageDepartment = departmentRepository.findAllWithPagination(statuses, name, pageable);
        return pageDepartment.map(department -> {
            DepartmentDTO dto = new DepartmentDTO();
            dto.setDepartmentId(department.getId());
            dto.setDepartmentCode(department.getDepartmentCode());
            dto.setDepartmentName(department.getDepartmentName());
            dto.setStatus(department.getStatus());
            return dto;
        });
    }

    @Override
    @Transactional
    public void addDepartment(DepartmentDTO departmentDTO) {
        Optional<Department> existingDepartment = departmentRepository.findByCode(departmentDTO.getDepartmentCode());
        if (existingDepartment.isPresent()) {
            throw new AppException("ERO01", "Trùng mã phòng ban");
        }
        Department entity = departmentMapper.toEntity(departmentDTO);
        departmentRepository.save(entity);
    }

    @Override
    @Transactional
    public void editDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentDTO.getDepartmentId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy phòng ban")
        );
        if (!DataUtils.isNullOrEmpty(departmentDTO.getDepartmentName())) {
            department.setDepartmentName(departmentDTO.getDepartmentName());
        }
        if (!DataUtils.isNullOrEmpty(departmentDTO.getStatus())) {
            department.setStatus(departmentDTO.getStatus());
        }
        departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        log.debug("// Xóa phòng ban: {}", id);
        if (departmentRepository.deleteById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Không tìm thấy phòng ban!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDTO detailDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new AppException("ERR01", "Không tìm thấy phòng ban!"));
        return departmentMapper.toDto(department);
    }

    @Override
    public List<StatisticalDepartmentResponse> getStatisticalDepartment() {
        List<Object[]> statisticalDepartment = departmentRepository.getStatisticalDepartment();
        return DataUtils.convertListObjectsToClass(Arrays.asList("name", "value"),
                statisticalDepartment,
                StatisticalDepartmentResponse.class);
    }

    @Override
    public List<StatisticalTaskResponse> getStatisticalTask() {
        List<Object[]> statisticalTask = employeeRepository.getStatisticalTask();
        return DataUtils.convertListObjectsToClass(Arrays.asList("employeeName", "totalTask", "totalTaskDone", "percentage"),
                statisticalTask,
                StatisticalTaskResponse.class);
    }

    @Override
    public List<StatisticalContractResponse> getStatisticalContract() {
        List<Object[]> statisticalContract = contractRepository.getStatisticalContract();
        return DataUtils.convertListObjectsToClass(Arrays.asList("name", "value"),
                statisticalContract,
                StatisticalContractResponse.class);
    }

    @Override
    public StatisticalHeaderResponse getStatisticalHeader(Long employeeId) {
        StatisticalHeaderResponse statisticalHeaderResponse = new StatisticalHeaderResponse();
        try {
            statisticalHeaderResponse.setTotalEmployee(departmentRepository.getStatisticalTotalEmployee());
            statisticalHeaderResponse.setTotalBirthDayMonth(departmentRepository.getStatisticalTotalBirthDayMonth());
            statisticalHeaderResponse.setTotalLateWork(departmentRepository.getStatisticalTotalLateWork(employeeId));
            statisticalHeaderResponse.setTotalLeaveWork(departmentRepository.getStatisticalTotalLeaveWork(employeeId));
        } catch (Exception e) {
            e.printStackTrace(); // In stack trace để ghi nhận lỗi vào log
            statisticalHeaderResponse.setTotalEmployee(0L);
            statisticalHeaderResponse.setTotalBirthDayMonth(0L);
            statisticalHeaderResponse.setTotalLateWork(0L);
            statisticalHeaderResponse.setTotalLeaveWork(0L);
        }
        return statisticalHeaderResponse;
    }


}
