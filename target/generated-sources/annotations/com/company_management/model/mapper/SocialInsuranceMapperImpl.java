package com.company_management.model.mapper;

import com.company_management.model.dto.SocialInsuranceDTO;
import com.company_management.model.entity.SocialInsurance;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-06T23:12:21+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class SocialInsuranceMapperImpl implements SocialInsuranceMapper {

    @Override
    public SocialInsurance toEntity(SocialInsuranceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SocialInsurance socialInsurance = new SocialInsurance();

        socialInsurance.setSocialInsuranceCode( dto.getSocialInsuranceCode() );
        socialInsurance.setInitialPayment( dto.getInitialPayment() );
        socialInsurance.setPercent( dto.getPercent() );
        socialInsurance.setActualPayment( dto.getActualPayment() );
        socialInsurance.setPlacement( dto.getPlacement() );
        socialInsurance.setLicenseDate( dto.getLicenseDate() );
        socialInsurance.setExpiredDate( dto.getExpiredDate() );
        socialInsurance.setUserDetailId( dto.getUserDetailId() );

        return socialInsurance;
    }

    @Override
    public SocialInsuranceDTO toDto(SocialInsurance entity) {
        if ( entity == null ) {
            return null;
        }

        SocialInsuranceDTO.SocialInsuranceDTOBuilder socialInsuranceDTO = SocialInsuranceDTO.builder();

        socialInsuranceDTO.socialInsuranceCode( entity.getSocialInsuranceCode() );
        socialInsuranceDTO.initialPayment( entity.getInitialPayment() );
        socialInsuranceDTO.percent( entity.getPercent() );
        socialInsuranceDTO.actualPayment( entity.getActualPayment() );
        socialInsuranceDTO.placement( entity.getPlacement() );
        socialInsuranceDTO.licenseDate( entity.getLicenseDate() );
        socialInsuranceDTO.expiredDate( entity.getExpiredDate() );
        socialInsuranceDTO.userDetailId( entity.getUserDetailId() );

        return socialInsuranceDTO.build();
    }

    @Override
    public List<SocialInsurance> toEntity(List<SocialInsuranceDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SocialInsurance> list = new ArrayList<SocialInsurance>( dtoList.size() );
        for ( SocialInsuranceDTO socialInsuranceDTO : dtoList ) {
            list.add( toEntity( socialInsuranceDTO ) );
        }

        return list;
    }

    @Override
    public List<SocialInsuranceDTO> toDto(List<SocialInsurance> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SocialInsuranceDTO> list = new ArrayList<SocialInsuranceDTO>( entityList.size() );
        for ( SocialInsurance socialInsurance : entityList ) {
            list.add( toDto( socialInsurance ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(SocialInsurance entity, SocialInsuranceDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getSocialInsuranceCode() != null ) {
            entity.setSocialInsuranceCode( dto.getSocialInsuranceCode() );
        }
        if ( dto.getInitialPayment() != null ) {
            entity.setInitialPayment( dto.getInitialPayment() );
        }
        if ( dto.getPercent() != null ) {
            entity.setPercent( dto.getPercent() );
        }
        if ( dto.getActualPayment() != null ) {
            entity.setActualPayment( dto.getActualPayment() );
        }
        if ( dto.getPlacement() != null ) {
            entity.setPlacement( dto.getPlacement() );
        }
        if ( dto.getLicenseDate() != null ) {
            entity.setLicenseDate( dto.getLicenseDate() );
        }
        if ( dto.getExpiredDate() != null ) {
            entity.setExpiredDate( dto.getExpiredDate() );
        }
        if ( dto.getUserDetailId() != null ) {
            entity.setUserDetailId( dto.getUserDetailId() );
        }
    }
}
