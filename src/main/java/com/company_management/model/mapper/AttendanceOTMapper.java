package com.company_management.model.mapper;

import com.company_management.model.dto.AttendanceOTDTO;
import com.company_management.model.entity.AttendanceOt;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface AttendanceOTMapper extends EntityMapper<AttendanceOt, AttendanceOTDTO> {
}
