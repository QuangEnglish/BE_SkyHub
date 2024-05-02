package com.company_management.repository.impl;

import com.company_management.model.dto.AttendanceOTDTO;
import com.company_management.model.request.SearchAttendanceOTRequest;
import com.company_management.model.response.DataPage;
import com.company_management.repository.AttendanceOTRepositoryCustom;
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
public class AttendanceOTRepositoryImpl implements AttendanceOTRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DataPage<AttendanceOTDTO> search(SearchAttendanceOTRequest searchOTRequest, Pageable pageable) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("select le.id as attendanceOtID,\n" +
                "       le.start_day as startDay,\n" +
                "       le.employee_id as employeeId,\n" +
                "       ud.employee_code as employeeCode,\n" +
                "       ud.employee_name as employeeName,\n" +
                "       le.start_time as startTime,\n" +
                "       le.end_time as endTime,\n" +
                "       le.description_ot as descriptionOt,\n" +
                "       le.total_time as totalTime,\n" +
                "       le.follow_id as followId,\n" +
                "       ut.employee_code as followCode,\n" +
                "       ut.employee_name as followName,\n" +
                "       le.is_active as isActive\n" +
                "from attendance_ot le\n" +
                "left join user_detail ud on le.employee_id = ud.id\n" +
                "left join user_detail ut on le.follow_id = ut.id\n" +
                "where 1 = 1 and (le.is_active = 1 or le.is_active = 2 or le.is_active = 3) \n");

        Map<String, Object> map = getStringObjectMap(searchOTRequest, sqlSelect);
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
        List<AttendanceOTDTO> lstOT = DataUtils.convertListObjectsToClass(
                Arrays.asList("attendanceOtID", "startDay", "employeeId", "employeeCode", "employeeName", "startTime",
                        "endTime", "descriptionOt", "totalTime", "followId", "followCode", "followName", "isActive"),
                resultList,
                AttendanceOTDTO.class);
        DataPage<AttendanceOTDTO> dataPage = new DataPage<>();
        if (pageable.isPaged() && countQuery != null) {
            Long count = DataUtils.safeToLong(countQuery.getSingleResult());
            dataPage.setDataCount(count);
            dataPage.setPageSize(pageable.getPageSize());

            int pageCount = pageable.getPageSize() == 0 ? 1 : (int) Math.ceil((double) count / (double) pageable.getPageSize());
            dataPage.setPageCount(pageCount);
            dataPage.setPageIndex(pageable.getPageNumber());
        }
        dataPage.setData(lstOT);
        return dataPage;
    }

    @Override
    public List<AttendanceOTDTO> searchExport(SearchAttendanceOTRequest searchOTRequest, Pageable pageable) {
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

        Map<String, Object> map = getStringObjectMap(searchOTRequest, sqlSelect);
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
                Arrays.asList("attendanceOtID", "startDay", "employeeId", "employeeCode", "employeeName", "startTime",
                        "endTime", "descriptionOt", "totalTime", "followId", "followCode", "followName", "isActive"),
                nativeQuery.getResultList(),
                AttendanceOTDTO.class);
    }

    private static Map<String, Object> getStringObjectMap(SearchAttendanceOTRequest searchOTRequest, StringBuilder sqlSelect) {
        Map<String, Object> map = new HashMap<>();
        if (!DataUtils.isNullOrEmpty(searchOTRequest.getStartDay())) {
            sqlSelect.append("  and le.start_day >= :startDay");
            map.put("startDay", searchOTRequest.getStartDay());
        }
        if (!DataUtils.isNullOrEmpty(searchOTRequest.getEmployeeId())) {
            sqlSelect.append("  and le.employee_id = :employeeId");
            map.put("employeeId", searchOTRequest.getEmployeeId());
        }
        if (!DataUtils.isNullOrEmpty(searchOTRequest.getIsActive())) {
            sqlSelect.append("  and le.is_active = :isActive");
            map.put("isActive", searchOTRequest.getIsActive());
        }
        return map;
    }

}
