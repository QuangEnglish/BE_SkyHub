package com.company_management.common;

public class ErrorCode {

    public static final String OK = "200";
    public static final ObjectError SERVER_ERROR = new ObjectError("EX001", Constants.SERVER_ERROR);
    public static final ObjectError VALID_OBJ = new ObjectError("EX002", "Lỗi valid dữ liệu");
    public static final ObjectError CREATED_FAIL = new ObjectError("EX003", Constants.CREATED_FAIL);
    public static final ObjectError UPDATED_FAIL = new ObjectError("EX004", Constants.UPDATED_FAIL);
    public static final ObjectError NOT_FOUND_OBJECT = new ObjectError("EX005", Constants.NOT_FOUND_OBJECT);
    public static final ObjectError SELECT_FAIL = new ObjectError("EX006", Constants.SELECT_FAIL);
    public static final ObjectError DELETED_ALERT_FAIL = new ObjectError("EX007", "Bật/Tắt cảnh báo không thành công");
    public static final ObjectError DOWNLOAD_FAIL = new ObjectError("EX007", Constants.DOWNLOAD_FAIL);
    public static final ObjectError UPLOADED_FAIL = new ObjectError("EX008", Constants.UPLOADED_FAIL);
    public static final ObjectError DELETED_FAIL = new ObjectError("EX009", Constants.DELETED_FAIL);


    public static final ObjectError CREATED_OK = new ObjectError("OK", Constants.CREATED_OK);
    public static final ObjectError UPDATED_OK = new ObjectError("OK", Constants.UPDATED_OK);
    public static final ObjectError DELETED_OK = new ObjectError("OK", Constants.DELETED_OK);

    public static final ObjectError ADD_OK = new ObjectError("OK", Constants.ADD_OK);
    public static final ObjectError ADD_FAIL = new ObjectError("EX008", Constants.ADD_FAIL);

}
