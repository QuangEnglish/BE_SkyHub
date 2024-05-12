package com.company_management.repository.impl;


import com.company_management.model.request.SearchAttendanceRequest;
import com.company_management.model.response.*;
import com.company_management.repository.AttendanceRepositoryCustom;
import com.company_management.utils.DataUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AttendanceRepositoryImpl implements AttendanceRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;


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

    @Override
    public List<AttendanceExportExcelResponse> searchExport(SearchAttendanceRequest searchAttendanceRequest) {
        createTemporaryTable(searchAttendanceRequest.getWorkingDay());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(searchAttendanceRequest.getWorkingDay());
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        StringBuilder sqlSelect = new StringBuilder();

        sqlSelect.append("SELECT ud.id as employeeId,\n" +
                "       ud.employee_code as employee_code,\n" +
                "       ud.employee_name as employeeName,\n" +
                "       COALESCE(SUM(at.total_penalty), 0) AS totalPenalty,\n" +
                "       COALESCE(SUM(at.working_point), 0) AS workingPoint,\n" +
                "       COALESCE(SUM(at.working_time), 0) AS workingTime,\n" +
                "       COALESCE(SUM(al.total_time), 0) AS totalTimeLeave,\n" +
                "       COALESCE(SUM(ao.total_time), 0) AS totalTimeOt\n" +
                "FROM user_detail ud\n" +
                "         LEFT JOIN (\n" +
                "    SELECT employee_id,\n" +
                "           SUM(total_penalty) AS total_penalty,\n" +
                "           SUM(working_point) AS working_point,\n" +
                "           SUM(working_time) AS working_time\n" +
                "    FROM attendance\n" +
                "    WHERE MONTH(working_day) = :month AND YEAR(working_day) = :year\n" +
                "    GROUP BY employee_id\n" +
                ") at ON ud.id = at.employee_id\n" +
                "         LEFT JOIN (\n" +
                "    SELECT employee_id,\n" +
                "           SUM(total_time) AS total_time\n" +
                "    FROM attendance_leave\n" +
                "    WHERE MONTH(end_day) = :month AND YEAR(end_day) = :year\n" +
                "    GROUP BY employee_id\n" +
                ") al ON ud.id = al.employee_id\n" +
                "         LEFT JOIN (\n" +
                "    SELECT employee_id,\n" +
                "           SUM(total_time) AS total_time\n" +
                "    FROM attendance_ot\n" +
                "    WHERE MONTH(start_day) = :month AND YEAR(start_day) = :year\n" +
                "    GROUP BY employee_id\n" +
                ") ao ON ud.id = ao.employee_id\n" +
                "WHERE 1=1\n" +
                "AND (:employeeId is null or ud.id = :employeeId)\n" +
                "GROUP BY ud.id " +
                "HAVING\n" +
                "    totalPenalty != 0;");

        Map<String, Object> mapJoin = new HashMap<>();
        mapJoin.put("employeeId", searchAttendanceRequest.getEmployeeId());
        mapJoin.put("month", month);
        mapJoin.put("year", year);

        Query nativeQueryJoin = entityManager.createNativeQuery(sqlSelect.toString());
        if (!mapJoin.isEmpty()) {
            mapJoin.forEach(nativeQueryJoin::setParameter);
        }

        List<Object[]> resultList = nativeQueryJoin.getResultList();
        List<AttendanceExportExcelResponse> lstAttendanceExportExcelResponses = DataUtils.convertListObjectsToClass(
                Arrays.asList("employeeId", "employeeCode", "employeeName", "totalPenalty",
                        "workingPoint", "workingTime", "totalTimeLeave", "totalTimeOt"),
                resultList,
                AttendanceExportExcelResponse.class);
        return lstAttendanceExportExcelResponses;
    }

    @Override
    public List<String> listCheckIn(Long employeeId) {
        StringBuilder sqlSelectJoin = new StringBuilder();
        sqlSelectJoin.append("SELECT \n" +
                "    COALESCE(DATE_FORMAT(attendance.check_in_time, '%H:%i'), 'V') AS checkIn\n" +
                "FROM\n" +
                "    temporary_table\n" +
                "        LEFT JOIN\n" +
                "    attendance ON temporary_table.day = DATE(attendance.check_in_time)\n" +
                "        AND (attendance.employee_id IS NULL OR employee_id = :employeeId)\n" +
                "WHERE\n" +
                "        1 = 1\n" +
                "GROUP BY\n" +
                "    temporary_table.day\n" +
                "ORDER BY day ASC;");
        Map<String, Object> mapJoin = new HashMap<>();
        mapJoin.put("employeeId", employeeId);

        Query nativeQueryJoin = entityManager.createNativeQuery(sqlSelectJoin.toString());
        if (!mapJoin.isEmpty()) {
            mapJoin.forEach(nativeQueryJoin::setParameter);
        }
        List<String> resultList = nativeQueryJoin.getResultList();
//        List<String> stringList = new ArrayList<>();
//        for (Object[] objArray : resultList) {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (int i = 0; i < objArray.length; i++) {
//                stringBuilder.append(objArray[i].toString());
//                if (i < objArray.length - 1) {
//                    stringBuilder.append(", ");
//                }
//            }
//            stringList.add(stringBuilder.toString());
//        }
        return resultList;
    }

    @Override
    public List<String> listCheckOut(Long employeeId) {
        StringBuilder sqlSelectJoin = new StringBuilder();
        sqlSelectJoin.append("SELECT \n" +
                "    COALESCE(DATE_FORMAT(attendance.check_out_time, '%H:%i'), 'V') AS checkOut\n" +
                "FROM\n" +
                "    temporary_table\n" +
                "        LEFT JOIN\n" +
                "    attendance ON temporary_table.day = DATE(attendance.check_out_time)\n" +
                "        AND (attendance.employee_id IS NULL OR employee_id = :employeeId)\n" +
                "WHERE\n" +
                "        1 = 1\n" +
                "GROUP BY\n" +
                "    temporary_table.day\n" +
                "ORDER BY day ASC;");
        Map<String, Object> mapJoin = new HashMap<>();
        mapJoin.put("employeeId", employeeId);

        Query nativeQueryJoin = entityManager.createNativeQuery(sqlSelectJoin.toString());
        if (!mapJoin.isEmpty()) {
            mapJoin.forEach(nativeQueryJoin::setParameter);
        }
        List<String> resultList = nativeQueryJoin.getResultList();
//        List<String> stringList = new ArrayList<>();
//        for (Object[] objArray : resultList) {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (int i = 0; i < objArray.length; i++) {
//                stringBuilder.append(objArray[i].toString());
//                if (i < objArray.length - 1) {
//                    stringBuilder.append(", ");
//                }
//            }
//            stringList.add(stringBuilder.toString());
//        }
        return resultList;
    }

    private static Map<String, Object> getStringObjectMap(SearchAttendanceRequest
                                                                  searchAttendanceRequest, StringBuilder sqlSelect) {
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

//    private static Map<String, Object> getStringObjectMapMonthYear(SearchAttendanceRequest searchAttendanceRequest, StringBuilder sqlSelect) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(searchAttendanceRequest.getWorkingDay());
//        int month = calendar.get(Calendar.MONTH) + 1;
//        int year = calendar.get(Calendar.YEAR);
//        Map<String, Object> map = new HashMap<>();
//        sqlSelect.append("  SET @month = :month; ");
//        map.put("month", month);
//        sqlSelect.append("  SET @year = :year; ");
//        map.put("year", year);
//        return map;
//    }

    private void createTemporaryTable(Date workingDay) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("CREATE TEMPORARY TABLE temporary_table (\n" +
                "    day DATE\n" +
                ");\n");
        jdbcTemplate.execute(String.valueOf(sqlSelect));

        StringBuilder sqlSeleInsert = new StringBuilder();
        sqlSeleInsert.append(
                "INSERT INTO temporary_table (day)\n" +
                        " SELECT DATE_ADD(CONCAT(?, '-', LPAD(?, 2, '0'), '-01'), INTERVAL (t.digit + (t10.digit * 10)) DAY)\n" +
                        "FROM\n" +
                        "    (SELECT 0 AS digit\n" +
                        "     UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4\n" +
                        "     UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS t\n" +
                        "    CROSS JOIN\n" +
                        "    (SELECT 0 AS digit UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4\n" +
                        "     UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS t10\n" +
                        "WHERE DATE_ADD(CONCAT(?, '-', LPAD(?, 2, '0'), '-01'), INTERVAL (t.digit + (t10.digit * 10)) DAY) <= LAST_DAY(CONCAT(?, '-', LPAD(?, 2, '0'), '-01'));");


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(workingDay);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        jdbcTemplate.update(String.valueOf(sqlSeleInsert), year, month, year, month, year, month);
    }
}
