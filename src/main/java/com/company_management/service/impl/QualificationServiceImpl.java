package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.dto.QualificationDTO;
import com.company_management.model.entity.Qualification;
import com.company_management.repository.QualificationRepository;
import com.company_management.service.QualificationService;
import com.company_management.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepository;


    @Override
    @Transactional(readOnly = true)
    public Page<QualificationDTO> search(Long userDetailId, Pageable pageable) {
        Page<Qualification> qualificationEntyList = qualificationRepository.findByUserDetailId(userDetailId, pageable);
        List<QualificationDTO> qualificationDTOList = qualificationEntyList.stream()
                .filter(res -> res.getIsActive() == 1 )
                .map(
                res -> {
                    QualificationDTO qualificationDTO = new QualificationDTO();
                    qualificationDTO.setId(res.getId());
                    qualificationDTO.setLevel(res.getLevel());
                    qualificationDTO.setName(res.getName());
                    qualificationDTO.setMajor(res.getMajor());
                    qualificationDTO.setDescription(res.getDescription());
                    qualificationDTO.setLicenseDate(res.getLicenseDate());
                    qualificationDTO.setUserDetailId(res.getUserDetailId());
                    return qualificationDTO;
                }
        ).collect(Collectors.toList());
        return new PageImpl<>(qualificationDTOList, pageable, qualificationEntyList.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public QualificationDTO detail(Long id) {
        Qualification qualification = qualificationRepository.findById(id).orElseThrow(
                () -> new AppException("ERR01","Bằng cấp không tồn tại"));
        return QualificationDTO.builder()
                .id(qualification.getId())
                .major(qualification.getMajor())
                .name(qualification.getName())
                .description(qualification.getDescription())
                .level(qualification.getLevel())
                .licenseDate(qualification.getLicenseDate())
                .userDetailId(qualification.getUserDetailId())
                .build();
    }

    @Override
    @Transactional
    public void update(QualificationDTO qualificationDTO) {
        Qualification qualification = qualificationRepository.findById(qualificationDTO.getId())
                .orElseThrow(() -> new AppException("ERR01","Bằng cấp không tồn tại"));
        qualification.setLevel(qualificationDTO.getLevel());
        qualification.setMajor(qualificationDTO.getMajor());
        qualification.setDescription(qualificationDTO.getDescription());
        qualification.setName(qualificationDTO.getName());
        qualification.setLicenseDate(qualificationDTO.getLicenseDate());
        qualificationRepository.save(qualification);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addQualification(QualificationDTO qualificationDTO) {
        Qualification qualification = new Qualification();
        qualification.setLevel(qualificationDTO.getLevel());
        qualification.setMajor(qualificationDTO.getMajor());
        qualification.setName(qualificationDTO.getName());
        qualification.setDescription(qualificationDTO.getDescription());
        qualification.setLicenseDate(qualificationDTO.getLicenseDate());
        if(qualificationDTO.getUserDetailId()!=null){
            qualification.setUserDetailId(qualificationDTO.getUserDetailId());
        }else{
            throw new AppException("ERR01", "Chọn nhân viên thêm bằng cấp chứng chỉ");
        }
        qualificationRepository.save(qualification);
    }

    @Override
    @Transactional
    public void deleteByIds(Long id) {
        if (qualificationRepository.updateById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01" ,"Bằng cấp này không tồn tại hoặc đã bị xóa");
        }
    }

}
