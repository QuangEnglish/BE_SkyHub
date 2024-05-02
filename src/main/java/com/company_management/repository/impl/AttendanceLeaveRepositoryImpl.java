package com.company_management.repository.impl;

import com.company_management.model.dto.AttendanceLeaveDTO;
import com.company_management.model.request.SearchLeaveRequest;
import com.company_management.model.response.DataPage;
import com.company_management.repository.AttendanceLeaveRepositoryCustom;
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
public class AttendanceLeaveRepositoryImpl implements AttendanceLeaveRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DataPage<AttendanceLeaveDTO> search(SearchLeaveRequest searchLeaveRequest, Pageable pageable) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("select le.id as leaveID,\n" +
                "       le.leave_category as leaveCategory,\n" +
                "       le.employee_id as employeeId,\n" +
                "       ud.employee_code as employeeCode,\n" +
                "       ud.employee_name as employeeName,\n" +
                "       le.start_day as startDay,\n" +
                "       le.end_day as endDay,\n" +
                "       le.description as description,\n" +
                "       le.total_time as totalTime,\n" +
                "       le.tracker_id as trackerId,\n" +
                "       ut.employee_code as trackerCode,\n" +
                "       ut.employee_name as trackerName,\n" +
                "       le.reviewer_id as reviewerId,\n" +
                "       ur.employee_code as reviewerCode,\n" +
                "       ur.employee_name as reviewerName,\n" +
                "       le.is_active as isActive\n" +
                "from attendance_leave le\n" +
                "left join user_detail ud on le.employee_id = ud.id\n" +
                "left join user_detail ut on le.tracker_id = ut.id\n" +
                "left join user_detail ur on le.reviewer_id = ur.id\n" +
                "where 1 = 1 and (le.is_active = 1 or le.is_active = 2 or le.is_active = 3) \n");

        Map<String, Object> map = getStringObjectMap(searchLeaveRequest, sqlSelect);
        Query nativeQuery;
        Query countQuery;
        if (pageable.isPaged()) {
            String countSql = "select COUNT(*) from (" + sqlSelect + ") as total";
            if (pageable.getSort().isSorted()) {
                sqlSelect.append(" ORDER BY le.")
                        .append(pageable.getSort().toString().replace(":", " "))
                        .append(", le.id desc");
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
            nativeQuery = entityManager.createNativeQuery(sqlSelect + " order by le.CREATED_DATE desc, le.ID desc");
            if (!map.isEmpty()) {
                map.forEach(nativeQuery::setParameter);
            }
        }
        List<Object[]> resultList = nativeQuery.getResultList();
        List<AttendanceLeaveDTO> lstLeave = DataUtils.convertListObjectsToClass(
                Arrays.asList("leaveID", "leaveCategory", "employeeId", "employeeCode", "employeeName", "startDay",
                        "endDay", "description", "totalTime", "trackerId", "trackerCode", "trackerName",
                        "reviewerId", "reviewerCode", "reviewerName", "isActive"),
                resultList,
                AttendanceLeaveDTO.class);
        DataPage<AttendanceLeaveDTO> dataPage = new DataPage<>();
        if (pageable.isPaged() && countQuery != null) {
            Long count = DataUtils.safeToLong(countQuery.getSingleResult());
            dataPage.setDataCount(count);
            dataPage.setPageSize(pageable.getPageSize());

            int pageCount = pageable.getPageSize() == 0 ? 1 : (int) Math.ceil((double) count / (double) pageable.getPageSize());
            dataPage.setPageCount(pageCount);
            dataPage.setPageIndex(pageable.getPageNumber());
        }
        dataPage.setData(lstLeave);
        return dataPage;
    }

    @Override
    public List<AttendanceLeaveDTO> searchExport(SearchLeaveRequest searchLeaveRequest, Pageable pageable) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("select le.id as leaveID,\n" +
                "       le.leave_category as leaveCategory,\n" +
                "       le.employee_id as employeeId,\n" +
                "       ud.employee_code as employeeCode,\n" +
                "       ud.employee_name as employeeName,\n" +
                "       le.start_day as startDay,\n" +
                "       le.end_day as endDay,\n" +
                "       le.description as description,\n" +
                "       le.total_time as totalTime,\n" +
                "       le.tracker_id as trackerId,\n" +
                "       ut.employee_code as trackerCode,\n" +
                "       ut.employee_name as trackerName,\n" +
                "       le.reviewer_id as reviewerId,\n" +
                "       ur.employee_code as reviewerCode,\n" +
                "       ur.employee_name as reviewerName,\n" +
                "       le.is_active as isActive\n" +
                "from attendance_leave le\n" +
                "left join user_detail ud on le.employee_id = ud.id\n" +
                "left join user_detail ut on le.tracker_id = ut.id\n" +
                "left join user_detail ur on le.reviewer_id = ur.id\n" +
                "where 1 = 1\n");

        Map<String, Object> map = getStringObjectMap(searchLeaveRequest, sqlSelect);
        if (pageable.isPaged() && pageable.getSort().isSorted()) {
            sqlSelect.append(" ORDER BY le.")
                    .append(pageable.getSort().toString().replace(":", " "))
                    .append(", le.id desc");
        }
        Query nativeQuery = entityManager.createNativeQuery(sqlSelect.toString());

        if (!map.isEmpty()) {
            map.forEach(nativeQuery::setParameter);
        }
        return DataUtils.convertListObjectsToClass(
                Arrays.asList("leaveID", "leaveCategory", "employeeId", "employeeCode", "employeeName", "startDay",
                        "endDay", "description", "totalTime", "trackerId", "trackerCode", "trackerName",
                        "reviewerId", "reviewerCode", "reviewerName", "isActive"),
                nativeQuery.getResultList(),
                AttendanceLeaveDTO.class);
    }

    private static Map<String, Object> getStringObjectMap(SearchLeaveRequest searchLeaveRequest, StringBuilder sqlSelect) {
        Map<String, Object> map = new HashMap<>();
        if (!DataUtils.isNullOrEmpty(searchLeaveRequest.getStartDay())) {
            sqlSelect.append("  and le.start_day >= :startDay");
            map.put("startDay", searchLeaveRequest.getStartDay());
        }
        if (!DataUtils.isNullOrEmpty(searchLeaveRequest.getEndDay())) {
            sqlSelect.append("  and le.end_day <= :endDay");
            map.put("endDay", searchLeaveRequest.getEndDay());
        }
        if (!DataUtils.isNullOrEmpty(searchLeaveRequest.getIsActive())) {
            sqlSelect.append("  and le.is_active = :isActive");
            map.put("isActive", searchLeaveRequest.getIsActive());
        }
        return map;
    }

}
