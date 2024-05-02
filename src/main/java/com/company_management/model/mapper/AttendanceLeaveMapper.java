package com.company_management.model.mapper;

import com.company_management.model.dto.AttendanceLeaveDTO;
import com.company_management.model.entity.AttendanceLeave;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface AttendanceLeaveMapper extends EntityMapper<AttendanceLeave, AttendanceLeaveDTO> {
}
