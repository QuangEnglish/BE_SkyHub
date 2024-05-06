package com.company_management.repository.impl;


import com.company_management.model.dto.WageDTO;
import com.company_management.model.response.DataPage;
import com.company_management.repository.UserDetailWageRepository;
import com.company_management.repository.WageRepositoryCustom;
import com.company_management.utils.DataUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class WageRepositoryImpl implements WageRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserDetailWageRepository userDetailWageRepository;
    @Override
    public DataPage<WageDTO> search(WageDTO wageDTO, Pageable pageable) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("select\n" +
                "    udw.id as userDetailWageId,\n" +
                "    wa.wage_name as wageName,\n" +
                "    wa.wage_base as wageBase,\n" +
                "    wa.wage_description as wageDescription,\n" +
                "    wa.attachfile as attachFile,\n" +
                "    udw.wage_id as wageId,\n" +
                "    udw.user_detail_id as userDetailId,\n" +
                "    udw.emp_sign as empSign,\n" +
                "    udw.license_date as licenseDate \n" +
                "from wage wa\n" +
                "left join user_detail_wage udw\n" +
                "on wa.id = udw.wage_id\n" +
                "where 1 = 1 and udw.is_active = 1 and wa.is_active = 1 ");
        Map<String, Object> map = new HashMap<>();
        if (!DataUtils.isNullOrEmpty(wageDTO.getWageName())) {
            sqlSelect.append(" and wa.wage_name like concat('%', :wageName, '%') ");
            map.put("wageName", wageDTO.getWageName());
        }

        if (!DataUtils.isNullOrEmpty(wageDTO.getUserDetailId())) {
            sqlSelect.append(" and udw.user_detail_id = :userDetailId ");
            map.put("userDetailId", wageDTO.getUserDetailId());
        }

        if (!DataUtils.isNullOrEmpty(wageDTO.getEmpSign())) {
            sqlSelect.append(" and udw.emp_sign = :empSign ");
            map.put("empSign", wageDTO.getEmpSign());
        }
        Query nativeQuery;
        Query countQuery;
        if (pageable.isPaged()) {
            String countSql = "select COUNT(*) from (" + sqlSelect + ") as total";
            if (pageable.getSort().isSorted()) {
                sqlSelect.append(" ORDER BY udw.")
                        .append(pageable.getSort().toString().replace(":", " "))
                        .append(", udw.id desc");
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
            nativeQuery = entityManager.createNativeQuery(sqlSelect + " order by wa.CREATED_DATE desc, wa.ID desc");
            if (!map.isEmpty()) {
                map.forEach(nativeQuery::setParameter);
            }
        }

        List<Object[]> resultList = nativeQuery.getResultList();
        List<WageDTO> listObjectsToClass = DataUtils.convertListObjectsToClass(Arrays.asList(
                "userDetailWageId","wageName", "wageBase", "wageDescription", "attachFile", "wageId", "userDetailId", "empSign", "licenseDate"),
                resultList,
                WageDTO.class);
        DataPage<WageDTO> dataPage = new DataPage<>();
        if (pageable.isPaged() && countQuery != null) {
            Long count = DataUtils.safeToLong(countQuery.getSingleResult());
            dataPage.setDataCount(count);
            dataPage.setPageSize(pageable.getPageSize());
            int pageCount = pageable.getPageSize() == 0 ? 1 : (int) Math.ceil((double) count / (double) pageable.getPageSize());
            dataPage.setPageCount(pageCount);
            dataPage.setPageIndex(pageable.getPageNumber());
        }
        dataPage.setData(listObjectsToClass);
        return dataPage;
    }

}
