package com.company_management.model.mapper;

import com.company_management.model.dto.PositionDTO;
import com.company_management.model.entity.Position;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T22:07:25+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class PositionMapperImpl implements PositionMapper {

    @Override
    public Position toEntity(PositionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Position position = new Position();

        position.setId( dto.getId() );
        position.setIsActive( dto.getIsActive() );
        position.setPositionCode( dto.getPositionCode() );
        position.setPositionName( dto.getPositionName() );
        position.setPositionDescription( dto.getPositionDescription() );
        position.setDepartmentId( dto.getDepartmentId() );

        return position;
    }

    @Override
    public PositionDTO toDto(Position entity) {
        if ( entity == null ) {
            return null;
        }

        PositionDTO positionDTO = new PositionDTO();

        positionDTO.setId( entity.getId() );
        positionDTO.setPositionName( entity.getPositionName() );
        positionDTO.setPositionCode( entity.getPositionCode() );
        positionDTO.setPositionDescription( entity.getPositionDescription() );
        positionDTO.setIsActive( entity.getIsActive() );
        positionDTO.setDepartmentId( entity.getDepartmentId() );

        return positionDTO;
    }

    @Override
    public List<Position> toEntity(List<PositionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Position> list = new ArrayList<Position>( dtoList.size() );
        for ( PositionDTO positionDTO : dtoList ) {
            list.add( toEntity( positionDTO ) );
        }

        return list;
    }

    @Override
    public List<PositionDTO> toDto(List<Position> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PositionDTO> list = new ArrayList<PositionDTO>( entityList.size() );
        for ( Position position : entityList ) {
            list.add( toDto( position ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Position entity, PositionDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            entity.setIsActive( dto.getIsActive() );
        }
        if ( dto.getPositionCode() != null ) {
            entity.setPositionCode( dto.getPositionCode() );
        }
        if ( dto.getPositionName() != null ) {
            entity.setPositionName( dto.getPositionName() );
        }
        if ( dto.getPositionDescription() != null ) {
            entity.setPositionDescription( dto.getPositionDescription() );
        }
        if ( dto.getDepartmentId() != null ) {
            entity.setDepartmentId( dto.getDepartmentId() );
        }
    }
}
