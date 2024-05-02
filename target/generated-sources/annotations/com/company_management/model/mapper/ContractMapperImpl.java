package com.company_management.model.mapper;

import com.company_management.model.dto.ContractDTO;
import com.company_management.model.entity.Contract;
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
public class ContractMapperImpl implements ContractMapper {

    @Override
    public Contract toEntity(ContractDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Contract contract = new Contract();

        contract.setIsActive( dto.getIsActive() );
        contract.setContractCode( dto.getContractCode() );
        contract.setContractType( dto.getContractType() );
        contract.setAttachFile( dto.getAttachFile() );

        return contract;
    }

    @Override
    public ContractDTO toDto(Contract entity) {
        if ( entity == null ) {
            return null;
        }

        ContractDTO contractDTO = new ContractDTO();

        contractDTO.setContractCode( entity.getContractCode() );
        contractDTO.setIsActive( entity.getIsActive() );
        contractDTO.setContractType( entity.getContractType() );
        contractDTO.setAttachFile( entity.getAttachFile() );

        return contractDTO;
    }

    @Override
    public List<Contract> toEntity(List<ContractDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Contract> list = new ArrayList<Contract>( dtoList.size() );
        for ( ContractDTO contractDTO : dtoList ) {
            list.add( toEntity( contractDTO ) );
        }

        return list;
    }

    @Override
    public List<ContractDTO> toDto(List<Contract> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ContractDTO> list = new ArrayList<ContractDTO>( entityList.size() );
        for ( Contract contract : entityList ) {
            list.add( toDto( contract ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Contract entity, ContractDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getIsActive() != null ) {
            entity.setIsActive( dto.getIsActive() );
        }
        if ( dto.getContractCode() != null ) {
            entity.setContractCode( dto.getContractCode() );
        }
        if ( dto.getContractType() != null ) {
            entity.setContractType( dto.getContractType() );
        }
        if ( dto.getAttachFile() != null ) {
            entity.setAttachFile( dto.getAttachFile() );
        }
    }
}
