package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.dto.UserDetailWageDTO;
import com.company_management.model.dto.WageDTO;
import com.company_management.model.entity.UserDetail;
import com.company_management.model.entity.UserDetailWage;
import com.company_management.model.entity.Wage;
import com.company_management.model.response.DataPage;
import com.company_management.model.response.WageResponse;
import com.company_management.repository.CustomBaseRepository;
import com.company_management.repository.UserDetailRepository;
import com.company_management.repository.UserDetailWageRepository;
import com.company_management.repository.WageRepository;
import com.company_management.service.WageService;
import com.company_management.utils.CommonUtils;
import com.company_management.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class WageServiceImpl implements WageService {

    private final WageRepository wageRepository;
    private final UserDetailRepository userDetailRepository;

    private final CustomBaseRepository customBaseRepository;

    private final UserDetailWageRepository userDetailWageRepository;

    @Value("${upload.path}")
    private String fileUpload;

    @Override
    @Transactional(readOnly = true)
    public DataPage<WageDTO> searchForEmployee(WageDTO wageDTO, Pageable pageable) {
        return wageRepository.search(wageDTO, pageable);
    }

    @Override
    public DataPage<WageDTO> search(WageDTO wageDTO, Pageable pageable) {
        Pageable paging = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        Page<Object[]> wagePage = wageRepository.findAllWithPagination(
                DataUtils.isNullOrEmpty(wageDTO.getWageName()) ? null : wageDTO.getWageName().trim().toLowerCase(),
                DataUtils.isNullOrEmpty(wageDTO.getCreatedDate()) ? null : wageDTO.getCreatedDate(),
                paging);
        List<WageDTO> wageDTOList = DataUtils.convertListObjectsToClass(new ArrayList<>(Arrays.asList(
                        "wageId", "wageName", "wageBase", "wageDescription", "attachFile", "createdDate")),
                wagePage.getContent(),
                WageDTO.class);
        DataPage<WageDTO> dataPage = new DataPage<>();
        dataPage.setData(wageDTOList);
        dataPage.setPageIndex(wagePage.getPageable().getPageNumber());
        dataPage.setPageSize(wagePage.getPageable().getPageSize());
        dataPage.setPageCount(wagePage.getTotalPages());
        dataPage.setDataCount(wagePage.getTotalElements());
        return dataPage;
    }

    @Override
    @Transactional(readOnly = true)
    public WageResponse detail(Long id) {
        if (null == id) {
            log.error("ID is null");
            throw new AppException("ID is null", "Argument Invalid");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder("select\n" +
                "    ctr.wage_code as wageCode,\n" +
                "    ctr.wage_type as wageType,\n" +
                "    ctr.attachfile as attachfile,\n" +
                "    udc.wage_id as wageId,\n" +
                "    udc.active_date as activeDate,\n" +
                "    udc.expired_date as expiredDate,\n" +
                "    udc.sign_date as signDate\n" +
                "from wage ctr\n" +
                "left join user_detail_wage udc\n" +
                "on ctr.id = udc.wage_id\n" +
                "where and ctr.is_active = 1\n" +
                "and ctr.id = :id ");
        params.put("id", id);

        List<Object[]> listObj = customBaseRepository.queryHasParam(sql.toString(), params);
        List<WageResponse> wageDTOS = DataUtils.convertListObjectsToClass(
                Arrays.asList("wageId", "wageCode", "isActive", "wageType", "attachFile", "signDate", "activeDate", "expiredDate"),
                listObj,
                WageResponse.class);
        List<UserDetailWage> byWageId = userDetailWageRepository.findByWageId(id);
        List<Long> collect = byWageId.stream().map(UserDetailWage::getWageId).collect(Collectors.toList());
        wageDTOS.get(0).setUserDetailId(collect);
        return wageDTOS.get(0);
    }

    @Override
    @Transactional
    public void update(MultipartFile file, WageDTO wageDTO) {
        Wage wage = wageRepository.findById(wageDTO.getWageId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm mã hợp đồng này!"));
        log.debug("// Update wage");
        if (!DataUtils.isNullOrEmpty(wageDTO.getWageName())) {
            wage.setWageName(wageDTO.getWageName());
        }
        if (!DataUtils.isNullOrEmpty(wageDTO.getWageBase())) {
            wage.setWageBase(wageDTO.getWageBase());
        }
        if (!DataUtils.isNullOrEmpty(wageDTO.getWageDescription())) {
            wage.setWageDescription(wageDTO.getWageDescription());
        }
        if (!DataUtils.isNullOrEmpty(wageDTO.getIsActive())) {
            wage.setIsActive(wageDTO.getIsActive());
        }
        //upload file word
        if (file != null && file.getOriginalFilename() != null) {
            try {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                if (fileName.contains("..")) {
                    log.debug("File upload không tồn tại!");
                    throw new AppException("ERO01", "Tên tệp tin không hợp lệ");
                }
                Path filePath = Paths.get(this.fileUpload + fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                if (!wage.getAttachFile().equals(filePath.toString())) {
                    wage.setAttachFile(fileName);
                }
            } catch (NullPointerException e) {
                log.error("File là null.", e);
                throw new AppException("ERO02", "File là null");
            } catch (IOException e) {
                log.error("Lỗi xảy ra khi xử lý file", e);
                throw new AppException("ERO02", "Lỗi xảy ra khi xử lý file");
            }
        }
        wageRepository.save(wage);
        log.info("// Lưu thông tin phụ cấp thành công!");
    }

    @Override
    @Transactional
    public void updateForEmployee(UserDetailWageDTO userDetailWageDTO) {
        UserDetailWage userDetailWage = userDetailWageRepository.findById(userDetailWageDTO.getId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy mã phụ cấp cho nhân viên này!"));
        log.debug("// Update wage");
        if(!DataUtils.isNullOrEmpty(userDetailWageDTO.getWageId())){
            Wage wage = wageRepository.findById(userDetailWageDTO.getWageId()).orElseThrow(
                    () -> new AppException("ERR01", "Không tìm thấy tên phụ cấp cho nhân viên này!"));
            userDetailWage.setWageId(wage.getId());
        }
        if (!DataUtils.isNullOrEmpty(userDetailWageDTO.getEmpSign())) {
            userDetailWage.setEmpSign(userDetailWageDTO.getEmpSign());
        }
        if (!DataUtils.isNullOrEmpty(userDetailWageDTO.getLicenseDate())) {
            userDetailWage.setLicenseDate(userDetailWageDTO.getLicenseDate());
        }
        userDetailWageRepository.save(userDetailWage);
        log.info("// Lưu thông tin phụ cấp thành công!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MultipartFile file, WageDTO wageDTO) {
        log.info("------------------------saveData Started--------------------------");
        if (null == wageDTO.getWageId()) {
            Wage wage = new Wage();
            wage.setWageName(wageDTO.getWageName());
            wage.setWageBase(wageDTO.getWageBase());
            wage.setWageDescription(wageDTO.getWageDescription());
            if (file != null && file.getOriginalFilename() != null) {
                try {
                    // Lưu tệp Word vào máy
                    String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                    Path filePath = Paths.get(this.fileUpload + fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    wage.setAttachFile(fileName);
                } catch (IOException e) {
                    log.error("Lỗi xảy ra khi xử lý file", e);
                    throw new AppException("ERO02", "Lỗi xảy ra khi xử lý file");
                }
            }
            wageRepository.save(wage);
        }
        log.info("------------------------saveData Finished--------------------------");
    }

    @Override
    @Transactional
    public void addForEmployee(UserDetailWageDTO userDetailWageDTO) {
        Wage wage = wageRepository.findById(userDetailWageDTO.getWageId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm mã phụ cấp này!")
        );
        UserDetail userDetail = userDetailRepository.findById(userDetailWageDTO.getUserDetailId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm mã nhân viên này!")
        );
        UserDetailWage userDetailWage = new UserDetailWage();
        userDetailWage.setWageId(wage.getId());
        userDetailWage.setUserDetailId(userDetail.getId());
        userDetailWage.setEmpSign(userDetailWageDTO.getEmpSign());
        userDetailWage.setLicenseDate(userDetailWageDTO.getLicenseDate());
        userDetailWageRepository.save(userDetailWage);
    }

    @Override
    @Transactional
    public void deleteByIds(Long id) {
        if (wageRepository.updateById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Thông tin phụ cấp này không tồn tại hoặc đã bị xóa");
        }
    }

    @Override
    @Transactional
    public void deleteForEmployeeByIds(Long id) {
        if (userDetailWageRepository.updateById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Thông tin phụ cấp này không tồn tại hoặc đã bị xóa");
        }
    }

}
