package com.company_management.repository;

import com.company_management.model.dto.PositionDTO;
import com.company_management.model.request.SearchPositionRequest;
import com.company_management.model.response.DataPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PositionRepositoryCustom {
    DataPage<PositionDTO> search(SearchPositionRequest searchPositionRequest, Pageable pageable);

    List<PositionDTO> searchExport(SearchPositionRequest searchPositionRequest, Pageable pageable);

}
