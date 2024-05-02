package com.company_management.common;

public class Constants {
    public final static String DATE_FORMAT = "yyyy-MM-dd";

    public static final Integer STATUS_ACTIVE_INT = 1;
    public static final Integer STATUS_INACTIVE_INT = 0;

    public static final String SERVER_ERROR = "Đã có lỗi xáy ra từ hệ thống";

    public static final String NEW = "NEW";

    public static final String OPEN = "OPEN";

    public static final String CLOSE = "CLOSE";

    public static final String CREATED_OK = "Thêm mới thành công!";

    public static final String UPDATED_OK = "Cập nhật thành công!";
    public static final String ADD_OK = "Thêm mới thành công!";

    public static final String CREATED_FAIL = "Đã xảy ra lỗi, không thể thêm mới bản ghi!";

    public static final String UPDATED_FAIL = "Đã xảy ra lỗi, không thể cập nhật bản ghi!";

    public static final String ADD_FAIL = "Đã xảy ra lỗi, không thể thêm mới bản ghi!";

    public static final String SELECT_FAIL = "Đã có lỗi trong quá trình xử lý, vui lòng thực hiện vào lúc khác!";

    public static final String NOT_FOUND_OBJECT = "Không tìm thấy dữ liệu!";

    public static final String DELETED_OK = "Xóa đối tượng thành công!";

    public static final String DELETED_FAIL = "Không thể xóa đối tượng được chọn!";

    public static final String DOWNLOAD_FAIL = "Không thể download file yêu cầu!";
    public static final String UPLOADED_FAIL = "Đã có lỗi xảy ra khi upload file!";
    public static final String KPI_INCIDENT = "KPI_INCIDENT";
    public static final String KPI_CLOSE = "KPI_CLOSE";
    public static final String KPI_TIME_PROCESS = "KPI_TIME_PROCESS";
    public static final String KPI_PASSED = "Đạt";
    public static final String KPI_NOT_PASSED = "Không đạt";

    public static final class ROLE {
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }
}
