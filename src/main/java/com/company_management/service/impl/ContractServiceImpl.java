package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.dto.ContractDTO;
import com.company_management.model.dto.UserDetailContractDTO;
import com.company_management.model.entity.Contract;
import com.company_management.model.entity.UserDetail;
import com.company_management.model.entity.UserDetailContract;
import com.company_management.model.response.ContractResponse;
import com.company_management.model.response.DataPage;
import com.company_management.repository.ContractRepository;
import com.company_management.repository.CustomBaseRepository;
import com.company_management.repository.UserDetailContractRepository;
import com.company_management.repository.UserDetailRepository;
import com.company_management.service.ContractService;
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
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final UserDetailRepository userDetailRepository;

    private final CustomBaseRepository customBaseRepository;

    private final UserDetailContractRepository userDetailContractRepository;

    @Value("${upload.path}")
    private String fileUpload;

    @Override
    @Transactional(readOnly = true)
    public DataPage<ContractDTO> searchForEmployee(ContractDTO contractDTO, Pageable pageable) {
        return contractRepository.search(contractDTO, pageable);
    }

    @Override
    public DataPage<ContractDTO> search(ContractDTO contractDTO, Pageable pageable) {
        Pageable paging = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        Page<Object[]> contractPage = contractRepository.findAllWithPagination(
                DataUtils.isNullOrEmpty(contractDTO.getContractCode()) ? null : contractDTO.getContractCode().trim().toLowerCase(),
                DataUtils.isNullOrEmpty(contractDTO.getContractType()) ? null : contractDTO.getContractType().trim().toLowerCase(),
                paging);
        List<ContractDTO> contractDTOList = DataUtils.convertListObjectsToClass(new ArrayList<>(Arrays.asList(
                        "contractId", "contractCode", "contractType", "attachFile", "isActive")),
                contractPage.getContent(),
                ContractDTO.class);
        DataPage<ContractDTO> dataPage = new DataPage<>();
        dataPage.setData(contractDTOList);
        dataPage.setPageIndex(contractPage.getPageable().getPageNumber());
        dataPage.setPageSize(contractPage.getPageable().getPageSize());
        dataPage.setPageCount(contractPage.getTotalPages());
        dataPage.setDataCount(contractPage.getTotalElements());
        return dataPage;
    }

    @Override
    @Transactional(readOnly = true)
    public ContractResponse detail(Long id) {
        if (null == id) {
            log.error("ID is null");
            throw new AppException("ID is null", "Argument Invalid");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder("select\n" +
                "    ctr.contract_code as contractCode,\n" +
                "    ctr.contract_type as contractType,\n" +
                "    ctr.attachfile as attachfile,\n" +
                "    udc.contract_id as contractId,\n" +
                "    udc.active_date as activeDate,\n" +
                "    udc.expired_date as expiredDate,\n" +
                "    udc.sign_date as signDate\n" +
                "from contract ctr\n" +
                "left join user_detail_contract udc\n" +
                "on ctr.id = udc.contract_id\n" +
                "where and ctr.is_active = 1\n" +
                "and ctr.id = :id ");
        params.put("id", id);

        List<Object[]> listObj = customBaseRepository.queryHasParam(sql.toString(), params);
        List<ContractResponse> contractDTOS = DataUtils.convertListObjectsToClass(
                Arrays.asList("contractId", "contractCode", "isActive", "contractType", "attachFile", "signDate", "activeDate", "expiredDate"),
                listObj,
                ContractResponse.class);
        List<UserDetailContract> byContractId = userDetailContractRepository.findByContractId(id);
        List<Long> collect = byContractId.stream().map(UserDetailContract::getContractId).collect(Collectors.toList());
        contractDTOS.get(0).setUserDetailId(collect);
        return contractDTOS.get(0);
    }

    @Override
    @Transactional
    public void update(MultipartFile file, ContractDTO contractDTO) {
        Contract contract = contractRepository.findById(contractDTO.getContractId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm mã hợp đồng này!"));
        log.debug("// Update contract");
        if (!DataUtils.isNullOrEmpty(contractDTO.getContractCode())) {
            contract.setContractCode(contractDTO.getContractCode());
        }
        if (!DataUtils.isNullOrEmpty(contractDTO.getContractType())) {
            contract.setContractType(contractDTO.getContractType());
        }
        if (!DataUtils.isNullOrEmpty(contractDTO.getIsActive())) {
            contract.setIsActive(contractDTO.getIsActive());
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
                if (!contract.getAttachFile().equals(filePath.toString())) {
                    contract.setAttachFile(fileName);
                }
            } catch (NullPointerException e) {
                log.error("File ảnh là null.", e);
                throw new AppException("ERO02", "File là null");
            } catch (IOException e) {
                log.error("Lỗi xảy ra khi xử lý file", e);
                throw new AppException("ERO02", "Lỗi xảy ra khi xử lý file");
            }
        }
        contractRepository.save(contract);
        log.info("// Lưu hợp đồng thành công!");
    }

    @Override
    @Transactional
    public void updateForEmployee(UserDetailContractDTO userDetailContractDTO) {
        UserDetailContract userDetailContract = userDetailContractRepository.findById(userDetailContractDTO.getId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm thấy mã hợp đồng cho nhân viên này!"));
        log.debug("// Update contract");
        if(!DataUtils.isNullOrEmpty(userDetailContractDTO.getContractId())){
            Contract contract = contractRepository.findById(userDetailContractDTO.getContractId()).orElseThrow(
                    () -> new AppException("ERR01", "Không tìm thấy loại hợp đồng cho nhân viên này!"));
            userDetailContract.setContractId(contract.getId());
        }
        if (!DataUtils.isNullOrEmpty(userDetailContractDTO.getActiveDate())) {
            userDetailContract.setActiveDate(userDetailContractDTO.getActiveDate());
        }
        if (!DataUtils.isNullOrEmpty(userDetailContractDTO.getExpiredDate())) {
            userDetailContract.setExpiredDate(userDetailContractDTO.getExpiredDate());
        }
        if (!DataUtils.isNullOrEmpty(userDetailContractDTO.getSignDate())) {
            userDetailContract.setSignDate(userDetailContractDTO.getSignDate());
        }
        userDetailContractRepository.save(userDetailContract);
        log.info("// Lưu hợp đồng thành công!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MultipartFile file, ContractDTO contractDTO) {
        log.info("------------------------saveData Started--------------------------");
        if (null == contractDTO.getContractId()) {
            Contract contract = new Contract();
            contract.setContractCode(contractDTO.getContractCode());
            contract.setContractType(contractDTO.getContractType());
            if (file != null && file.getOriginalFilename() != null) {
                try {
                    // Lưu tệp Word vào máy
                    String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                    Path filePath = Paths.get(this.fileUpload + fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    contract.setAttachFile(fileName);
                } catch (IOException e) {
                    log.error("Lỗi xảy ra khi xử lý file", e);
                    throw new AppException("ERO02", "Lỗi xảy ra khi xử lý file");
                }
            }
            contractRepository.save(contract);
        }
        log.info("------------------------saveData Finished--------------------------");
    }

    @Override
    @Transactional
    public void addForEmployee(UserDetailContractDTO userDetailContractDTO) {
        Contract contract = contractRepository.findById(userDetailContractDTO.getContractId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm mã hợp đồng này!")
        );
        UserDetail userDetail = userDetailRepository.findById(userDetailContractDTO.getUserDetailId()).orElseThrow(
                () -> new AppException("ERR01", "Không tìm mã nhân viên này!")
        );
        UserDetailContract userDetailContract = new UserDetailContract();
        userDetailContract.setContractId(contract.getId());
        userDetailContract.setUserDetailId(userDetail.getId());
        userDetailContract.setActiveDate(userDetailContractDTO.getActiveDate());
        userDetailContract.setExpiredDate(userDetailContractDTO.getExpiredDate());
        userDetailContract.setSignDate(userDetailContractDTO.getSignDate());
        userDetailContractRepository.save(userDetailContract);
    }

    @Override
    @Transactional
    public void deleteByIds(Long id) {
        if (contractRepository.updateById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Hợp đồng này không tồn tại hoặc đã bị xóa");
        }
    }

    @Override
    @Transactional
    public void deleteForEmployeeByIds(Long id) {
        if (userDetailContractRepository.updateById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Hợp đồng này không tồn tại hoặc đã bị xóa");
        }
    }

}
