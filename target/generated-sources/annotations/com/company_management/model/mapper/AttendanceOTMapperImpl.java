package com.company_management.model.mapper;

import com.company_management.model.dto.AttendanceOTDTO;
import com.company_management.model.entity.AttendanceOt;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-06T23:12:20+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class AttendanceOTMapperImpl implements AttendanceOTMapper {

    @Override
    public AttendanceOt toEntity(AttendanceOTDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AttendanceOt attendanceOt = new AttendanceOt();

        attendanceOt.setIsActive( dto.getIsActive() );
        attendanceOt.setStartDay( dto.getStartDay() );
        attendanceOt.setStartTime( dto.getStartTime() );
        attendanceOt.setEndTime( dto.getEndTime() );
        attendanceOt.setTotalTime( dto.getTotalTime() );
        attendanceOt.setEmployeeId( dto.getEmployeeId() );
        attendanceOt.setFollowId( dto.getFollowId() );
        attendanceOt.setDescriptionOt( dto.getDescriptionOt() );

        return attendanceOt;
    }

    @Override
    public AttendanceOTDTO toDto(AttendanceOt entity) {
        if ( entity == null ) {
            return null;
        }

        AttendanceOTDTO attendanceOTDTO = new AttendanceOTDTO();

        attendanceOTDTO.setStartDay( entity.getStartDay() );
        attendanceOTDTO.setStartTime( entity.getStartTime() );
        attendanceOTDTO.setEndTime( entity.getEndTime() );
        attendanceOTDTO.setTotalTime( entity.getTotalTime() );
        attendanceOTDTO.setEmployeeId( entity.getEmployeeId() );
        attendanceOTDTO.setFollowId( entity.getFollowId() );
        attendanceOTDTO.setDescriptionOt( entity.getDescriptionOt() );
        attendanceOTDTO.setIsActive( entity.getIsActive() );

        return attendanceOTDTO;
    }

    @Override
    public List<AttendanceOt> toEntity(List<AttendanceOTDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<AttendanceOt> list = new ArrayList<AttendanceOt>( dtoList.size() );
        for ( AttendanceOTDTO attendanceOTDTO : dtoList ) {
            list.add( toEntity( attendanceOTDTO ) );
        }

        return list;
    }

    @Override
    public List<AttendanceOTDTO> toDto(List<AttendanceOt> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AttendanceOTDTO> list = new ArrayList<AttendanceOTDTO>( entityList.size() );
        for ( AttendanceOt attendanceOt : entityList ) {
            list.add( toDto( attendanceOt ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(AttendanceOt entity, AttendanceOTDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getIsActive() != null ) {
            entity.setIsActive( dto.getIsActive() );
        }
        if ( dto.getStartDay() != null ) {
            entity.setStartDay( dto.getStartDay() );
        }
        if ( dto.getStartTime() != null ) {
            entity.setStartTime( dto.getStartTime() );
        }
        if ( dto.getEndTime() != null ) {
            entity.setEndTime( dto.getEndTime() );
        }
        if ( dto.getTotalTime() != null ) {
            entity.setTotalTime( dto.getTotalTime() );
        }
        if ( dto.getEmployeeId() != null ) {
            entity.setEmployeeId( dto.getEmployeeId() );
        }
        if ( dto.getFollowId() != null ) {
            entity.setFollowId( dto.getFollowId() );
        }
        if ( dto.getDescriptionOt() != null ) {
            entity.setDescriptionOt( dto.getDescriptionOt() );
        }
    }
}
