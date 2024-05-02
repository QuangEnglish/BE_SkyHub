package com.company_management.model.mapper;

import com.company_management.model.dto.WageDTO;
import com.company_management.model.entity.Wage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T10:29:45+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class WageMapperImpl implements WageMapper {

    @Override
    public Wage toEntity(WageDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Wage wage = new Wage();

        wage.setIsActive( dto.getIsActive() );
        wage.setWageName( dto.getWageName() );
        wage.setWageBase( dto.getWageBase() );
        wage.setLicenseDate( dto.getLicenseDate() );
        wage.setWageDescription( dto.getWageDescription() );
        wage.setEmpSign( dto.getEmpSign() );
        wage.setUserDetailId( dto.getUserDetailId() );

        return wage;
    }

    @Override
    public WageDTO toDto(Wage entity) {
        if ( entity == null ) {
            return null;
        }

        WageDTO wageDTO = new WageDTO();

        wageDTO.setWageName( entity.getWageName() );
        wageDTO.setWageBase( entity.getWageBase() );
        wageDTO.setWageDescription( entity.getWageDescription() );
        wageDTO.setIsActive( entity.getIsActive() );
        wageDTO.setLicenseDate( entity.getLicenseDate() );
        wageDTO.setEmpSign( entity.getEmpSign() );
        wageDTO.setUserDetailId( entity.getUserDetailId() );

        return wageDTO;
    }

    @Override
    public List<Wage> toEntity(List<WageDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Wage> list = new ArrayList<Wage>( dtoList.size() );
        for ( WageDTO wageDTO : dtoList ) {
            list.add( toEntity( wageDTO ) );
        }

        return list;
    }

    @Override
    public List<WageDTO> toDto(List<Wage> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<WageDTO> list = new ArrayList<WageDTO>( entityList.size() );
        for ( Wage wage : entityList ) {
            list.add( toDto( wage ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Wage entity, WageDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getIsActive() != null ) {
            entity.setIsActive( dto.getIsActive() );
        }
        if ( dto.getWageName() != null ) {
            entity.setWageName( dto.getWageName() );
        }
        if ( dto.getWageBase() != null ) {
            entity.setWageBase( dto.getWageBase() );
        }
        if ( dto.getLicenseDate() != null ) {
            entity.setLicenseDate( dto.getLicenseDate() );
        }
        if ( dto.getWageDescription() != null ) {
            entity.setWageDescription( dto.getWageDescription() );
        }
        if ( dto.getEmpSign() != null ) {
            entity.setEmpSign( dto.getEmpSign() );
        }
        if ( dto.getUserDetailId() != null ) {
            entity.setUserDetailId( dto.getUserDetailId() );
        }
    }
}
