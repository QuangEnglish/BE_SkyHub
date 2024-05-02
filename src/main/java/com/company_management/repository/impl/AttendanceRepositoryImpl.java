package com.company_management.repository.impl;


import com.company_management.model.request.SearchAttendanceRequest;
import com.company_management.model.response.AttendanceResponse;
import com.company_management.model.response.DataPage;
import com.company_management.repository.AttendanceRepositoryCustom;
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
public class AttendanceRepositoryImpl implements AttendanceRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public DataPage<AttendanceResponse> search(SearchAttendanceRequest searchAttendanceRequest, Pageable pageable) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("select at.id as attendanceId,\n" +
                "       at.employee_id as employeeId,\n" +
                "       ud.employee_code as employeeCode,\n" +
                "       ud.employee_name as employeeName,\n" +
                "       de.department_name as departmentName,\n" +
                "       at.working_day as workingDay,\n" +
                "       at.check_in_time as checkInTime,\n" +
                "       at.check_out_time as checkOutTime,\n" +
                "       at.working_time as workingTime,\n" +
                "       at.working_point as workingPoint,\n" +
                "       at.total_penalty as totalPenalty\n" +
                "from attendance at\n" +
                "left join user_detail ud\n" +
                "on at.employee_id = ud.id\n" +
                "left join department de\n" +
                "on ud.department_id = de.id\n" +
                "where 1 = 1 \n");

        Map<String, Object> map = getStringObjectMap(searchAttendanceRequest, sqlSelect);
        Query nativeQuery;
        Query countQuery;
        if (pageable.isPaged()) {
            String countSql = "select COUNT(*) from (" + sqlSelect + ") as total";
            if (pageable.getSort().isSorted()) {
                sqlSelect.append(" ORDER BY at.")
                        .append(pageable.getSort().toString().replace(":", " "))
                        .append(", at.id desc");
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
            nativeQuery = entityManager.createNativeQuery(sqlSelect + " order by at.CREATED_DATE desc, at.ID desc");
            if (!map.isEmpty()) {
                map.forEach(nativeQuery::setParameter);
            }
        }
        List<Object[]> resultList = nativeQuery.getResultList();
        List<AttendanceResponse> lstAttendance = DataUtils.convertListObjectsToClass(
                Arrays.asList("attendanceId", "employeeId", "employeeCode", "employeeName",
                        "departmentName", "workingDay", "checkInTime", "checkOutTime", "workingTime", "workingPoint", "totalPenalty"),
                resultList,
                AttendanceResponse.class);
        DataPage<AttendanceResponse> dataPage = new DataPage<>();
        if (pageable.isPaged() && countQuery != null) {
            Long count = DataUtils.safeToLong(countQuery.getSingleResult());
            dataPage.setDataCount(count);
            dataPage.setPageSize(pageable.getPageSize());

            int pageCount = pageable.getPageSize() == 0 ? 1 : (int) Math.ceil((double) count / (double) pageable.getPageSize());
            dataPage.setPageCount(pageCount);
            dataPage.setPageIndex(pageable.getPageNumber());
        }
        dataPage.setData(lstAttendance);
        return dataPage;
    }

    private static Map<String, Object> getStringObjectMap(SearchAttendanceRequest searchAttendanceRequest, StringBuilder sqlSelect) {
        Map<String, Object> map = new HashMap<>();
        if (!DataUtils.isNullOrEmpty(searchAttendanceRequest.getEmployeeId())) {
            sqlSelect.append("  and (:employeeId IS NULL OR at.employee_id = :employeeId)");
            map.put("employeeId", searchAttendanceRequest.getEmployeeId());
        }
        if (!DataUtils.isNullOrEmpty(searchAttendanceRequest.getEmployeeCode())) {
            sqlSelect.append("  and (:employeeCode IS NULL OR ud.employee_code = :employeeCode)");
            map.put("employeeCode", searchAttendanceRequest.getEmployeeCode());
        }
        if (!DataUtils.isNullOrEmpty(searchAttendanceRequest.getWorkingDay())) {
            sqlSelect.append("  and (:workingDay IS NULL OR DATE(at.working_day) = :workingDay)");
            map.put("workingDay", searchAttendanceRequest.getWorkingDay());
        }
        if (!DataUtils.isNullOrEmpty(searchAttendanceRequest.getDepartmentId())) {
            sqlSelect.append("  and (:departmentId IS NULL OR de.id = :departmentId)");
            map.put("departmentId", searchAttendanceRequest.getDepartmentId());
        }
        return map;
    }
}
