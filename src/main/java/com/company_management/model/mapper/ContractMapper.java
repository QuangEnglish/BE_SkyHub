package com.company_management.model.mapper;

import com.company_management.model.dto.ContractDTO;
import com.company_management.model.entity.Contract;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ContractMapper extends EntityMapper<Contract, ContractDTO> {
}
