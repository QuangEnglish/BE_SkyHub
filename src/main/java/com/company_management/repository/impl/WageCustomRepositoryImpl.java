package com.company_management.repository.impl;

import com.company_management.common.DataUtil;
import com.company_management.model.dto.WageDTO;
import com.company_management.model.response.PageResponse;
import com.company_management.repository.WageCustomRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class WageCustomRepositoryImpl implements WageCustomRepository {

    public final EntityManager em;
    @Override
    public PageResponse<WageDTO> getAllWage(WageDTO wageDTO, Pageable pageable) {
        StringBuilder querySTR = new StringBuilder();
        StringBuilder count = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        querySTR.append("SELECT w.wage_id, w.wage_base,\n" +
                "       w.is_active,\n" +
                "       w.wage_social_insurance,\n" +
                "       w.license_date,\n" +
                "       w.emp_sign,\n" +
                "       ud.full_name\n" +
                "FROM wage w\n" +
                "         LEFT JOIN\n" +
                "     user_detail ud on ud.id = w.user_detail_id\n" +
                "WHERE 1 = 1\n");
        if(!DataUtil.isNullOrEmpty(wageDTO.getEmpSign())){
            querySTR.append(" and w.emp_sign = :emp_sign");
            params.put("emp_sign", wageDTO.getEmpSign());
        }
        count.append("select count(*) from ( " + querySTR + " ) tmp ");
        Query query = em.createNativeQuery(querySTR.toString());
        Query countQuery = em.createNativeQuery(count.toString());
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        for (Map.Entry<String, Object> p : params.entrySet()) {
            query.setParameter(p.getKey(), p.getValue());
            countQuery.setParameter(p.getKey(), p.getValue());
        }
        List<Object[]> objects = query.getResultList();
        List<WageDTO> wageDTOS = new ArrayList<>();
        for (Object[] ob: objects) {
            WageDTO w = new WageDTO();
            w.setWageId(DataUtil.safeToLong(ob[0]));
            w.setWageBase(DataUtil.safeToDouble(ob[1]));
            w.setIsActive(DataUtil.safeToInt(ob[2]));
            w.setLicenseDate(DataUtil.safeToDate(ob[4]));
            w.setEmpSign(DataUtil.safeToString(ob[5]));
            w.setUserDetailName(DataUtil.safeToString(ob[6]));
            wageDTOS.add(w);
        }
        int index = 1;
        for (WageDTO rp : wageDTOS) {
            rp.setStt(index++);
        }
        Object count1 = countQuery.getSingleResult();
        PageResponse<WageDTO> response = new PageResponse<>();
        response.setDataList(wageDTOS);
        response.setPageIndex(pageable.getPageNumber());
        response.setPageSize(pageable.getPageSize());
        Page<WageDTO> page = new PageImpl<>(wageDTOS, pageable, Long.parseLong(count1.toString()));
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        return response;
    }
}
