package com.company_management.model.mapper;

import com.company_management.model.dto.UserDetailDTO;
import com.company_management.model.entity.UserDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T15:56:54+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public UserDetail toEntity(UserDetailDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserDetail userDetail = new UserDetail();

        userDetail.setId( dto.getId() );
        userDetail.setIsActive( dto.getIsActive() );
        userDetail.setEmployeeCode( dto.getEmployeeCode() );
        userDetail.setEmployeeName( dto.getEmployeeName() );
        userDetail.setGender( dto.getGender() );
        userDetail.setBirthday( dto.getBirthday() );
        userDetail.setAddress( dto.getAddress() );
        userDetail.setAvatar( dto.getAvatar() );
        userDetail.setPhone( dto.getPhone() );
        userDetail.setDepartmentId( dto.getDepartmentId() );
        userDetail.setPositionId( dto.getPositionId() );

        return userDetail;
    }

    @Override
    public UserDetailDTO toDto(UserDetail entity) {
        if ( entity == null ) {
            return null;
        }

        UserDetailDTO userDetailDTO = new UserDetailDTO();

        userDetailDTO.setId( entity.getId() );
        userDetailDTO.setEmployeeCode( entity.getEmployeeCode() );
        userDetailDTO.setEmployeeName( entity.getEmployeeName() );
        userDetailDTO.setGender( entity.getGender() );
        userDetailDTO.setBirthday( entity.getBirthday() );
        userDetailDTO.setAvatar( entity.getAvatar() );
        userDetailDTO.setPhone( entity.getPhone() );
        userDetailDTO.setAddress( entity.getAddress() );
        userDetailDTO.setIsActive( entity.getIsActive() );
        userDetailDTO.setDepartmentId( entity.getDepartmentId() );
        userDetailDTO.setPositionId( entity.getPositionId() );

        return userDetailDTO;
    }

    @Override
    public List<UserDetail> toEntity(List<UserDetailDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserDetail> list = new ArrayList<UserDetail>( dtoList.size() );
        for ( UserDetailDTO userDetailDTO : dtoList ) {
            list.add( toEntity( userDetailDTO ) );
        }

        return list;
    }

    @Override
    public List<UserDetailDTO> toDto(List<UserDetail> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDetailDTO> list = new ArrayList<UserDetailDTO>( entityList.size() );
        for ( UserDetail userDetail : entityList ) {
            list.add( toDto( userDetail ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(UserDetail entity, UserDetailDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            entity.setIsActive( dto.getIsActive() );
        }
        if ( dto.getEmployeeCode() != null ) {
            entity.setEmployeeCode( dto.getEmployeeCode() );
        }
        if ( dto.getEmployeeName() != null ) {
            entity.setEmployeeName( dto.getEmployeeName() );
        }
        if ( dto.getGender() != null ) {
            entity.setGender( dto.getGender() );
        }
        if ( dto.getBirthday() != null ) {
            entity.setBirthday( dto.getBirthday() );
        }
        if ( dto.getAddress() != null ) {
            entity.setAddress( dto.getAddress() );
        }
        if ( dto.getAvatar() != null ) {
            entity.setAvatar( dto.getAvatar() );
        }
        if ( dto.getPhone() != null ) {
            entity.setPhone( dto.getPhone() );
        }
        if ( dto.getDepartmentId() != null ) {
            entity.setDepartmentId( dto.getDepartmentId() );
        }
        if ( dto.getPositionId() != null ) {
            entity.setPositionId( dto.getPositionId() );
        }
    }
}
