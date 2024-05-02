package com.company_management.repository.impl;


import com.company_management.model.dto.ContractDTO;
import com.company_management.model.response.DataPage;
import com.company_management.repository.ContractRepositoryCustom;
import com.company_management.repository.UserDetailContractRepository;
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
public class ContractRepositoryImpl implements ContractRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserDetailContractRepository userDetailContractRepository;
    @Override
    public DataPage<ContractDTO> search(ContractDTO contractDTO, Pageable pageable) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("select\n" +
                "    udc.id as userDetailContractId,\n" +
                "    ctr.contract_code as contractCode,\n" +
                "    ctr.contract_type as contractType,\n" +
                "    ctr.attachfile as attachfile,\n" +
                "    udc.contract_id as contractId,\n" +
                "    udc.user_detail_id as userDetailId,\n" +
                "    udc.active_date as activeDate,\n" +
                "    udc.expired_date as expiredDate,\n" +
                "    udc.sign_date as signDate\n" +
                "from contract ctr\n" +
                "left join user_detail_contract udc\n" +
                "on ctr.id = udc.contract_id\n" +
                "where 1 = 1 and udc.is_active = 1 and ctr.is_active = 1 ");
        Map<String, Object> map = new HashMap<>();
        if (!DataUtils.isNullOrEmpty(contractDTO.getContractType())) {
            sqlSelect.append(" and ctr.contract_type = :contractType");
            map.put("contractType", contractDTO.getContractType());
        }

        if (!DataUtils.isNullOrEmpty(contractDTO.getUserDetailId())) {
            sqlSelect.append(" and udc.user_detail_id = :userDetailId");
            map.put("userDetailId", contractDTO.getUserDetailId());
        }

        if (!DataUtils.isNullOrEmpty(contractDTO.getContractCode())) {
            sqlSelect.append(" and ctr.contract_code like concat('%', :contractCode, '%') ");
            map.put("contractCode", contractDTO.getContractCode());
        }
        Query nativeQuery;
        Query countQuery;
        if (pageable.isPaged()) {
            String countSql = "select COUNT(*) from (" + sqlSelect + ") as total";
            if (pageable.getSort().isSorted()) {
                sqlSelect.append(" ORDER BY udc.")
                        .append(pageable.getSort().toString().replace(":", " "))
                        .append(", udc.id desc");
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
            nativeQuery = entityManager.createNativeQuery(sqlSelect + " order by ctr.CREATED_DATE desc, ctr.ID desc");
            if (!map.isEmpty()) {
                map.forEach(nativeQuery::setParameter);
            }
        }

        List<Object[]> resultList = nativeQuery.getResultList();
        List<ContractDTO> listObjectsToClass = DataUtils.convertListObjectsToClass(Arrays.asList(
                "userDetailContractId","contractCode", "contractType", "attachFile", "contractId", "userDetailId", "activeDate", "expiredDate", "signDate"),
                resultList,
                ContractDTO.class);
        DataPage<ContractDTO> dataPage = new DataPage<>();
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
