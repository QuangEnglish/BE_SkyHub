package com.company_management.model.mapper;

import com.company_management.model.dto.AttendanceLeaveDTO;
import com.company_management.model.entity.AttendanceLeave;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T15:30:57+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class AttendanceLeaveMapperImpl implements AttendanceLeaveMapper {

    @Override
    public AttendanceLeave toEntity(AttendanceLeaveDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AttendanceLeave attendanceLeave = new AttendanceLeave();

        attendanceLeave.setIsActive( dto.getIsActive() );
        attendanceLeave.setLeaveCategory( dto.getLeaveCategory() );
        attendanceLeave.setStartDay( dto.getStartDay() );
        attendanceLeave.setEndDay( dto.getEndDay() );
        attendanceLeave.setTotalTime( dto.getTotalTime() );
        attendanceLeave.setDescription( dto.getDescription() );
        attendanceLeave.setEmployeeId( dto.getEmployeeId() );
        attendanceLeave.setReviewerId( dto.getReviewerId() );
        attendanceLeave.setTrackerId( dto.getTrackerId() );

        return attendanceLeave;
    }

    @Override
    public AttendanceLeaveDTO toDto(AttendanceLeave entity) {
        if ( entity == null ) {
            return null;
        }

        AttendanceLeaveDTO attendanceLeaveDTO = new AttendanceLeaveDTO();

        attendanceLeaveDTO.setLeaveCategory( entity.getLeaveCategory() );
        attendanceLeaveDTO.setEmployeeId( entity.getEmployeeId() );
        attendanceLeaveDTO.setStartDay( entity.getStartDay() );
        attendanceLeaveDTO.setEndDay( entity.getEndDay() );
        attendanceLeaveDTO.setDescription( entity.getDescription() );
        attendanceLeaveDTO.setTotalTime( entity.getTotalTime() );
        attendanceLeaveDTO.setTrackerId( entity.getTrackerId() );
        attendanceLeaveDTO.setReviewerId( entity.getReviewerId() );
        attendanceLeaveDTO.setIsActive( entity.getIsActive() );

        return attendanceLeaveDTO;
    }

    @Override
    public List<AttendanceLeave> toEntity(List<AttendanceLeaveDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<AttendanceLeave> list = new ArrayList<AttendanceLeave>( dtoList.size() );
        for ( AttendanceLeaveDTO attendanceLeaveDTO : dtoList ) {
            list.add( toEntity( attendanceLeaveDTO ) );
        }

        return list;
    }

    @Override
    public List<AttendanceLeaveDTO> toDto(List<AttendanceLeave> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AttendanceLeaveDTO> list = new ArrayList<AttendanceLeaveDTO>( entityList.size() );
        for ( AttendanceLeave attendanceLeave : entityList ) {
            list.add( toDto( attendanceLeave ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(AttendanceLeave entity, AttendanceLeaveDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getIsActive() != null ) {
            entity.setIsActive( dto.getIsActive() );
        }
        if ( dto.getLeaveCategory() != null ) {
            entity.setLeaveCategory( dto.getLeaveCategory() );
        }
        if ( dto.getStartDay() != null ) {
            entity.setStartDay( dto.getStartDay() );
        }
        if ( dto.getEndDay() != null ) {
            entity.setEndDay( dto.getEndDay() );
        }
        if ( dto.getTotalTime() != null ) {
            entity.setTotalTime( dto.getTotalTime() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
        if ( dto.getEmployeeId() != null ) {
            entity.setEmployeeId( dto.getEmployeeId() );
        }
        if ( dto.getReviewerId() != null ) {
            entity.setReviewerId( dto.getReviewerId() );
        }
        if ( dto.getTrackerId() != null ) {
            entity.setTrackerId( dto.getTrackerId() );
        }
    }
}
