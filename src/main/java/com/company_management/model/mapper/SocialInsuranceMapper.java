package com.company_management.model.mapper;

import com.company_management.model.dto.SocialInsuranceDTO;
import com.company_management.model.entity.SocialInsurance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface SocialInsuranceMapper extends EntityMapper<SocialInsurance, SocialInsuranceDTO>{
}
