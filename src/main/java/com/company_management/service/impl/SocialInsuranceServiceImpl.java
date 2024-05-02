package com.company_management.service.impl;

import com.company_management.exception.AppException;
import com.company_management.model.dto.SocialInsuranceDTO;
import com.company_management.model.entity.SocialInsurance;
import com.company_management.model.mapper.SocialInsuranceMapper;
import com.company_management.repository.SocialInsuranceRepository;
import com.company_management.service.SocialInsuranceService;
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
public class SocialInsuranceServiceImpl implements SocialInsuranceService {

    private final SocialInsuranceRepository socialInsuranceRepository;

    private final SocialInsuranceMapper socialInsuranceMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<SocialInsuranceDTO> search(Long userDetailId, Pageable pageable) {
        Page<SocialInsurance> socialInsurances = socialInsuranceRepository.findByUserDetailId(userDetailId, pageable);
        List<SocialInsuranceDTO> socialInsuranceDTOS = socialInsurances.stream().map(
                res -> {
                    SocialInsuranceDTO socialInsuranceDTO = new SocialInsuranceDTO();
                    socialInsuranceDTO.setSocialInsuranceId(res.getId());
                    socialInsuranceDTO.setSocialInsuranceCode(res.getSocialInsuranceCode());
                    socialInsuranceDTO.setInitialPayment(res.getInitialPayment());
                    socialInsuranceDTO.setPercent(res.getPercent());
                    socialInsuranceDTO.setActualPayment(res.getActualPayment());
                    socialInsuranceDTO.setLicenseDate(res.getLicenseDate());
                    socialInsuranceDTO.setExpiredDate(res.getExpiredDate());
                    socialInsuranceDTO.setUserDetailId(res.getUserDetailId());
                    return socialInsuranceDTO;
                }
        ).collect(Collectors.toList());
        return new PageImpl<>(socialInsuranceDTOS, pageable, socialInsurances.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public SocialInsuranceDTO detail(Long id) {
        SocialInsurance socialInsurance = socialInsuranceRepository.findById(id).orElseThrow(
                () -> new AppException("ERR01", "Mã bảo hiểm xã hội không tồn tại"));
        return SocialInsuranceDTO.builder()
                .socialInsuranceId(socialInsurance.getId())
                .socialInsuranceCode(socialInsurance.getSocialInsuranceCode())
                .initialPayment(socialInsurance.getInitialPayment())
                .percent(socialInsurance.getPercent())
                .actualPayment(socialInsurance.getActualPayment())
                .expiredDate(socialInsurance.getExpiredDate())
                .licenseDate(socialInsurance.getLicenseDate())
                .userDetailId(socialInsurance.getUserDetailId())
                .build();
    }

    @Override
    @Transactional
    public void update(SocialInsuranceDTO socialInsuranceDTO) {
        SocialInsurance socialInsurance = socialInsuranceRepository.findById(socialInsuranceDTO.getSocialInsuranceId())
                .orElseThrow(() -> new AppException("ERR01", "Mã bảo hiểm xã hội không tồn tại"));
        socialInsurance.setSocialInsuranceCode(socialInsuranceDTO.getSocialInsuranceCode());
        socialInsurance.setActualPayment(socialInsuranceDTO.getActualPayment());
        socialInsurance.setPercent(socialInsuranceDTO.getPercent());
        socialInsurance.setInitialPayment(socialInsuranceDTO.getInitialPayment());
        socialInsurance.setExpiredDate(socialInsuranceDTO.getExpiredDate());
        socialInsurance.setLicenseDate(socialInsuranceDTO.getLicenseDate());
        socialInsuranceRepository.save(socialInsurance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SocialInsuranceDTO socialInsuranceDTO) {
        SocialInsurance socialInsurance = socialInsuranceMapper.toEntity(socialInsuranceDTO);
        socialInsurance.setInitialPayment(Double.parseDouble(socialInsuranceDTO.getInitialPayment().toString()));
        socialInsurance.setPercent(Double.parseDouble(socialInsuranceDTO.getPercent().toString()));
        socialInsurance.setActualPayment(Double.parseDouble(socialInsuranceDTO.getActualPayment().toString()));
        socialInsurance.setLicenseDate(socialInsuranceDTO.getLicenseDate());
        socialInsurance.setExpiredDate(socialInsuranceDTO.getExpiredDate());
        if (socialInsuranceDTO.getUserDetailId() != null) {
            socialInsurance.setUserDetailId(socialInsuranceDTO.getUserDetailId());
        } else {
            throw new AppException("ERR01", "Chọn nhân viên để thêm mã bảo hiểm xã hội");
        }
        socialInsuranceRepository.save(socialInsurance);
    }

    @Override
    @Transactional
    public void deleteByIds(Long id) {
        if (socialInsuranceRepository.updateById(id, CommonUtils.getUserLoginName()) <= 0) {
            throw new AppException("ERR01", "Mã bảo hiểm xã hội này không tồn tại hoặc đã bị xóa");
        }
    }

}
