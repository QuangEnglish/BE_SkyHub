package com.company_management.service.impl;

import com.company_management.common.Constants;
import com.company_management.exception.AppException;
import com.company_management.model.dto.UserDetailDTO;
import com.company_management.model.entity.Department;
import com.company_management.model.entity.Position;
import com.company_management.model.entity.UserCustom;
import com.company_management.model.entity.UserDetail;
import com.company_management.model.mapper.EmployeeMapper;
import com.company_management.model.request.SearchEmployeeRequest;
import com.company_management.model.response.DataPage;
import com.company_management.model.response.ExportPdfEmployeeResponse;
import com.company_management.model.response.UserDetailExcelResponse;
import com.company_management.repository.*;
import com.company_management.service.EmployeeService;
import com.company_management.utils.CommonUtils;
import com.company_management.utils.DataUtils;
import com.company_management.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final UserCustomRepository userCustomRepository;
    private final CustomBaseRepository customBaseRepository;

    private final EmployeeMapper employeeMapper;

    @Value("${upload.path}")
    private String fileUpload;


    private Map.Entry<StringBuilder, Map<String, Object>> getSql(SearchEmployeeRequest searchEmployeeRequest) {
        log.info("------------------------search Started--------------------------");
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT\n" +
                "    ud.id as employeeId,\n" +
                "    ud.employee_code as employeeCode,\n" +
                "    ud.employee_name as employeeName,\n" +
                "    ud.avatar as avatar,\n" +
                "    ud.phone as phone,\n" +
                "    ud.gender as gender,\n" +
                "    ud.birthday as birthday,\n" +
                "    ud.address as address,\n" +
                "    ucu.email as email,\n" +
                "    de.department_name as departmentName,\n" +
                "    de.id as departmentId, \n" +
                "    po.position_name as positionName, \n" +
                "    po.id as positionId \n" +
                "FROM user_detail ud\n" +
                "         LEFT JOIN user_custom ucu ON ud.id = ucu.user_detail_id\n" +
                "         LEFT JOIN department de ON ud.department_id = de.id\n" +
                "         LEFT JOIN position po ON ud.position_id = po.id\n" +
                "WHERE 1 = 1\n" +
                "AND ud.is_active = 1 or ud.is_active = 2 ");
        if (!DataUtils.isNullOrEmpty(searchEmployeeRequest.getEmployeeCode())) {
            sql.append(" AND ud.employee_code LIKE CONCAT('%', :employeeCode, '%') ");
            params.put("employeeCode", CommonUtils.getLikeCondition(searchEmployeeRequest.getEmployeeCode()));
        }
        if (!DataUtils.isNullOrEmpty(searchEmployeeRequest.getEmployeeName())) {
            sql.append(" AND ud.employeeName LIKE CONCAT('%', :employeeName, '%') ");
            params.put("employeeName", CommonUtils.getLikeCondition(searchEmployeeRequest.getEmployeeName()));
        }
        if (!DataUtils.isNullOrEmpty(searchEmployeeRequest.getEmployeeGender())) {
            sql.append(" AND ud.employeeGender = :employeeGender ");
            params.put("employeeGender", searchEmployeeRequest.getEmployeeGender());
        }
        if (!DataUtils.isNullOrEmpty(searchEmployeeRequest.getDepartmentId())) {
            sql.append(" AND de.id = :departmentId ");
            params.put("departmentId", searchEmployeeRequest.getDepartmentId());
        }
        return new AbstractMap.SimpleEntry<>(sql, params);
    }

    @Override
    public DataPage<UserDetailDTO> search(SearchEmployeeRequest searchEmployeeRequest, Pageable pageable) {
        Map.Entry<StringBuilder, Map<String, Object>> entry = getSql(searchEmployeeRequest);
        StringBuilder sql = entry.getKey();
        Map<String, Object> params = entry.getValue();
        if (pageable.getSort().isSorted()) {
            sql.append(" ORDER BY ud." + pageable.getSort().toString().replace(":", " "));
            log.info(sql.toString());
        }
        List<Object[]> listObj = customBaseRepository.queryHasParam(sql.toString(), params);
        List<UserDetailDTO> lstDTO = DataUtils.convertListObjectsToClass(
                Arrays.asList("id", "employeeCode", "employeeName", "avatar", "phone", "gender",
                        "birthday", "address", "email", "departmentName", "departmentId", "positionName", "positionId"), listObj, UserDetailDTO.class);
        lstDTO.forEach(dto -> {
            dto.setGenderName(dto.getGender() != 1 ? "Nữ" : "Nam");
        });
        DataPage<UserDetailDTO> dataPage = getUserDetailDTODataPage(pageable, lstDTO);
        log.info("------------------------search Finished--------------------------");
        return dataPage;
    }

    private static DataPage<UserDetailDTO> getUserDetailDTODataPage(Pageable pageable, List<UserDetailDTO> lstDTO) {
        DataPage<UserDetailDTO> dataPage = new DataPage<>();
        if (pageable.isPaged() && !lstDTO.isEmpty()) {
            int count = lstDTO.size();
            dataPage.setDataCount(count);
            dataPage.setPageSize(pageable.getPageSize());
            int pageCount = pageable.getPageSize() == 0 ? 1 : (int) Math.ceil((double) count / (double) pageable.getPageSize());
            dataPage.setPageCount(pageCount);
            dataPage.setPageIndex(pageable.getPageNumber());
        }
        dataPage.setData(lstDTO);
        return dataPage;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetailDTO detailEmployee(Long id) {
        UserDetail userDetail = employeeRepository.findById(id).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy nhân viên này!"));
        UserDetailDTO detailDTO = employeeMapper.toDto(userDetail);
        detailDTO.setDepartmentId(userDetail.getDepartmentId());
        detailDTO.setPositionId(userDetail.getPositionId());
        if (userDetail.getDepartmentId() != null) {
            Department department = departmentRepository.findById(userDetail.getDepartmentId()).orElseThrow(
                    () -> new AppException("ERR01", "Không tìm thấy phòng ban này!"));
            detailDTO.setDepartmentName(department.getDepartmentName());
        }
        if (userDetail.getPositionId() != null) {
            Position position = positionRepository.findById(userDetail.getPositionId()).orElseThrow(
                    () -> new AppException("ERR01", "Không tìm thấy chức vụ này!"));
            detailDTO.setPositionName(position.getPositionName());
        }
        Optional<UserCustom> userCustom = userCustomRepository.findByUserDetailId(userDetail.getId());
        userCustom.ifPresent(custom -> detailDTO.setEmail(custom.getEmail()));
        return detailDTO;
    }

    @Override
    @Transactional
    public void createEmployee(MultipartFile avatarFile, UserDetailDTO userDetailDTO) throws IOException {
        log.debug("// Create Employee");
        UserDetail byEmployeeCode = employeeRepository.findByEmployeeCode(userDetailDTO.getEmployeeCode());
        if (byEmployeeCode != null) {
            throw new AppException("ERO01", "Mã nhân viên đã tồn tại");
        }
        Department department = new Department();
        if (userDetailDTO.getDepartmentId() != null) {
            department = departmentRepository.findById(userDetailDTO.getDepartmentId()).orElseThrow(() ->
                    new AppException("ERO01", "Phòng ban không hoạt động"));
        }
        Position position = new Position();
        if (userDetailDTO.getPositionId() != null) {
            position = positionRepository.findById(userDetailDTO.getPositionId()).orElseThrow(() ->
                    new AppException("ERO01", "Chức vụ không hoạt động"));
        }
        UserDetail userDetail = employeeMapper.toEntity(userDetailDTO);
        userDetail.setEmployeeCode(userDetail.getEmployeeCode().toUpperCase());
        userDetail.setDepartmentId(department.getId());
        userDetail.setPositionId(position.getId());
        userDetail.setIsActive(Constants.STATUS_ACTIVE_INT);

        //upload file ảnh
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(avatarFile.getOriginalFilename()));
        if (fileName.contains("..")) {
            log.debug("File upload không tồn tại!");
            throw new AppException("ERO01", "Tên tệp tin không hợp lệ");
        }
        Path filePath = Paths.get(this.fileUpload + fileName);
        Files.copy(avatarFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        userDetail.setAvatar(fileName);

        employeeRepository.save(userDetail);
        log.info("// Lưu nhân viên thành công!");
    }

    @Override
    @Transactional
    public void updateEmployee(MultipartFile avatarFile, UserDetailDTO userDetailDTO) throws IOException {
        UserDetail userDetail = employeeRepository.findById(userDetailDTO.getId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy nhân viên này!"));
        log.debug("// Update Employee");
        if (userDetailDTO.getEmployeeCode() != null && !userDetailDTO.getEmployeeCode().equals(userDetail.getEmployeeCode())) {
            UserDetail byEmployeeCode = employeeRepository.findByEmployeeCode(userDetailDTO.getEmployeeCode());
            if (byEmployeeCode != null) {
                throw new AppException("ERO01", "Mã nhân viên đã tồn tại");
            }
            userDetail.setEmployeeCode(userDetailDTO.getEmployeeCode().toUpperCase());
        }
        if (userDetailDTO.getDepartmentId() != null) {
            Department department = new Department();
            if (userDetailDTO.getDepartmentId() != null) {
                department = departmentRepository.findById(userDetailDTO.getDepartmentId()).orElseThrow(() ->
                        new AppException("ERO01", "Phòng ban không tồn tại"));
            }
            userDetail.setDepartmentId(department.getId());
        }
        if (!DataUtils.isNullOrEmpty(userDetailDTO.getEmployeeName())) {
            userDetail.setEmployeeName(userDetailDTO.getEmployeeName());
        }
        if (!DataUtils.isNullOrEmpty(userDetailDTO.getDepartmentId())) {
            userDetail.setDepartmentId(userDetailDTO.getDepartmentId());
        }
        if (!DataUtils.isNullOrEmpty(userDetailDTO.getPositionId())) {
            userDetail.setPositionId(userDetailDTO.getPositionId());
        }
        if (!DataUtils.isNullOrEmpty(userDetailDTO.getGender())) {
            userDetail.setGender(userDetailDTO.getGender());
        }
        if (!DataUtils.isNullOrEmpty(userDetailDTO.getAddress())) {
            userDetail.setAddress(userDetailDTO.getAddress());
        }
        if (!DataUtils.isNullOrEmpty(userDetailDTO.getBirthday())) {
            userDetail.setBirthday(userDetailDTO.getBirthday());
        }
        if (!DataUtils.isNullOrEmpty(userDetailDTO.getIsActive())) {
            userDetail.setIsActive(userDetailDTO.getIsActive());
        }
        //upload file ảnh
        if (avatarFile != null && avatarFile.getOriginalFilename() != null) {
            try {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(avatarFile.getOriginalFilename()));
                if (fileName.contains("..")) {
                    log.debug("File upload không tồn tại!");
                    throw new AppException("ERO01", "Tên tệp tin không hợp lệ");
                }
                Path filePath = Paths.get(this.fileUpload + fileName);
                Files.copy(avatarFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                if (!userDetail.getAvatar().equals(filePath.toString())) {
                    userDetail.setAvatar(fileName);
                }
            } catch (NullPointerException e) {
                log.error("File ảnh là null.", e);
                throw new AppException("ERO02", "File ảnh là null");
            } catch (IOException e) {
                log.error("Lỗi xảy ra khi xử lý file ảnh", e);
                throw new AppException("ERO02", "Lỗi xảy ra khi xử lý file ảnh");
            }
        }
//        userDetail.setUpdatedUser(CommonUtils.getUserLoginName());
        employeeRepository.save(userDetail);
        log.info("// Lưu nhân viên thành công!");
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        log.debug("// Xóa nhân viên: {}", id);
        if (employeeRepository.deleteById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Không tìm thấy nhân viên!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ByteArrayInputStream exportExcel(SearchEmployeeRequest searchEmployeeRequest, Pageable pageable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream in = CommonUtils.getInputStreamByFileName("export-employee-template.xlsx")) {
            Map.Entry<StringBuilder, Map<String, Object>> entry = getSqlExcel(searchEmployeeRequest);
            StringBuilder sql = entry.getKey();
            Map<String, Object> params = entry.getValue();
            List<Object[]> listObj = customBaseRepository.queryHasParam(sql.toString(), params);
            List<UserDetailExcelResponse> userDetailDTOS = DataUtils.convertListObjectsToClass(
                    Arrays.asList("id", "employeeCode", "employeeName", "avatar", "gender",
                            "birthday", "address", "email", "departmentName", "departmentId"),
                    listObj,
                    UserDetailExcelResponse.class);
            AtomicInteger index = new AtomicInteger();
            for (UserDetailExcelResponse item : userDetailDTOS) {
                item.setIndex(index.incrementAndGet());
                if (item.getGender() == 1) {
                    item.setGenderName("Nam");
                } else {
                    item.setGenderName("Nữ");
                }
            }
            Map<String, Object> beans = new HashMap<>();
            beans.put("posLst", userDetailDTOS);
            beans.put("date", DateTimeUtils.convertDateToStringByPattern(new Date(), "dd/MM/yyyy HH:mm:ss"));
            beans.put("total", userDetailDTOS.size());
            XLSTransformer transformer = new XLSTransformer();
            Workbook workbook = transformer.transformXLS(in, beans);
            workbook.write(byteArrayOutputStream);
            byte[] exportInputStream = byteArrayOutputStream.toByteArray();
            return new ByteArrayInputStream(exportInputStream);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new AppException("ERR01", "Xuất file excel bị lỗi");
        }
    }

    @Override
    @Transactional
    public void updateEmployeeStatus() {
        List<UserDetail> employees = employeeRepository.findAll();
        for (UserDetail employee : employees) {
            employee.setIsActive(1);
        }
        employeeRepository.saveAll(employees);
    }

    private Map.Entry<StringBuilder, Map<String, Object>> getSqlExcel(SearchEmployeeRequest searchEmployeeRequest) {
        log.info("------------------------search Started--------------------------");
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT\n" +
                "    ud.id as employeeId,\n" +
                "    ud.employee_code as employeeCode,\n" +
                "    ud.employee_name as employeeName,\n" +
                "    ud.avatar as avatar,\n" +
                "    ud.gender as gender,\n" +
                "    ud.birthday as birthday,\n" +
                "    ud.address as address,\n" +
                "    ucu.email as email,\n" +
                "    de.department_name as departmentName,\n" +
                "    de.id as departmentId \n" +
                "FROM user_detail ud\n" +
                "         LEFT JOIN user_custom ucu ON ud.id = ucu.user_detail_id\n" +
                "         LEFT JOIN department de ON ud.department_id = de.id\n" +
                "WHERE 1 = 1\n" +
                "AND ud.is_active = 1 ");
        if (!DataUtils.isNullOrEmpty(searchEmployeeRequest.getEmployeeCode())) {
            sql.append(" AND ud.employee_code LIKE CONCAT('%', :employeeCode, '%') ");
            params.put("employeeCode", CommonUtils.getLikeCondition(searchEmployeeRequest.getEmployeeCode()));
        }
        if (!DataUtils.isNullOrEmpty(searchEmployeeRequest.getEmployeeName())) {
            sql.append(" AND ud.employeeName LIKE CONCAT('%', :employeeName, '%') ");
            params.put("employeeName", CommonUtils.getLikeCondition(searchEmployeeRequest.getEmployeeName()));
        }
        if (!DataUtils.isNullOrEmpty(searchEmployeeRequest.getEmployeeGender())) {
            sql.append(" AND ud.employeeGender = :employeeGender ");
            params.put("employeeGender", searchEmployeeRequest.getEmployeeGender());
        }
        if (!DataUtils.isNullOrEmpty(searchEmployeeRequest.getDepartmentId())) {
            sql.append(" AND de.id = :departmentId ");
            params.put("departmentId", searchEmployeeRequest.getDepartmentId());
        }
        return new AbstractMap.SimpleEntry<>(sql, params);
    }

    @Override
    public ExportPdfEmployeeResponse exportPdf(Long userDetailId) {
        return null;
    }

}
