package com.company_management.utils;

import com.company_management.model.dto.AttendanceLeaveDTO;
import com.company_management.model.response.ExportPdfEmployeeResponse;

import java.util.HashMap;
import java.util.Map;

public class LogisticsMailUtils {


    public static final String BUSINESS_TRIP_DETAIL_URL = "/#/hau-can/chi-tiet-dang-ky-cong-tac?id=";
    public static final String BUSINESS_TRIP_EVALUATE_URL = "/#/hau-can/chi-tiet-danh-gia-dich-vu?id=";


    public static Map<String, Object> sendMailToStaffOnCreate(
            String staffName,
            String title,
            String startDate,
            String endDate
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("staffName", staffName);
        params.put("title", title);
        params.put("startDate", startDate);
        params.put("endDate", endDate);

        return params;
    }

    public static Map<String, Object> sendMailToCode(String codeRegister) {
        Map<String, Object> params = new HashMap<>();
        params.put("codeRegister", codeRegister);
        return params;
    }

    public static Map<String, Object> sendMailToAttendanceLeave(AttendanceLeaveDTO attendanceLeaveDTO) {
        Map<String, Object> params = new HashMap<>();
        params.put("leaveCategory", attendanceLeaveDTO.getLeaveCategory());
        params.put("startDayConvert", attendanceLeaveDTO.getStartDayConvert());
        params.put("endDayConvert", attendanceLeaveDTO.getEndDayConvert());
        params.put("totalTime", attendanceLeaveDTO.getTotalTime());
        params.put("description", attendanceLeaveDTO.getDescription());
        params.put("employeeId", attendanceLeaveDTO.getEmployeeId());
        params.put("employeeName", attendanceLeaveDTO.getEmployeeName());
        params.put("reviewerId", attendanceLeaveDTO.getReviewerId());
        params.put("reviewerName", attendanceLeaveDTO.getReviewerName());
        params.put("trackerId", attendanceLeaveDTO.getTrackerId());
        params.put("trackerName", attendanceLeaveDTO.getTrackerName());
        return params;
    }

    public static Map<String, Object> sendExportPdfEmployee(ExportPdfEmployeeResponse exportPdfEmployeeResponse) {
        Map<String, Object> params = new HashMap<>();
//        params.put("leaveCategory", attendanceLeaveDTO.getLeaveCategory());
//        params.put("startDayConvert", attendanceLeaveDTO.getStartDayConvert());
//        params.put("endDayConvert", attendanceLeaveDTO.getEndDayConvert());
//        params.put("totalTime", attendanceLeaveDTO.getTotalTime());
//        params.put("description", attendanceLeaveDTO.getDescription());
//        params.put("employeeId", attendanceLeaveDTO.getEmployeeId());
//        params.put("employeeName", attendanceLeaveDTO.getEmployeeName());
//        params.put("reviewerId", attendanceLeaveDTO.getReviewerId());
//        params.put("reviewerName", attendanceLeaveDTO.getReviewerName());
//        params.put("trackerId", attendanceLeaveDTO.getTrackerId());
//        params.put("trackerName", attendanceLeaveDTO.getTrackerName());
        return params;
    }

    /*
     * Gửi mail cho bên cung cấp dịch vụ chuyến công tác khi yêu cầu công tác được tạo mới
     * */
    public static Map<String, Object> sendMailToHotelProvider(
            String hotelName,
            Long businessTripId
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("hotelName", hotelName);
        params.put("businessTripId", businessTripId);

        return params;
    }

    /*
     * Gửi mail cho bên cung cấp dịch vụ chuyến công tác khi yêu cầu công tác được tạo mới
     * */
    public static Map<String, Object> sendMailToCarProvider(
            String driverName,
            Long businessTripId
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("driverName", driverName);
        params.put("businessTripId", businessTripId);

        return params;
    }

    /*
     * Gửi mail cho nhân viên tham gia chuyến công tác khi yêu cầu công tác được cung cấp
     * */
    public static Map<String, Object> sendMailToStaffOnProvided(
            String staffName,
            String title,
            String startDate,
            String endDate,
            String detailUrl
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("staffName", staffName);
        params.put("title", title);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("detailUrl", detailUrl);

        return params;
    }


    /*
     * Gửi mail cho người tạo chuyến công tác khi yêu cầu công tác được cung cấp
     * */
    public static Map<String, Object> sendMailToCreatorOnProvided(
            String staffName,
            String title,
            String startDate,
            String endDate,
            String detailUrl
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("staffName", staffName);
        params.put("title", title);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("detailUrl", detailUrl);

        return params;
    }

    /*
     * Gửi mail cho người tạo và nhân viên tham gia công tác khi dịch vụ được đánh giá bới người khác
     * và nhân viên tham gia công tác hoặc người tạo ra chuyến công tác chưa đánh giá
     * */
    public static Map<String, Object> sendMailToCreatorAndStaffOnEvaluated(
            String staffName,
            String evaluatedUser,
            String title,
            String startDate,
            String endDate,
            String evaluationUrl
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("staffName", staffName);
        params.put("evaluatedUser", evaluatedUser);
        params.put("title", title);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("evaluationUrl", evaluationUrl);

        return params;
    }
}
