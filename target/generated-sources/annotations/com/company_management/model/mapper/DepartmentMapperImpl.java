package com.company_management.model.mapper;

import com.company_management.model.dto.DepartmentDTO;
import com.company_management.model.entity.Department;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-25T14:45:39+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department toEntity(DepartmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Department department = new Department();

        department.setDepartmentCode( dto.getDepartmentCode() );
        department.setDepartmentName( dto.getDepartmentName() );
        department.setStatus( dto.getStatus() );

        return department;
    }

    @Override
    public DepartmentDTO toDto(Department entity) {
        if ( entity == null ) {
            return null;
        }

        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setDepartmentCode( entity.getDepartmentCode() );
        departmentDTO.setDepartmentName( entity.getDepartmentName() );
        departmentDTO.setStatus( entity.getStatus() );

        return departmentDTO;
    }

    @Override
    public List<Department> toEntity(List<DepartmentDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Department> list = new ArrayList<Department>( dtoList.size() );
        for ( DepartmentDTO departmentDTO : dtoList ) {
            list.add( toEntity( departmentDTO ) );
        }

        return list;
    }

    @Override
    public List<DepartmentDTO> toDto(List<Department> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DepartmentDTO> list = new ArrayList<DepartmentDTO>( entityList.size() );
        for ( Department department : entityList ) {
            list.add( toDto( department ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Department entity, DepartmentDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getDepartmentCode() != null ) {
            entity.setDepartmentCode( dto.getDepartmentCode() );
        }
        if ( dto.getDepartmentName() != null ) {
            entity.setDepartmentName( dto.getDepartmentName() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
    }
}
