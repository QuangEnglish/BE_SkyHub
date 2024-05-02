package com.company_management.repository.impl;

import com.company_management.model.dto.PositionDTO;
import com.company_management.model.request.SearchPositionRequest;
import com.company_management.model.response.DataPage;
import com.company_management.repository.PositionRepositoryCustom;
import com.company_management.utils.DataUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PositionRepositoryImpl implements PositionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public DataPage<PositionDTO> search(SearchPositionRequest searchPositionRequest, Pageable pageable) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("select p.id, POSITION_CODE positionCode, POSITION_NAME positionName, POSITION_DESCRIPTION positionDescription, p.IS_ACTIVE isActive, de.ID departmentId, de.DEPARTMENT_NAME departmentName ");
        sqlSelect.append("from position p ");
        sqlSelect.append("  left join department de on p.DEPARTMENT_ID = de.ID and de.IS_ACTIVE = 1 ");
        sqlSelect.append("where 1 = 1 ");
        Map<String, Object> map = getStringObjectMap(searchPositionRequest, sqlSelect);
        Query nativeQuery;
        Query countQuery;
        if (pageable.isPaged()) {
            String countSql = "select COUNT(*) from (" + sqlSelect + ") as total";
            if (pageable.getSort().isSorted()) {
                sqlSelect.append(" ORDER BY p.")
                        .append(pageable.getSort().toString().replace(":", " "))
                        .append(", id desc");
            }
            nativeQuery = entityManager.createNativeQuery(sqlSelect.toString());

            nativeQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            nativeQuery.setMaxResults(pageable.getPageSize());

            countQuery = entityManager.createNativeQuery(countSql);
            if (!map.isEmpty()) {
                map.forEach((key, value) -> {
                    nativeQuery.setParameter(key, value);
                    countQuery.setParameter(key, value);
                });
            }
        } else {
            countQuery = null;
            nativeQuery = entityManager.createNativeQuery(sqlSelect + " order by p.CREATED_DATE desc, p.ID desc");
            if (!map.isEmpty()) {
                map.forEach(nativeQuery::setParameter);
            }
        }
        List<Object[]> resultList = nativeQuery.getResultList();
        List<PositionDTO> lstPosition = DataUtils.convertListObjectsToClass(
                Arrays.asList("id", "positionCode", "positionName", "positionDescription", "isActive", "departmentId", "departmentName"),
                resultList,
                PositionDTO.class);
        DataPage<PositionDTO> dataPage = new DataPage<>();
        if (pageable.isPaged() && countQuery != null) {
            Long count = DataUtils.safeToLong(countQuery.getSingleResult());
            dataPage.setDataCount(count);
            dataPage.setPageSize(pageable.getPageSize());

            int pageCount = pageable.getPageSize() == 0 ? 1 : (int) Math.ceil((double) count / (double) pageable.getPageSize());
            dataPage.setPageCount(pageCount);
            dataPage.setPageIndex(pageable.getPageNumber());
        }
        dataPage.setData(lstPosition);
        return dataPage;
    }

    @Override
    public List<PositionDTO> searchExport(SearchPositionRequest searchPositionRequest, Pageable pageable) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("select p.id, POSITION_CODE positionCode, POSITION_NAME positionName, POSITION_DESCRIPTION positionDescription, p.IS_ACTIVE isActive, de.ID departmentId, de.DEPARTMENT_NAME departmentName ");
        sqlSelect.append("from position p ");
        sqlSelect.append("  left join department de on p.DEPARTMENT_ID = de.ID and de.IS_ACTIVE = 1 ");
        sqlSelect.append("where p.IS_ACTIVE = 1 ");

        Map<String, Object> map = getStringObjectMap(searchPositionRequest, sqlSelect);
        if (pageable.isPaged() && pageable.getSort().isSorted()) {
            sqlSelect.append(" ORDER BY p.")
                    .append(pageable.getSort().toString().replace(":", " "))
                    .append(", p.id desc");
        }
        Query nativeQuery = entityManager.createNativeQuery(sqlSelect.toString());

        if (!map.isEmpty()) {
            map.forEach(nativeQuery::setParameter);
        }
        return DataUtils.convertListObjectsToClass(
                Arrays.asList("id", "positionCode", "positionName", "positionDescription", "isActive", "departmentId", "departmentName"),
                nativeQuery.getResultList(),
                PositionDTO.class);

    }

    private static Map<String, Object> getStringObjectMap(SearchPositionRequest searchPositionRequest, StringBuilder sqlSelect) {
        Map<String, Object> map = new HashMap<>();
        if (!DataUtils.isNullOrEmpty(searchPositionRequest.getPositionCode())) {
            sqlSelect.append("  and p.position_code = :positionCode");
            map.put("positionCode", searchPositionRequest.getPositionCode());
        }
        if (!DataUtils.isNullOrEmpty(searchPositionRequest.getPositionName())) {
            sqlSelect.append("  and p.position_name = :positionName");
            map.put("positionName", searchPositionRequest.getPositionName());
        }
        if (!DataUtils.isNullOrEmpty(searchPositionRequest.getIsActive())) {
            sqlSelect.append("  and p.is_active = :isActive");
            map.put("isActive", searchPositionRequest.getIsActive());
        }
        return map;
    }

}
