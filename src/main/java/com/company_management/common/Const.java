package com.company_management.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Const {
    public final static String CHECK_ISDN_ADD_PREFIX = "^0+(?!$)|^84(?!$)";

    public final static String REGEX_BASE64 = "^(?:[A-Za-z0-9+\\/]{4})*(?:[A-Za-z0-9+\\/]{2}==|[A-Za-z0-9+\\/]{3}=)?$";

    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_FORMAT2 = "dd-MM-yyyy HH:mm:ss";
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String DATE_FORMAT_MONTHS = "MM/yyyy";
    public final static String DATE_FORMAT2 = "dd-MM-yyyy";
    public final static String MONTH_FORMAT = "yyyy-MM";
    public final static String DATE_FORMAT3 = "dd/MM/yyyy";

    public final static String ADMIN = "ADMIN";
    public final static String USER_NAME = "TRANGNTH72";
    public final static String UNIT_TYPE_2_0 = "2.0";
    public final static String UNIT_TYPE_2_1 = "2.1";
    public final static String UNIT_TYPE_2_2 = "2.2";
    public final static String UNIT_TYPE_2_3 = "2.3";

    public final static String EDU_ORDER_0 = "0";
    public final static String EDU_ORDER_1 = "1";
    public final static String EDU_ORDER_2 = "2";
    public final static String EDU_ORDER_3 = "3";
    public final static String EDU_ORDER_4 = "4";

    //namnh - add thông tin thay đổi khi sửa đổi am
    public final static String AM_CHANGE = " Thay thế sang ";
    //namnh - add thông tin thay đổi khi sửa đổi am

    public final static class STATUS {
        public final static String INACTIVE = "0";
        public final static String ACTIVE = "1";
        public final static String CANCELED = "2";
        public final static String DELETED = "3";
        public final static String PAUSE = "4"; // tạm dừng hoạt động
    }

    public final static class STATUS_RESPONSE {
        public final static String SUCCESS = "success";
        public final static String FAIL = "fail";
    }

    public final static class AREA_CODE_LENGTH {
        public final static Integer PROVINCE = 4;
        public final static Integer DISTRICT = 7;
        public final static Integer PRECINCT = 10;
    }

    public final static class PARTNER_STATUS {
        public String ACTIVE = "1";
    }

    public final static String DOCUMENT_CODE_PREFIX = "DOC-";
    public final static char PAD_CHAR = '0';

    public final static class DOC_OBJ_MAP_TYPE {
        public final static String PRODUCT = "PRODUCT";
        public final static String PARTNER = "PARTNER";
        public final static String PRODUCT_OFFER = "PRODUCT_OFFER";
        public final static String PARTNER_REVENUE_SHARE = "PARTNER_REVENUE_SHARE";
        public final static String CUSTOMER = "CUSTOMER";
        public final static String CONTRACT = "CONTRACT";
        public final static String SUBSCRIBER = "SUBSCRIBER";
        public final static String CUSTOMER_ORDER = "CUSTOMER_ORDER";
        public final static String WORK = "WORK";
        public final static String PROMOTION_CAMPAIGN = "PROMOTION_CAMPAIGN";
        public final static String DRUG_STORE = "DRUG_STORE";
    }

    public final static class GLOBAL_LIST_CODE {
        public final static String PARTNER_STATUS = "PARTNER_STATUS";
        public final static String PARTNER_SHARE_TYPE = "PARTNER_SHARE_TYPE";
        public final static String PRODUCT_GROUP = "PRODUCT_GROUP";
        public final static String PRODUCT_TYPE = "PRODUCT_TYPE";
        public final static String CUSTOMER_TYPE = "CUSTOMER_TYPE";
        public final static String CUSTOMER_BUS_TYPE = "CUSTOMER_BUS_TYPE";
        //        public final static String ID_TYPE = "ID_TYPE";
        public final static String ID_TYPE_INDIVIDUAL = "ID_TYPE_INDIVIDUAL";
        public final static String ID_TYPE_CORPORATION = "ID_TYPE_CORPORATION";
        public final static String PRODUCT_STATUS = "PRODUCT_STATUS";
        public final static String PRODUCT_OFFER_STATUS = "PRODUCT_OFFER_STATUS";
        public final static String VOL_LIMITATION_TYPE = "VOL_LIMITATION_TYPE";
        public final static String PRODUCT_OFFER_TYPE = "PRODUCT_OFFER.PRODUCT_OFFER_TYPE";
        public final static String PRICE_LIST_TYPE = "PRICE_LIST_TYPE";
        public final static String UNIT_STORAGE = "UNIT_STORAGE";
        public final static String DOCUMENT_STATUS = "DOCUMENT_STATUS";
        public final static String VAT_TYPE = "VAT_TYPE";
        public final static String SEX = "SEX";
        public final static String ORDER_TYPE = "ORDER_TYPE";
        public final static String ORDER_ACTION_TYPE = "ORDER_ACTION_TYPE";
        public final static String ORDER_STATUS = "ORDER_STATUS";
        public final static String SALE_CHANNEL = "SALE_CHANEL";
        public final static String BILL_NOTIFICATION_METHOD = "BILL_NOTIFICATION_METHOD";
        public final static String DOCUMENT_TYPE = "DOCUMENT_TYPE";
        public final static String PRODUCT_CUST_SEGM = "PRODUCT_CUST_SEGM";
        public final static String PROVISION_TYPE = "PROVISION_TYPE";
        public final static String CUSTOMER_EXT_MEDICAL_MEDICAL_ORG_TYPE = "CUSTOMER_EXT_MEDICAL.MEDICAL_ORG_TYPE";
        public final static String PAYMENT_METHOD = "PAYMENT_METHOD";
        public final static String CONTRACT_STATUS = "CONTRACT_STATUS";
        public final static String ITEM_CLASSIFICATION = "ITEM_CLASSIFICATION";
        public final static String PRODUCT_FORM = "PRODUCT_FORM";
        public final static String DEVICE_TYPE = "DEVICE_TYPE";
        public final static String EXCHANGE_UNIT = "EXCHANGE_UNIT";
        public final static String UNIT = "UNIT";
        public final static String FEE_UNIT = "FEE_UNIT";
        public final static String ACTION_FEE_TYPE = "ACTION_FEE_TYPE";
        public final static String ACTION_FEE_REASON = "FEE_REASON";
        public final static String SUBSCRIBER_DEVICE_DEVICE_CODE_FORMAT = "SUBSCRIBER_DEVICE.DEVICE_CODE_FORMAT";
        public final static String WORK_TYPE = "WORK_TYPE";
        public final static String WORK_STATUS = "WORK_STATUS";
        public final static String RECIPIENT = "RECIPIENT";
        public final static String ACTION_SMS = "ACTION_SMS";
        public final static String STATUS = "STATUS";
        public final static String FEE_TYPE = "FEE_TYPE";
        public final static String FEE_ACTION_TYPE = "FEE_ACTION_TYPE";
        public final static String COMBO_TYPE = "COMBO_TYPE";
        public final static String ACTION_FEE_CONFIG = "ACTION_FEE_CONFIG.TYPE";
        public final static String MCU_ACCOUNT_TYPE = "MCU_ACCOUNT_TYPE";
        public final static String YTHD_OBJECT_TYPE = "YTHD_OBJECT_TYPE";
        public final static String SCHOOL_LEVEL = "SCHOOL_LEVEL";
        //check tien trinh
        public final static String PROCESS_BLOCK_OPEN_SUBSCRIBER_BU2_AUTO = "blockOpenSubscriberBU2Auto";//subscriberId
        public final static String FIRSTRETRY_CANCEL_ORDER_NO_ACTION = "firstRetryCancelOrderNoAction";//customerOrderId
        public final static String SECONDRETRY_CANCEL_ORDER_NO_ACTION = "secondRetryCancelOrderNoAction";//customerOrderId
        public final static String FIRSTRETRY_CANCEL_ORDER_NO_ACCEPTANCECHARGE = "firstRetryCancelOrderNoAcceptanceCharge";//customerOrderId
        public final static String SECONDRETRY_CANCEL_ORDER_NO_ACCEPTANCECHARGE = "secondRetryCancelOrderNoAcceptanceCharge";//customerOrderId
        public final static String FIRSTRETRY_CANCEL_SUBSCRIBER_BLOCK_EXPIRE = "firstRetryCancelSubscriberBlockExpire";//subscriberId
        public final static String SECONDRETRY_CANCEL_SUBSCRIBER_BLOCK_EXPIRE = "secondRetryCancelSubscriberBlockExpire";//subscriberId
        public final static String BLOCK_OPEN_SUBSCRIBER_BU4_AUTO = "blockOpenSubscriberBU4Auto";//subscriberIdBccsId
        public final static String FIRSTRETRY_CANCEL_SUBSCRIBER_SUPPLEMENT_BLOCKEXPIRE = "firstRetryCancelSubscriberSupplementBlockExpire";//subscriberSupplementId
        public final static String TERMINATE_CONTRACT_BU2AUTO = "terminateContractBu2Auto";//customerContractId
        public final static String BLOCK_SUBSCRIBER_EXPIRE_SUBSCRIBER_PLAN = "blockSubscriberExpireSubscriberPlan";//customerContractId
        public final static String WARNING_EXPIRING_CONTRACT_AUTO = "warningExpiringContractAuto";//customerContractId
        //IIST-TUDN 080921 start
        public final static String WHITE_LIST_IP = "WHITE_LIST_IP";
        public final static String ACTION_SENSITIVE_DATA = "ACTION_SENSITIVE_DATA";
        //IIST-TUDN 080921 end


        public final static String NORMS_TYPE = "NORMS_TYPE"; //Loại định mức
        public final static String COSTS_TYPE = "COSTS_TYPE"; // Loại chi phí
        public final static String UNIT_NORM_TYPE = "UNIT_NORM_TYPE"; // Đơn vị định mức
        public final static String DOCUMENT_CODE = "DOCUMENT_CODE";
        public final static String CUSTOMER_TYPE_SAP = "CUSTOMER_TYPE_SAP";
    }

    public static class ORDER_TYPE {
        public final static String CONNECT = "1"; // phát triển mới
        public final static String AFTER_SALE = "2"; // Nghiệp vụ sau bán
        public final static String CONTRACT = "3"; // Hợp đồng
    }

    public static class CUSTOMER_STATUS {
        public final static String ACTIVE = "1";
        public final static String CANCELED = "3";

    }

    public static class CUSTOMER_CONTRACT_TYPE {
        public final static String CREATE_NEW = "1";
        public final static String DOCONNECT = "0";

    }

    public static class CUSTOMER_BUS_TYPE {
        public final static String PERSONAL = "1";
        public final static String BUSINESS = "2";
        public final static String FOREIGN = "3";
    }

    public static class PROVISIONING_STATUS {
        public final static String WAIT_FOR_CONNECT = "0";
        public final static String CONNECTED = "1";
    }

    public static class ORDER_STATUS {
        public final static String PENDING = "1";
        public final static String EXECUTED = "2";
        public final static String DELETED = "3";
    }

    public static class LICENSE_STATUS {
        public final static String UPDATED = "1";
    }

    public static class ORDER_ACTION_TYPE {
        public final static String CONNECT_CREATE_NEW_REQUEST = "0";               //Chờ phê duyệt
        public final static String CONNECT_WAIT_SURVEY = "1";               //Đấu nối - Chờ khảo sát
        public final static String CONNECT_WAIT_DEPLOY = "2";               //Đấu nối - Chờ triển khai
        public final static String CONNECT_WAIT_ACCEPTANCE = "3";           //Đấu nối - Chờ nghiệm thu
        public final static String CONNECT_WAIT_SYNC_BCCS = "4";            //Đấu nối - Chờ đồng bộ BCCS
        public final static String CONNECT_ACCEPTANCED = "5";               //Đấu nối - Đã nghiệm thu
        public final static String CONNECT_CANCEL = "6";                    //Đấu nối - Hủy yêu cầu
        public final static String AFTER_SALE_CHANGE_INFOR = "7";           //Sau bán - Thay đổi thông tin
        public final static String AFTER_SALE_CHANGE_PRODUCT_OFFER = "8";   //Sau bán - Thay đổi gói cước
        public final static String AFTER_SALE_EXTEND_PRODUCT_OFFER = "9";   //Sau bán - Gia hạn gói cước
        public final static String CONTRACT_CHANGE_INFOR_KHYC = "10";       //Hơp đồng - Thay đổi thông tin - KHYC
        public final static String CONTRACT_CHANGE_INFOR_CORRECT = "11";    //Hơp đồng - Thay đổi thông tin - Sửa sai
        public final static String CONNECT_CANCEL_TRANS = "12";             //Đấu nối - Hủy giao dịch
        public final static String CONTRACT_TRANSFER = "13";                //Hơp đồng chuyển nhượng
        public final static String CONTRACT_LIQUIDATION = "14";             //Hợp đồng - Thanh lý hợp đồng
        public final static String CONTRACT_EXTEND = "15";                  //Hợp đồng - Gia hạn hợp đồng
        public final static String AFTER_SALE_CANCEL = "16";                  //Sau bán - Hủy yêu cầu

        public final static String SPLIT_CONTRACT = "94";                //  hop dong - tach HD
        public final static String  CHANGEPRE_PAID_FEE_PROGRAM= "95";                // sau ban doi - CDT
        public final static String MERGE_CONTRACT = "93";                //  hop dong - gop HD

        public final static String AFTER_SALE_CHANGE_PREPAID_CHARGE = "17";   //Sau bán - Thay đổi cuoc dong truoc
        public final static String CONTRACT_CANCEL = "19";                  //Sau bán - Hợp đồng - Hủy yêu cầu
        public final static String AFTER_SALE_EXTEND_PREPAID_CHARGE = "96";   //Sau bán - Thay đổi cuoc dong truoc

        public final static String CONNECT_DOING_SURVEY = "20";             //20		Đấu nối - Đang khảo sát
        public final static String CONNECT_COMPLETE_SURVEY = "21";          //21		Đấu nối - Hoàn thành khảo sát
        public final static String CONNECT_CANCEL_SURVEY = "22";            //22		Đấu nối - Hủy yêu cầu khảo sát
        public final static String CONNECT_DOING_DEPLOY = "23";             //23		Đấu nối - Đang triển khai
        public final static String CONNECT_COMPLETE_DEPLOY = "24";          //24		Đấu nối - Hoàn thành triển khai
        public final static String CONNECT_CANCEL_DEPLOY = "25";            //25		Đấu nối - Hủy yêu cầu triển khai
        public final static String CONNECT_NOT_ELIGIBLE_FOR_DEPLOYMENT = "26";            //26		Đấu nối - Không đủ điều kiện triển khai

        public final static String AFTER_SALE_BLOCK = "30";
        public final static String AFTER_SALE_OPEN = "31";

        public final static String AFTER_SALE_ADD_USER = "40";              // 40       Sau bán - Thêm số lượng User Account
        public final static String AFTER_SALE_EXTEND_ACCOUNT = "41";        // 41       Sau bán - Mua thêm, gia hạn gói dịch vụ
        public final static String AFTER_SALE_ADD_ROOM = "42";              // 42       Sau bán - Thêm phòng họp
        public final static String AFTER_SALE_CANCEL_TRANS = "43";              // 43       Sau bán - Hủy giao dịch
        public final static String AFTER_SALE_CHANGE_DEVICE = "44";              // 44       Sau bán - Đổi thiết bị
        public final static String CREATE_NEW_CONTRACT = "45";                  // Tạo mới hợp đồng
        public final static String AFTER_SALE_BLOCK_USER = "100";              // 100   Sau bán - Chặn tài khoản
        public final static String AFTER_SALE_OPEN_USER = "101";                  //47  Sau bán - Mở tài khoản
        public final static String CONNECT_REQUEST_DESTROY_ORDER = "102";                  //102  Sau bán - Yêu cầu hủy
        public final static String ADDITIONAL_EXTEND_ACCOUNT = "48";                  //48  Sau bán - Mua bo sung
        public final static String CONNECT_WAITING_APPROVAL = "28";                  //28  Dau noi - Cho tich duyet
        public final static String AFTER_SALE_EXTEND_WAITING_APPROVAL = "29";                  //29  Sau ban - Mua them cho tich duyet
        public final static String AFTER_SALE_ADDITIONAL_WAITING_APPROVAL = "32";                  //32  Sau ban - Mua bo sung cho tich duyet

        public final static String CONNECT_WAIT_GRANT = "104";           // Đấu nối - Chờ cấp phát tài nguyên
        public final static String AFTER_SALE_REDUCE_NODE = "108";           // Sau bán - Giảm số node
        public final static String AFTER_SALE_EXTEND_NODE = "109";           // Sau bán - Mua thêm node

        //namnh - Thay đổi nvpt(am/ctv)
        public final static String CHANGE_NVPT = "111";      // Sửa thông tin AM
        //namnh - Thay đổi nvpt(am/ctv)
        public final static String CONNECT_WAIT_CONFIRM_CANCEL = "112";           // Đấu nối - Chờ duyệt hủy
        public final static String CONNECT_CANCELED_ACCEPTANCE = "113";           // Đấu nối - Đã hủy nghiệm thu
        public final static String CONNECT_REJECT_CANCELED_ACCEPTANCE = "114";          // Đấu nối - Từ chối hủy nghiệm thu
        public final static String AFTER_SALE_TERMINATE = "116";          // Sau bán - Hủy dịch vụ
        public final static String AFTER_SALE_BLOCK_VGPU = "117";          // Sau bán - Chặn dịch vụ
        public final static String AFTER_SALE_UN_BLOCK_VGPU = "118";          // Sau bán - Bỏ chặn dịch vụ
        public final static String CONNECT_ACCEPT_AUDIT = "119";           // Đấu nối - Đã tiếp nhận AUDIT
        public final static String CONNECT_ACCEPT_VHKT = "120";           // Đấu nối - Đã tiếp nhận VHKT hệ thống
        public final static String AFTER_SALE_EXTEND_STORAGE = "121";           // Sau bán - Mua thêm dung lượng
        public final static String AFTER_SALE_CANCEL_BAAS = "122";           // Sau bán - Hủy dịch vụ
    }

    public static class DOCUMENT_ACTION_TYPE {
        public final static String CONNECT_WAIT_ACCEPTANCE = "043";
        public final static String CONNECT_ACCEPTANCED = "045";
        public final static String AFTER_SALE_EXTEND_ACCOUNT = "041";
    }

    public static class SUBSCRIBER_STATUS {
        public final static String DONT_CONNECT = "1";                      //Chưa đấu nối
        public final static String ACTIVE = "2";                            //Đang hoạt động
        public final static String CANCELED = "3";                          //Dừng hoạt động
        public final static String CANCEL_DEPLOY = "4";                     // hủy triển khai
        public final static String SUPPEND_TERMINATE = "5";                 //Chặn thanh lý
        public final static String WAIT_DEPLOY = "6";                       //Đấu nối -  Chờ triển khai
        public final static String CONNECT_WAIT_ACCEPTANCE = "7";           //Chờ nghiệm thu
        public final static String DELETED = "8";
        public final static String PAUSE_OPERATION = "9"; // tạm dừng hoạt động
    }

    public static class SUBSCRIBER_DEVICE_STATUS {
        public final static String COMPLETE_SURVEY = "0";       //	Hoàn thành khảo sát
        public final static String WAIT_DEPLOY = "1";           //	Chờ trien khai
        public final static String CONNECT = "2";               //	Hoạt động
        public final static String CANCEL_NO_CONNECT = "3";     //	Hủy chưa đấu nối
        public final static String CANCEL_DEPLOY = "4";         //	Hủy triển khai
        public final static String CANCEL_CONNECT = "5";        //	Huy dau noi
        public final static String BLOCK_LIQUIDATION = "6";     //	Chan thanh ly
        public final static String DONE_DEPLOY = "7";           //	Da thanh ly
        public final static String DONE_LIQUIDATION = "8";      //	Da thanh ly
        public final static String INACTIVE = "9";
    }

    public static class SUBSCRIBER_SUPPLEMENT_STATUS {
        public final static String WAIT_DEPLOY = "6";                       //Chờ triển khai
        public final static String CONNECT_WAIT_ACCEPTANCE = "7";           //Chờ nghiệm thu
        public final static String ACTIVE = "2";                            //Đang hoạt động
        public final static String CANCEL_DEPLOY = "4";                     //Ngừng triển khai
        public final static String SUPPEND_TERMINATE = "5";                 //Chặn thanh lý
        public final static String CANCELED = "3";                 //Dừng hoạt động
        public final static String DELETED = "8";

    }

    public static class SUBSCRIBER_SUPPLEMENT_ACT_STATUS {
        public final static String ONE_WAY_BLOCK = "100";           //Chặn một chiều
    }

    public static class SUBSCRIBER_PREPAID_CHARGE_STATUS {
        public final static String CONNECT_WAIT_ACCEPTANCE = "1";                       //Chờ nghiệm thu
        public final static String ACTIVE = "2";                                        //Đang hoạt động
        public final static String CANCEL = "3";                                        //Dừng hoạt động
    }

    public static class SUBSCRIBER_USER_STATUS {
        public final static String ACTIVE = "2";                            //Đang hoạt động
        public final static String CANCELED = "3";                          //Dừng hoạt động
        public final static String SUPPEND_TERMINATE = "5";                 //Chặn thanh lý
        public final static String CONNECT_WAIT_ACCEPTANCE = "7";           //Chờ nghiệm thu
        public final static String DELETED = "8";                           //Đã xóa
        public final static String PAUSE_OPERATION = "9";                   //Tạm dừng hoạt động
    }

    public static class PRODUCT_OFFER_TYPE {
        public final static String ACCORDING_PRICE_BRACKET = "1";           //Theo khung giá
        public final static String NO_PRICE_DETERMINATION = "2";            //Không xác định mức giá
        public final static String FIX_PRICE = "3";                         //Giá cố định
        public final static String PRICE_LIST = "4";                         //Theo bảng giá
    }

    public static class PRODUCT_OFFER_APPLY_TYPE {
        public final static String SINGLE_PHARMACY = "1";           //Nhà thuôc đơn lẻ
        public final static String DRUGSTORES = "2";                //Quầy thuốc
        public final static String COMMUNE_HEALTH_STATION = "3";    //Trạm y tế xã phường
        public final static String WHOLESALE_FACILITY = "4";        //Cơ sở bán buôn
    }

    public static class OBJECT_UPLOAD_TYPE {
        public final static String CUSTOMER = "customer";
        public final static String PRODUCT = "product";
    }

    public static class FOLDER_UPLOAD_NAME {
        public final static String CUSTOMER = "customer";
        public final static String PRODUCT = "product";
    }

    public static class CUSTOMER_GROUP_TYPE {
        public final static String KHTT = "0";
        public final static String KH_GPDN = "1";
        public final static String KH_BCCS = "2";
    }

    public static class CONTRACT_TYPE {
        public final static String NEW_CONTRACT = "0";
        public final static String CONTRACT_GPDN = "1";
        public final static String CONTRACT_BCCS = "2";
    }

    public static class CUSTOMER_ORDER_HIS_TYPE {
        public final static String INSERT = "1";
        public final static String UPDATE = "2";
        public final static String DELETE = "3";
    }

    public static class CUSTOMER_ORDER_HIS_CHANNEL {
        public final static String ONLINE = "1";
        public final static String OFFLINE = "2";
    }

    public static class CUSTOMER_ORDER_ONLINE_HIS_TYPE {
        public final static String INSERT = "1";
        public final static String UPDATE = "2";
        public final static String DELETE = "3";
    }

    public static class BCCS_RESPONE_CODE {
        public final static String SUCCESS = "0";
        public final static String FAIL = "1";
    }

    public static class EXTERNAL_SYSTEM_ID {
        public final static int BCCS = 1;
        public final static int YTCS = 2;
        public final static int HSSK = 3;
        public final static int QLHS = 4;
        public final static int PORTAL = 5;
        public final static int SAFE_ONE = 6;

    }

    public static class PROVISION_TYPE {
        public final static String SELL_OFF = "1";
        public final static String FOR_RENT = "2";
        public final static String ACCORDING_PACKAGE = "3";
        public final static String LIMIT = "4";
    }

    public static String SUBSCRIBER_ACCOUNT_NO_FORMAT = "SUBSCRIBER.ACCOUNT_NO.FORMAT";
    public static String SUBSCRIBER_APP_ACCOUNT_NO_FORMAT = "SUBSCRIBER.APP_ACCOUNT_NO.FORMAT";

    public static final String END_LOG = " -- END ---------------";

    public static final class REPORT_TEMPLATE_CODE {
        public static final String REPORT_NEW_CONNECT_MEDICAL_CODE = "RP_NEW_CONNECT_MEDICAL";
        public static final String REPORT_NEW_CONNECT_MEDICAL_NAME = "report_new_connect_medical_v1.1.jrxml";

        public static final String REPORT_REVENUE_MEDICAL_CODE = "RP_REVENUE_MEDICAL_DETAIL";
        public static final String REPORT_REVENUE_MEDICAL_NAME = "report_detail_medical_v1.1.jrxml";
    }

    public static final class FILE_TYPE {
        public static final String PDF = "PDF";
        public static final String XLS = "XLS";
        public static final String XLSX = "XLSX";
        public static final String DOC = "DOC";
        public static final String HTML = "HTML";

        public static final String PDF_EXTENSION = ".pdf";
        public static final String XLS_EXTENSION = ".xls";
        public static final String XLSX_EXTENSION = ".xlsx";
        public static final String DOC_EXTENSION = ".doc";
        public static final String HTML_EXTENSION = ".html";
    }

    public static final String YTCS_DOMAIN = "http://10.60.156.4:8099";
    public static final String HSSK_DOMAIN = "http://10.60.110.17:8811/";
//    public static final String HSSK_DOMAIN = "http://localhost:8811/";

    public static final class YTCS_RESPONSE_CODE {
        public static final String SUCCESS = "200";
        public static final String SUCCESS_201 = "201";
        public static final String DUPLICATE_ACCOUNT_INFO = "300";
        public static final String MA_DON_VI_NOT_EXIST = "301";
        public static final String TOKEN_EXPIRED = "401";
        public static final String LACK_OF_INPUT_INFO = "402";
        public static final String ANOTHER_ERRORS = "500";
    }

    public static final class JIRA_RESPONSE_CODE {
        public static final String SUCCESS = "200";
        public static final String RESPONSE_404 = "404";
        public static final String RESPONSE_400 = "400";
        public static final String ANOTHER_ERRORS = "500";
    }

    public static final class HSSK_SUSPEND_ACCOUNT_TYPE {
        public static final String LOCK = "1";
        public static final String UNLOCK = "2";
    }

    public static final class DOC_REQUIRED_SEARCH_TYPE {
        public static final String MANAGER = "1";
        public static final String CONNECT = "2";
    }

    public static final class SALE_CHANNEL_TYPE {
        public static final String SALE_CHANNEL = "SALE_CHANNEL";
        public static final String BROKER_CHANNEL = "BROKER_CHANNEL";
    }

    public static final class SALE_CHANNEL_TYPE_PO {
        public static final String TRADITIONAL = "1";
        public static final String ONLINE = "2";
    }

    public static final class SUBSCRIBER_BLOCK_TYPE {
        public static final String SUBSCRIBE_OPEN = "0";
        public static final String SUBSCRIBE_BLOCK = "1";
    }

    public static final class REVENUE_PLAN_STATUS {
        public static final String ACTIVE = "1";
        public static final String INACTIVE = "3";
    }

    public static final class REVENUE_PLAN_TYPE {
        public static final String MONTH = "1";
        public static final String QUARTER = "2";
        public static final String YEAR = "3";
    }

    public static final class SUBSCRIBER_ACT_STATUS {
        public static final char ACT_OPEN = '0';
        public static final char ACT_BLOCK = '2';
    }

    public static final class APP_ACCOUNT_NO_EXIST_STATUS {
        public static final String DONT_EXIST = "0";
        public static final String ALREADY_EXIST_IN_CMS = "1";
        public static final String ALREADY_EXIST_IN_SUBSCRIBER = "2";
    }

    public final static class API_PERMISSION {
        public final static String SHOW_ALL_CUSTOMER_ORDER = "SHOW_ALL_CUSTOMER_ORDER";
        public final static String LIMIT_AM_AND_DEV_STAFF = "LIMIT_AM_AND_DEV_STAFF";
    }

    public final static class CMS_MEDICAL_CODE {
        public final static String YTE_CS = "YTE_CS";
        public final static String HSSK = "HSSK";
        public final static String YTHD = "YTHD";
        public final static String SAFE_ONE = "SAFE_ONE";
        public final static String PORTAL = "PORTAL";
        public final static String VCARE = "VCARE";
        public final static String VMS = "VMS";
        public final static String DVTKB = "DVTKB";
        public final static String ECABINET = "ECN";
        public final static String HIS_YTE = "HIS_YTE";
    }

    public final static class ITEM_CLASSIFICATION {
        public final static String NORMAL = "1";
        public final static String COMBO = "2";
        public final static String MAIN_ITEM = "3";
        public final static String ATTACH_ITEM = "4";
    }

    public final static class PRODUCT_FORM {
        public final static String IN_PACKAGE = "1";
        public final static String OPTION = "2";
    }

    public final static class COMPARE_TIME_TYPE {
        public final static Integer SAME_PERIOD = 1;
        public final static Integer LAST_MONTH = 2;
        public final static Integer OTHER = 3;
    }

    public final static String TRANS_CODE_QLBH = "QLKD_GPCNTT";
    public final static String IP_WEB = "10";

    public final static class ACTION_TYPE {
        public final static String REG_ACTION = "00";
        public final static String CONNECT_WITH_PREPAID_CHARGE = "412";
        public final static String TERMINATE = "03";
        public final static String BLOCK_KHYC = "1414";
        public final static String OPEN_KHYC = "1415";
        public final static String REPAIR_PREPAID_CHARGE = "2412";
        public final static String EXTEND_PREPAID_CHARGE = "412";
        public final static String  CHANGEPRE_PAID_FEE_PROGRAM= "412";
        public final static String AFTER_SALE_REPAID_PREPAID_CHARGE = "17";
        //public final static String MCU_USER_ACCOUNT = "85";
        //public final static String ADD_ROOM = "82";
        public final static String CONTRACT_TRANSFER = "92";
        public final static String SPLIT_CONTRACT = "94";
        public final static String CONTRACT_MERGE = "93";
        public final static String MCU_ADD_USER_ACCOUNT = "4003";
        public final static String MCU_EXTEND_ACCOUNT = "4001";
        public final static String ADD_ROOM = "4002";
    }

    public static class ACT_STATUS {
        public final static String NORMAL = "000";                 // Trạng thái bình thường
        public final static String BLOCK = "200";                  // Chặn
        public final static String BLOCK_AUTO = "020";             // Chặn auto
        public final static String SUPPEND_TERMINATE = "300";      // Chặn thanh lý
    }

    public static class BLOCK_OPEN_TYPE {
        public final static String OPEN_SUB = "0";
        public final static String BLOCK_SUB = "1";
    }

    public static class BLOCK_OPEN_REASON {
        public final static long OPEN_REASON = 2L;
        public final static long BLOCK_REASON = 1L;
    }

    public static class BLOCK_OPEN_TYPE_BCCS {
        public static final String BLOCK_1_WAY = "06";
        public static final String BLOCK_2_WAY = "07";
        public static final String CHANGE_TYPE_BLOCK_2_WAY = "2";
        public static final String CHANGE_TYPE_BLOCK_AUTO_2_WAY = "6";
        public static final String OPEN_1_WAY = "08";
        public static final String OPEN_2_WAY = "09";
        public static final String CHANGE_TYPE_OPEN_2_WAY = "4";
        public static final String CHANGE_TYPE_OPEN_AUTO2_WAY = "8";
    }

    public static final class REVENUE_TYPE {
        public static final Integer MONTH = 1;
        public static final Integer QUARTER = 2;
        public static final Integer YEAR = 3;
    }

    public static final class VOL_LIMITATION_TYPE {
        public static final String NOLIMIT = "1";
        public static final String LIMIT = "2";
    }

    public static final class SALE_TRANS_STATUS {
        public static final String WAIT_ACTIVE = "0";
        public static final String ACTIVE = "1";
        public static final String DEACTIVE = "2";
    }

    public static final class SALE_TRANS_DETAIL_STATUS {
        public static final String WAIT_ACTIVE = "0";
        public static final String ACTIVE = "1";
        public static final String DEACTIVE = "2";
        public static final String CANCEL = "3";
    }

    public static final class VAT_TYPE {
        public static final long NO_VAT = -1; // khong chiu thue
        public static final long NO_PAY_VAT = -2; // khong phai nop thue
        public static final long VAT_0 = 0;
        public static final long VAT_5 = 5;
        public static final long VAT_10 = 10;
    }

    public static class RESPONSE_STATUS_CODE {
        public final static int INTERNAL_SERVER_ERROR = 500;
        public final static int INTERNAL_SERVER_ERROR_400 = 400;
        public final static int INTERNAL_SERVER_ERROR_TOKEN = 401;
        public final static int INTERNAL_SERVER_ERROR_403 = 403;
    }

    public static final class PROPECTIVE_FROM_TYPE {
        public static final long CREATE_FORM = 1;
        public static final long CUSTOMER_SURVEY = 2;
    }

    public static final class WORK_TYPE {
        public static final long SURVEY = 1;
        public static final long DEPLOYMENT = 2;
        public static final long CANCEL_SERVICE = 3;
        public static final long OPEN_SERVICE = 4;
        public static final long LIQUIDATION = 5; // Chặn thanh lý dịch vụ
        public static final long CANCEL_DEPLOY = 6;


    }

    public static final class TABLE_NAME {
        public static final String CUSTOMER = "CUSTOMER";
        public static final String CUSTOMER_ORDER = "CUSTOMER_ORDER";
    }

    public final static class CUSTOMER_ORDER_TYPE {
        public final static String SURVEY = "1"; // khao sat
        public final static String DEPLOYMENT = "2"; // trien khai
        public final static String CANCEL_SERVICE = "3"; // huy / chan dich vu
        public final static String OPEN_SERVICE = "4"; // mo dich vu
        public static final String LIQUIDATION = "5"; // Chặn thanh lý dịch vụ
        public static final String CANCEL_DEPLOYMENT = "6"; // Huỷ triển khai
    }

    public final static class CUSTOMER_ACCOUNT_STATUS {
        //        public final static String WAIT = "0";              //Chờ kích hoạt
        public final static String ACTIVE = "1";            // đang hoạt động
        public final static String CANCEL = "3";             // hủy
    }

    public final static class CUSTOMER_CONTRACT_STATUS {
        //        public final static String WAIT = "0";              //Chờ kích hoạt
        public final static String ACTIVE = "1";            // đang hoạt động
        public final static String CANCEL = "0";             // hủy
    }

    public final static class BUSINESS_UNIT {
        public final static Long Y_TE = 2L;
        public final static String Y_TE_CODE = "GPKD_GPCNTT_BU_YTE";
        public final static Long MANAGE_SERVICE = 3L;
        public final static String MANAGE_SERVICE_CODE = "GPKD_GPCNTT_BU_HTCLOUD";
        public final static String YSYTEM_CODE = "QLKD_QUAN_TRI_HE_THONG";
        public final static Long DEFAULT = 0L;
        public final static Long VOICE_AI = 4L;
        public final static String VOICEAI_SERVICE_CODE = "GPKD_GPCNTT_BU_DL_AI";
        public final static Long MCU = 5L;
        public final static String MCU_CODE = "QLKD_GPCNTT_BU_MCU";

        public final static Long GPCP = 9L;
        public final static Long GDUC = 10L;
        public final static Long CLOUD = 11L;
        public final static Long GPDN = 12L;
    }
    public static final List<String> CHECK_CONFIG_ROLE = Arrays.asList(new String [] {"QLKD_CAU_HINH_TIN_NHAN","QLKD_CAU_HINH_CONG_VIEC"});
    public static final List<String> CHECK_CONFIG_ROLE_MAP = Arrays.asList(new String [] {"GPKD_GPCNTT_BU_DL_AI","GPKD_GPCNTT_BU_UDTCLOUD","QLKD_QUAN_TRI_HE_THONG"});

    public static final String PASS="password";
    public final static class TRANSACTION_STATUS {
        public final static String SUCCESS = "0";
        public final static String FAIL = "1";
    }

    public final static class HTTP_METHOD {
        public final static String GET = "GET";
        public final static String POST = "POST";
    }

    public static final class WORK_STATUS {
        public final static String WAIT_FOR_ACCEPT = "1";   //chưa thực hiện
        public final static String PROCESSING = "2";        //đang thực hiện
        public final static String CANCELED = "3";          //hủy công việc
        public final static String DONE_PROCESS = "4";      //hoàn thành công việc
        public final static String CONFIRM_DONE_PROCESS = "5"; //xác nhận hoàn thành công việc
        public final static String RE_PROCESS = "6";
        public final static String NOT_ENOUGH_CONDITION_DEPLOY = "8";        //không đủ điều kiện triển khai
        public final static String CONFIRM_NOT_ENOUGH_CONDITION_DEPLOY = "9";        //xác nhận không đủ điều kiện triển khai
    }

    public static class JOB_SCHEDULE_CONFIG_STATUS {
        public final static String INACTIVE = "0";
        public final static String ACTIVE = "1";
        public final static String CANCEL = "2";
    }

    public static class JOB_CODE {
        public final static String JOB_BLOCK_SUBSCRIBER_BU2 = "JOB_BLOCK_SUBSCRIBER_BU2";
        public final static String FIRST_RETRY_CANCEL_ORDER_NO_ACTION = "FIRST_RETRY_CANCEL_ORDER_NO_ACTION";
        public final static String SECOND_RETRY_CANCEL_ORDER_NO_ACTION = "SECOND_RETRY_CANCEL_ORDER_NO_ACTION";
        public final static String FIRST_RETRY_CANCEL_ORDER_NO_ACCEPTANCE_CHARGE = "FIRST_RETRY_CANCEL_ORDER_NO_ACCEPTANCE_CHARGE";
        public final static String SECOND_RETRY_CANCEL_ORDER_NO_ACCEPTANCE_CHARGE = "SECOND_RETRY_CANCEL_ORDER_NO_ACCEPTANCE_CHARGE";
        public final static String FIRST_RETRY_CANCEL_SUBSCRIBER_BLOCK_EXPIRE = "FIRST_RETRY_CANCEL_SUBSCRIBER_BLOCK_EXPIRE";
        public final static String SECOND_RETRY_CANCEL_SUBSCRIBER_BLOCK_EXPIRE = "SECOND_RETRY_CANCEL_SUBSCRIBER_BLOCK_EXPIRE";
        public final static String JOB_BLOCK_SUBSCRIBER_BU4 = "JOB_BLOCK_SUBSCRIBER_BU4";
        public final static String RETRY_BLOCK_SUBSCRIBER_BU4 = "RETRY_BLOCK_SUBSCRIBER_BU4";
        public final static String FIRST_RETRY_BLOCK_SUBSCRIBER_BU4 = "FIRST_RETRY_BLOCK_SUBSCRIBER_BU4";
        public final static String SECOND_RETRY_BLOCK_SUBSCRIBER_BU4 = "SECOND_RETRY_BLOCK_SUBSCRIBER_BU4";
        public final static String THIRD_RETRY_BLOCK_SUBSCRIBER_BU4 = "THIRD_RETRY_BLOCK_SUBSCRIBER_BU4";
        public final static String BLOCK__EXPIRE_SUBSCRIBER_SUPPLEMENT_PLAN = "BLOCK_EXPIRE_SUBSCRIBER_SUPPLEMENT_PLAN";
        public final static String TERMINATE_CONTRACT_BU2 = "TERMINATE_CONTRACT_BU2";
        public final static String WARNING_EXPIRING_CONTRACT_AUTO="WARNING_EXPIRING_CONTRACT_AUTO";
        public final static String BLOCK_SUBSCRIBER_EXPIRE_SUBSCRIBER_PLAN = "BLOCK_SUBSCRIBER_EXPIRE_SUBSCRIBER_PLAN";

        public final static String BLOCK_OPEN_SUBSCRIBER_AUTO = "BLOCK_OPEN_SUBSCRIBER_AUTO";
        public final static String CANCEL_ORDER_DONT_ACCEPTANCE_CHARGE = "CANCEL_ORDER_DONT_ACCEPTANCE_CHARGE";
        public final static String CANCEL_ORDER_DONT_ACTION = "CANCEL_ORDER_DONT_ACTION";
        public final static String DELETE_SUBSCRIBER_AUTO = "DELETE_SUBSCRIBER_AUTO";
        public final static String BLOCK_SUBSCRIBER_YTHD_EXPIRE = "BLOCK_SUBSCRIBER_YTHD_EXPIRE";

        //IIST-TUDN 080921 start
        public final static String UPLOAD_FILE_LOG_SENSITIVE_DATA = "UPLOAD_FILE_LOG_SENSITIVE_DATA";
        //IIST-TUDN 080921 end

        public final static String CANCEL_ORDER_OUT_OF_DATE = "CANCEL_ORDER_OUT_OF_DATE";
        public final static String BLOCK_ORDER_OUT_OF_DATE = "BLOCK_ORDER_OUT_OF_DATE";

        public final static String UPDATE_STATUS_SERVICE_PLAN_PORTAL = "UPDATE_STATUS_SERVICE_PLAN_PORTAL";

        public final static String UPDATE_STATUS_SERVICE_PLAN_VCARE = "UPDATE_STATUS_SERVICE_PLAN_VCARE";

        public final static String DASHBOARD_DATA_SYNC = "DASHBOARD_DATA_SYNC";

        public final static String JOB_BLOCK_SUBSCRIBER_BU8 = "JOB_BLOCK_SUBSCRIBER_BU8";
        public final static String FIRST_RETRY_BLOCK_SUBSCRIBER_BU8 = "FIRST_RETRY_BLOCK_SUBSCRIBER_BU8";
        public final static String SECOND_RETRY_BLOCK_SUBSCRIBER_BU8 = "SECOND_RETRY_BLOCK_SUBSCRIBER_BU8";
        public final static String THIRD_RETRY_BLOCK_SUBSCRIBER_BU8 = "THIRD_RETRY_BLOCK_SUBSCRIBER_BU8";
        public final static String BLOCK_OPEN_SUBSCRIBER_BU8_AUTO = "BLOCK_OPEN_SUBSCRIBER_BU8_AUTO";
        public final static String JOB_DESTROY_SERVICE_EXPIRE_DATE = "JOB_DESTROY_SERVICE_EXPIRE_DATE";
        public final static String JOB_AUTO_ACCEPTANCE_POSTPAID_CLOUD = "JOB_AUTO_ACCEPTANCE_POSTPAID_CLOUD";
        public final static String SUBSCRIBER_POSTPAID_IM_BU11 = "SUBSCRIBER_POSTPAID_IM_BU11";
        public final static String JOB_BLOCK_PAYMENT_EXPIRED_SERVICE = "JOB_BLOCK_PAYMENT_EXPIRED_SERVICE";
        public final static String JOB_SERVICE_RESTORE = "JOB_SERVICE_RESTORE";
        public final static String JOB_CANCEL_SERVICE_OVERDUE_RESTORE = "JOB_CANCEL_SERVICE_OVERDUE_RESTORE";
        public final static String JOB_UPDATE_TRANS_BY_IM = "JOB_UPDATE_TRANS_BY_IM";
        public final static String JOB_CONTROL_OF_REVENUE = "JOB_CONTROL_OF_REVENUE";
        public final static String JOB_AUTO_ACCEPTANCE_VDC = "JOB_AUTO_ACCEPTANCE_VDC";
        //TUDN 08/09/2023 START
        public final static String JOB_CLOSES_REVENUE_ALLOCATION = "JOB_CLOSES_REVENUE_ALLOCATION";
        //TUDN 08/09/2023 END
        public final static String JOB_AUTO_ACCEPTANCE_BaaS_CLOUD = "JOB_AUTO_ACCEPTANCE_BaaS_CLOUD";
        public final static String JOB_CHECK_APPROVED_RECEIPT_TEMP_CLOUD = "JOB_CHECK_APPROVED_RECEIPT_TEMP_CLOUD";
    }

    public static class JOB_PROCESS_STATUS {
        public final static String WAITING = "0";
        public final static String RUNNING = "1";
        public final static String DONE = "2";
        public final static String ERROR = "3";
    }

    public static class JOB_SCHEDLUE_CONFIG_STATUS {
        public final static String INACTIVE = "0";
        public final static String ACTIVE = "1";
        public final static String CANCEL = "2";
    }

    public final static String SYSTEM = "SYSTEM";

    public final static class RECIPIENT {
        public final static String AM = "1";            //am
        public final static String ASSIGNEE = "2";      //người giao việc
        public final static String ACTION_STAFF = "3";  //người nhận việc
        public final static String CUSTOMER = "4";

        public final static String STAFF = "3";
    }

    public static class DEPLOYMENT_TYPE {
        public final static String CLOUD = "1";
        public final static String NON_CLOUD = "2";
    }

    public static class OBJECT_DOCUMENT_MAPPING {
        public final static String WORK = "WORK";
    }

    public static class GLOBAL_PARAM_CODE {
        public final static String X_DAY_START_JOB_ACTION_TO_ORDER = "X_DAY_START_JOB_ACTION_TO_ORDER";
        public final static String NUMBER_OF_DAY_TO_CANCEL_ORDER_DONT_ACCEPTANCE_CHARGE = " ";
        public final static String NUMBER_OF_HOUR_TO_CANCEL_ORDER_DONT_ACCEPTANCE_CHARGE = "NUMBER_OF_HOUR_TO_CANCEL_ORDER_DONT_ACCEPTANCE_CHARGE";
        public final static String SUBSCRIBER_ROOM_ID_FORMAT = "SUBSCRIBER_ROOM_ID_FORMAT";
        public final static String X_DAY_START_JOB_ACTION_CANCEL_ORDER = "X_DAY_START_JOB_ACTION_CANCEL_ORDER";
        public final static String X_DAY_START_JOB_ACTION_BLOCK_ORDER = "X_DAY_START_JOB_ACTION_BLOCK_ORDER";
        public final static String X_DAY_DESTROY_SERVICE_EXPIRE_DATE = "DESTROY_SERVICE_EXPIRE_DATE";
        public final static String NUMBER_DAY_CONSIDERED_OVERDUE_PAYMENT = "NUMBER_DAY_CONSIDERED_OVERDUE_PAYMENT";
    }

    public static class PROCESS_STATUS {
        public final static String FAIL = "0";
        public final static String SUCCESS = "1";
    }

    public static class MCU_ACCOUNT_TYPE {
        public final static String ADMIN = "1";
        public final static String USER = "2";
    }

    public static class SUBSCRIBER_BALANCE_TYPE {
        public final static String ROOM = "1";
        public final static String USER = "2";
    }

    public static final class PAY_METHOD {
        //Hinh thuc thanh toan
        public static final String PAY_AT_HOME = "00";//thu tai nha
        public static final String PAY_AT_VIETTEL_STORE = "01";//thanh toan tai cua hang
        public static final String STANDING_ORDER = "02";//uy nhiem thu
        public static final String ENCASHMENT_ORDER = "03";//uy nhiem chi
        public static final String PAY_VIA_PHONE_CARD = "05";//thanh toan bang the cao
        public static final String PAY_VIA_ECOMMERCE = "06";//thanh toan thuong mai dien tu
    }

    public static class UNIT_TYPE {
        public final static String THOUSAND = "1";
        public final static String MILLION = "2";
        public final static String BILLION = "3";
    }

    public static class HSSK_STATUS {
        public final static String SUCCESS = "303";
    }

    public static class DOCUMENT_MANAGEMENT_STATUS {
        public final static String SUCCESS = "0";
    }

    public static class PROUDUCT_GROUP {
        public final static String MEDICAL = "2";
        public final static String INFRASTRUCTURE_AND_CLOUD = "3";
    }

    public static class PARAM_TYPE {
        public final static int PARAM_GENERAL = 0;
        public final static int PARAM_OPTION = 1;
    }

    public static String MS_SM = "020909";

    public static class DEVICE_TYPE {
        public final static String SERVER = "1";
        public final static String VM_SERVER = "2";
        public final static String OTHER = "3";
    }

    public static class PREPAID {
        public final static Boolean PREPAID = false;
        public final static Boolean POSTPAID = true;
    }

    public static class DOC_MANAGEMENT_SERVICE_CODE {
        public final static String MS = "MS"; // Manage services
        public final static String MCU = "MCU"; // đấu nối MCU
        public final static String VOICE_AI = "VA"; // VoiceAI
        public final static String YTE_HIS = "HIS_YTE";
        public final static String YTCS = "YTCS";
        public final static String HSSK = "HSSK";
        public final static String YTHD = "YTHD";
        public final static String CB = "CB";
        public final static String DA = "DA";
        public final static String SAFE_ONE = "03"; //"SO"
        public final static String ECN = "02"; //"ECN" or CBN real
        public final static String VMS = "01"; //"VMS"
        public final static String PT = "PORTAL";
        public final static String VCE = "CAR";
        public final static String CPĐT_ĐTTM = "CPĐT/ĐTTM";
        public final static String DVTKB = "DVTKB";
        public final static String CLOUD = "CLOUD";
        public final static String DVBS = "DVBS";
        public final static String DVCR = "DVCR";
        public final static String DVKE = "DVKE";
        public final static String DVVLB = "DVVLB";
        public final static String DVFS = "DVFS";
        public final static String DVOS = "DVOS";
        public final static String DVVS = "DVVS";
        public final static String DVBU = "DVBU";
        public final static String GPCP = "GPCP"; // giai phap chinh phu Bu9
        public final static String PAC = "PAC";
        public final static String PK = "PK";
        public final static String SHO = "SHO";

        public final static String DVVGPU = "DVVGPU";
        public final static String DVVDC = "DVVDC";
        public final static String DVDBS = "DVDBS";
        public final static String DVMS = "DVMS";
        public final static String DVBAS = "DVBAS";
        public final static String REPUTA = "REPUTA";
    }

    public static class DOC_SERVICE_TYPE {
        public final static String MS = "MS"; // Manage services
        public final static String MCU = "MCU"; // đấu nối MCU
        public final static String VOICE_AI = "VA"; // VoiceAI
        public final static String YTE_HIS = "HIS_YTE";
        public final static String YTCS = "YTCS";
        public final static String HSSK = "HSSK";
        public final static String YTHD = "YTHD";
        public final static String CB = "CB";
        public final static String DA = "DA";
        public final static String SAFE_ONE = "SO";
        public final static String ECN = "CBN";
        public final static String VMS = "VMS";
        public final static String PT = "PORTAL";
        public final static String PORTAL = "PORTAL";
        public final static String VCE = "CAR";
        public final static String VCARE = "VCARE";
        public final static String CPĐT_ĐTTM = "CPĐT/ĐTTM";
        public final static String DVTKB = "DVTKB";
        public final static String ECABINET = "ECN";
        public final static String CLOUD = "CLOUD";
        public final static String DVBS = "DVBS";
        public final static String DVCR = "DVCR";
        public final static String DVKE = "DVKE";
        public final static String DVVLB = "DVVLB";
        public final static String DVFS = "DVFS";
        public final static String DVOS = "DVOS";
        public final static String DVVS = "DVVS";
        public final static String DVBU = "DVBU";
        public final static String YTE_HIS_PROD = "44";
        public final static String GPCP = "GPCP"; // giai phap chinh phu
        public final static String PACSC = "PACSC";
        public final static String PK365 = "PK365";
        public final static String SMARTHOS = "SMARTHOS";
        public final static String DVVGPU = "DVVGPU";
        public final static String DVVDC = "DVVDC";
        public final static String DVDBS = "DVDBS";
        public final static String DVMS = "DVMS";
        public final static String DVBAS = "DVBAS";
        public final static String REPUTA = "REPUTA";
    }

    public static class PREPAID_POSTPAID {
        public final static Long PREPAID = 1L; // trả trước
        public final static Long POSTPAID = 0L; // trả sau
    }

    public static class PRODUCT_GROUP_ID {
        public final static Long YTE = 2L;
        public final static Long MANAGE_SERVICE = 3L;
        public final static Long VOICE_AI = 4L;
        public final static Long MCU = 5L;
        public final static Long YTHD = 0L;
        public final static Long CB = 6L;
        public final static Long DA = 7L;
        public final static Long ECN_VMS = 8L;
        public final static Long CQDT_DTTM = 9L;
        public final static Long GDUC = 10L;
        public final static Long CLOUD = 11L;
    }

    public static class NATION {
        public final static String VN = "Việt Nam";
    }

    public static class DOCUMENT_TYPE {
        public final static String BUSINESS_LICENSE  = "1"; // Giấy phép kinh doanh
        public final static String CONTRACT  = "2"; //Hợp đồng
        public final static String CMND_CCCD  = "5"; //	CMND/CCCD Người đại diện
        public final static String TEST_RECORDS  = "8"; // Biên bản nghiệm thu
    }


    public static class ID_TYPE_INDIVIDUAL {
        public final static String IDC  = "1"; // Thẻ căn cước
        //public final static String PASSPORT = "2"; // 	Hộ Chiếu
        public final static String 	ID  = "3"; // CMT
        public final static String MID  = "4"; // CMT QĐ/CA
    }

    public static class IS_RETRY {
        public final static String TRUE  = "1";
        public final static String FALSE  = "0";
    }

    public static class QUANTITY_RETRY {
        public final static Long BLOCK_SUBCRIBERBU4  = 3L; // Số lần retry của tiến trình chặn thuê bao bu4
    }

    public static class SEND_SMS_CODE_CONFIG {
        public final static String RETRY_SUCCESS_BLOCK_SUBSCRIBER_BU4_SMS_FOR_AM = "RETRY_SUCCESS_BLOCK_SUBSCRIBER_BU4_SMS_FOR_AM";
        public final static String RETRY_FALSE_BLOCK_SUBSCRIBER_BU4_SMS_FOR_AM = "RETRY_FALSE_BLOCK_SUBSCRIBER_BU4_SMS_FOR_AM";
        public final static String WARNING_EXPIRING_CONTRACT_SMS_FOR_AM = "WARNING_EXPIRING_CONTRACT_SMS_FOR_AM";
    }

    public static class SEND_EMAIL_CODE_CONFIG {
        public final static String RETRY_SUCCESS_BLOCK_SUBSCRIBER_BU4_EMAIL_FOR_AM  = "RETRY_SUCCESS_BLOCK_SUBSCRIBER_BU4_EMAIL_FOR_AM";
        public final static String RETRY_FALSE_BLOCK_SUBSCRIBER_BU4_EMAIL_FOR_AM  = "RETRY_FALSE_BLOCK_SUBSCRIBER_BU4_EMAIL_FOR_AM";
    }

    public static class NOTICE_CHARGE_METHOD {
        public final static String WITHOUT_NOTICE  = "8"; // Không nhận thông báo
    }

    public static class JOB_PROCESS_DETAIL_STATUS {
        public final static String CUSSES  = "1"; // Không nhận thông báo
        public final static String FAIL  = "0"; // Không nhận thông báo
    }

    public static final class CONVERT_QUERY_RESULT_OPTIONS {
        public static final String DIGITS_ROUND = "digitsRound";
    }

    public static final class SO_RESPONSE_CODE {
        public static final String SUCCESS = "200";
        public static final String RESPONSE_404 = "404";
        public static final String RESPONSE_400 = "400";
        public static final String MA_DON_VI_NOT_EXIST = "301";
        public static final String TOKEN_EXPIRED = "401";
        public static final String LACK_OF_INPUT_INFO = "402";
        public static final String ANOTHER_ERRORS = "500";
    }

    public static final class SO_MESSAGE_CODE {
        public static final String AUTH_NOT_VALID = "AUTH_NOT_VALID";
        public static final String AUTH_NOT_EMPTY = "AUTH_NOT_EMPTY";
        public static final String AUTH_EXPIRE_DATE = "AUTH_EXPIRE_DATE";
        public static final String AUTH_SUCCESS = "AUTH_SUCCESS";
        public static final String DEVICE_NOT_EMPTY = "DEVICE_NOT_EMPTY";
        public static final String GET_DEVICECODE_SUCCESS = "GET_DEVICECODE_SUCCESS";
        public static final String DEVICE_NOT_VALID = "DEVICE_NOT_VALID";
        public static final String SERVICE_STATUS_NOT_VALID = "SERVICE_STATUS_NOT_VALID";
        public static final String DEVICE_JOIN_SUCCESS = "DEVICE_JOIN_SUCCESS";
        public static final String SERVICE_STATUS_INVALID = "SERVICE_STATUS_INVALID";
        public static final String TIME_NOT_EMPTY = "TIME_NOT_EMPTY";
        public static final String SERVICE_NOT_EMPTY = "SERVICE_NOT_EMPTY";
        public static final String DEVICE_ACTIVE_SUCCESS = "DEVICE_ACTIVE_SUCCESS";
        public static final String DEVICE_RENEW_SUCCESS = "DEVICE_RENEW_SUCCESS";
        public static final String SERVICE_STATUS_NOT_EMPTY = "SERVICE_STATUS_NOT_EMPTY";
        public static final String DEVICE_BLOCKED_SUCCESS = "DEVICE_BLOCKED_SUCCESS";
        public static final String OLD_DEVICE_NOT_VALID = "OLD_DEVICE_NOT_VALID";
        public static final String OLD_DEVICE_NOT_EMPTY = "OLD_DEVICE_NOT_EMPTY";
        public static final String NEW_DEVICE_NOT_VALID = "NEW_DEVICE_NOT_VALID";
        public static final String NEW_DEVICE_NOT_EMPTY = "NEW_DEVICE_NOT_EMPTY";
        public static final String DEVICE_CODE_UPDATED_SUCCESS = "DEVICE_CODE_UPDATED_SUCCESS";
    }

    public static final class SO_SERVICE_STATUS {
        public static final Integer NOT_EXIST_DEVICE_SO = 0; //không tồn tại thiết bị trên SafeOne
        public static final Integer DEVICE_DECLARED = 1; //thiết bị đã khai báo trên SafeOne
        public static final Integer SERVICE_CONNECTED = 2; //Đã đấu nối dịch vụ
        public static final Integer SERVICE_ACCEPTANCED = 3; //Đã nghiệm thu dịch vụ
        public static final Integer SERVICE_BLOCKED = 4; //Đã chặn dịch vụ
        public static final Integer SERVICE_CANCELED = 5; //Đã hủy dịch vụ
    }

    public static class STATUS_SERVICE_PLAN_PORTAL {
        public final static String UNLOCK = "1";
        public final static String LOCK = "2";
    }

    public static class OFFER_TYPE_PORTAL {
        public final static Long CREATE = 0L;
        public final static Long KEEP = 1L;
    }

    public static class ALOW_REUSER {
        public final static Long YES = 1L; // có
        public final static Long NO = 0L; // không
    }

    public static class COST_COMPONENT_CHECKLIST {
        public final static String COST_COMPONENT_VAT  = "COST_COMPONENT_VAT"; // Danh sách thuế cấu phần chi phí
        public final static String ALLOCATION_TYPE  = "ALLOCATION_TYPE"; // Loại phân bổ cấu phần chi phí
    }

    public static class PRODUCT_COMPONENT_STATUS {
        public final static String ACTIVE  = "1"; // Danh sách thuế cấu phần chi phí
        public final static String NOTACTIVE  = "0"; // Loại phân bổ cấu phần chi phí
    }

    public static class DEVELOPMENT_STATE {
        public final static String DEVELOPED = "1"; //	Đã phát triển
        public final static String DEVELOPING = "2"; //	Đang phát triển
        public final static String UNDEVELOPED = "3"; // Chưa phát triển
    }

    public static class PARTNER_TYPE {
        public final static String PARTNER_CHAN = "1"; //	Đối tác kênh bán
        public final static String PARTNER_SUP = "2"; //	Đối tác nhà cung cấp
        public final static String PARTNER_DEV = "3"; // Đối tác phát triển
    }

    public static class CONTRACT_OBJECT {
        public final static String CONTRACT_EGO = "1"; //	Chính quyền điện tử
        public final static String CONTRACT_SMARTY = "2"; //	Đô thị thông minh
    }

    public static class PRICE_LIST_TYPE {
        public final static String BY_TIER = "1"; //	Theo bậc
        public final static String CLASSIFY = "2"; //	Phân loại
    }

    public static class PROJECT_STATE {
        public final static String PRESALES = "Presales";
        public final static String RUNNING = "Running";
        public final static String MAINTENANCE = "Maintenance";
        public final static String CANCEL = "Cancel";
        public final static String CLOSE = "Close";
        public final static String NONE = "None";
        public final static String PENDING = "Pending";
    }

    public static class DASHBOARD_PRODUCT_TYPE {
        public final static String ACCUMULATED_BUSINESS_RESULTS = "ACCUMULATED_BUSINESS_RESULTS"; //	Kết quả kinh doanh lũy kế năm
        public final static String ACCUMULATED_BUSINESS_RESULTS_EGO = "ACCUMULATED_BUSINESS_RESULTS_EGO"; //  Kết quả kinh doanh lũy kế năm của Chính quyền điện tử
        public final static String ACCUMULATED_BUSINESS_RESULTS_SMARTY = "ACCUMULATED_BUSINESS_RESULTS_SMARTY"; //Kết quả kinh doanh lũy kế năm của Đô thị thông minh
        public final static String PRODUCT_READINESS = "PRODUCT_READINESS"; //Mức độ sẵn sàng & Tỷ lệ hợp tác
        public final static String PRODUCT_AMOUNT = "PRODUCT_AMOUNT"; // Số lượng sản phẩm
        public final static String TOP_5_CUSTOMER_REVENUE_EGO = "TOP_5_CUSTOMER_REVENUE_EGO"; // Top 05 khách hàng có doanh thu lũy kế cao nhất của Chính quyền điện tử
        public final static String TOP_5_PRODUCT_REVENUE_EGO = "TOP_5_PRODUCT_REVENUE_EGO"; // Top 05 sản phẩm có doanh thu lũy kế cao nhất  của Chính quyền điện tử
        public final static String TOP_5_CUSTOMER_REVENUE_SMARTY = "TOP_5_CUSTOMER_REVENUE_SMARTY"; // Top 05 khách hàng có doanh thu lũy kế cao nhất của Đô thị thông minh
        public final static String TOP_5_PRODUCT_REVENUE_SMARTY = "TOP_5_PRODUCT_REVENUE_SMARTY"; // Top 05 sản phẩm có doanh thu lũy kế cao nhất của Đô thị thông minh
        public final static String PROJECT_PRODUCT_REVENUE_VOLATILITY = "PROJECT_PRODUCT_REVENUE_VOLATILITY"; // Doanh thu phát sinh theo dự án sản phẩm
        public final static String CHANGE_OF_DEVELOPMENT_PROJECT_BY_STATUS = "CHANGE_OF_DEVELOPMENT_PROJECT_BY_STATUS"; // Doanh thu phát sinh theo dự án sản phẩm
    }

    public static class PROJECT_SOURCE_REVENUE {
        public final static String JIRA = "JIRA";
        public final static String HANDMADE = "HANDMADE";
    }

    public static class CLOUD{
        public final static String DVKMCLOUD = "DVKMCLOUD";
        public final static String CSKMCLOUD = "CSKMCLOUD";
        public final static String EXPDATEKMCLOUD = "EXPDATEKMCLOUD";
        public final static String ERROR_CONFIRM  = "SHOWCONFIRMCLOUD";
        public final static Long DEVIANT  = 1000L;
        public final static String OS  = "Hệ điều hành";
        public final static String APP  = "Ứng dụng";
    }

    // Mang cac ky tu goc co dau
    public final static String STRING_SOURCE_CHARACTERS =
            ".*[ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠạẢảẤấẦầẨẩẪẫẬậẮắẰằẲẳẴẵẶặẸẹẺẻẼẽẾếỀềỂểỄễỆệỈỉỊịỌọỎỏỐốỒồỔổỖỗỘộỚớỜờỞởỠỡỢợỤụỦủỨứỪừỬửỮữỰự].*";
    // Dau cach
    public final static String String_SPACES = ".*\\s.*";

//    public final static String accessToken  = "GUPfSFg37X8i9Qh4zfNd";

    public static final int TIME_EXPIRE_CACHE = 720;

    public static class QLHS_SERVICE_TYPE {
        public final static String DVTKB = "33";  // DV TKB
        public final static String DVBS = "BLS";  // DV Block Storage
        public final static String DVCR = "COR";  // DV Container Registry
        public final static String DVKE = "K8S";  // DV K8S
        public final static String DVVLB = "VLB";  // DV vLB
        public final static String DVFS = "FIS";  // DV File Storage
        public final static String DVOS = "OBS";  // DV Object Storage
        public final static String DVVS = "VS";  // DV vServer
        public final static String VCARE = "104";  // DV VCARE
        public final static String PORTAL = "POR";  // DV Portal
        public final static String YTHD = "YHD";  // DV ythd
        public final static String KCBTX = "YKB";  // DV khám chữa bệnh từ xa
        public final static String HIS = "44";  // DV bệnh viện HIS
        public final static String HSSK = "66";  // DV hồ sơ sức khỏe
        public final static String YTCS = "66";  // DV y tế cơ sở
    }

    public static final HashMap<String, String> BCCS_CMP_RETRIEVE_API = new HashMap<String, String>() {{
        put("DVBU", "/bccs/plans/backup/");
        put("DVCR", "/bccs/plans/container-registry/");
        put("DVFS", "/bccs/plans/file-storage/");
        put("DVKE", "/bccs/plans/k8s-cluster/");
        put("DVVLB", "/bccs/plans/load-balancing/");
        put("DVOS", "/bccs/plans/object-storage/");
        put("DVVS", "/bccs/plans/server/");
        put("DVBS", "/bccs/plans/volume/");
    }};

    public static final HashMap<String, String> BCCS_CMP_RENEW_API = new HashMap<String, String>() {{
        put("DVBU", "/bccs/plans/backup/{id}/renew/");
        put("DVCR", "/bccs/plans/container-registry/{id}/renew/");
        put("DVFS", "/bccs/plans/file-storage/{id}/renew/");
        put("DVKE", "/bccs/plans/k8s-cluster/{id}/renew/");
        put("DVVLB","/bccs/plans/load-balancing/{id}/renew/");
        put("DVOS", "/bccs/plans/object-storage/{id}/renew/");
        put("DVVS", "/bccs/plans/server/{id}/renew/");
        put("DVBS", "/bccs/plans/volume/{id}/renew/");
        put(DOC_MANAGEMENT_SERVICE_CODE.DVVDC, "/bccs/plans/dpc/{id}/renew/"); // quyentv thêm 2 url endpoint cho 2 dv vDC và vGPU
        put(DOC_MANAGEMENT_SERVICE_CODE.DVVGPU, "/bccs/plans/server/{id}/renew/");
        put(DOC_MANAGEMENT_SERVICE_CODE.DVBAS, "/bccs/plans/baas/{id}/renew/");
    }};

    public static final HashMap<String, String> BCCS_CMP_VALID_RENEW_API = new HashMap<String, String>() {{
        put("DVBU", "/bccs/plans/backup/{id}/validate-renew/");
        put("DVCR", "/bccs/plans/container-registry/{id}/validate-renew/");
        put("DVFS", "/bccs/plans/file-storage/{id}/validate-renew/");
        put("DVKE", "/bccs/plans/k8s-cluster/{id}/validate-renew/");
        put("DVVLB","/bccs/plans/load-balancing/{id}/validate-renew/");
        put("DVOS", "/bccs/plans/object-storage/{id}/validate-renew/");
        put("DVVS", "/bccs/plans/server/{id}/validate-renew/");
        put("DVBS", "/bccs/plans/volume/{id}/validate-renew/");
    }};

    public static final HashMap<String, String> BCCS_CMP_BATCH_RENEW_API = new HashMap<String, String>() {{
        put("DVFS", "/bccs/plans/file-storage/batch-renew/");
        put("DVOS", "/bccs/plans/object-storage/batch-renew/");
        put("DVBS", "/bccs/plans/volume/batch-renew/");
        put("DVBAS", "/bccs/plans/volume/batch-renew/");
    }};

    public static final HashMap<String, String> BCCS_CMP_TERMINATE_API = new HashMap<String, String>() {{
        put("DVBU", "/bccs/plans/backup/{id}/terminate/");
        put("DVCR", "/bccs/plans/container-registry/{id}/terminate/");
        put("DVFS", "/bccs/plans/file-storage/{id}/terminate/");
        put("DVKE", "/bccs/plans/k8s-cluster/{id}/terminate/");
        put("DVVLB","/bccs/plans/load-balancing/{id}/terminate/");
        put("DVOS", "/bccs/plans/object-storage/{id}/terminate/");
        put("DVVS", "/bccs/plans/server/{id}/terminate/");
        put("DVBS", "/bccs/plans/volume/{id}/terminate/");
        put(DOC_MANAGEMENT_SERVICE_CODE.DVVDC, "/bccs/plans/dpc/{id}/terminate/"); // quyentv thêm 2 url endpoint cho 2 dv vDC và vGPU
        put(DOC_MANAGEMENT_SERVICE_CODE.DVVGPU, "/bccs/plans/server/{id}/terminate/");
        put(DOC_MANAGEMENT_SERVICE_CODE.DVBAS, "/bccs/plans/baas/{id}/terminate/"); // chấm dứt Baas - Bacnv
    }};

    public static final String UNDEFINED_DATE = "undefined:00";


    public static class INVOICE_REVENUE_TYPE {
        public final static Long INVOICE_REVENUE_REDUCE  = 0L; // Doanh thu giảm
        public final static Long INVOICE_REVENUE_INCREASE  = 1L; // Doanh thu tăng
    }

    public static class SALE_TRANS_TYPE {
        public final static String SALE_TRANS_NO_STOCK  = "0"; // Kho
        public final static String SALE_TRANS_RETAIL  = "1"; // Bán lẻ
    }

    public final static String SALE_TRANS_IM_CODE = "GD0000";

    public static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$");

    public final static Integer TRIAL_CONNECTED = 1;

    public interface CmpPaymentMethod {
        String PREPAID = "prepaid";
        String POSTPAID = "postpaid";
    }

    public interface ImPaymentStatus {
        String PAID = "00";
        String NOT_FOUND = "01";
        String UNPAID = "02";
        String UNKNOWN = "-1";
    }
    public static class UNLIMITED_TYPE {
        public final static String UNLIMITED_ACCOUNT  = "0"; // Giới hạn tài khoản
        public final static String ACCOUNT_LIMIT   = "1"; // Không giới hạn tài khoản
    }

    public static class PRODUCT_PACKAGE_TYPE {
        public final static String ADDTIONAL  = "2"; // Goi cuoc bo sung
        public final static String MAIN   = "1"; // Goi cuoc chinh
    }

    public static class PRODUCT_ID {
        public final static Long DRUGSTORE  = 1137L; // Nhà thuốc
    }
    public final static String WHITE_LIST_ACCOUNT_ADMIN_CODE = "WHITE_LIST_ACCOUNT_ADMIN";
    public static class ACTION_TYPE_VGPU {
        public final static String BLOCK  = "BLOCK";
        public final static String UN_BLOCK  = "UN_BLOCK";
    }

    public static class PROVISION_CODE_LIST {
        public final static String CLOUD = "CMP";
        public final static String EDUCATION = "EDUCATION";
    }

    public static class RESPONSE_API_CODE {
        public final static int SUCCESS = 0;
        public final static int BAD_REQUEST = 1;
        public final static int SERVER_ERROR = -1;
    }

    public static class RESPONSE_API_MESSAGE {
        public final static String SUCCESS = "success";
        public final static String FAIL = "fail";
        public final static String SERVER_ERROR = "Internal server error";
        public final static String NOT_FOUND_ITEM = "Not found item";
    }

    public static class EXTERNAL_SYSTEM {
        public final static String CLOUD = "CLOUD";
    }

    public static class CUSTOMER_TYPE_SAP {
        public final static String PERSONAL = "0";
        public final static String CAREER_UNIT = "1";
        public final static String STATE_ENTERPRISE = "2";
        public final static String PRIVATE_ENTERPRISE = "3";
        public final static String FOREIGN = "4";
    }

    public static final List<String> CUSTOMER_CLASS_TAGS_VALID_LIST = Arrays.asList(
            "CQTW","CQDP","KHDN","UBND Tỉnh","Bộ/ngành","Khách hàng kinh doanh","KDDV","KDGT","KDĐT","KDHH","YTE","TTTTT","GDDT","VNR500","KDQT","Khác"
    );

    public final static String SAP_CODE = "default";

    //TUDN 08/09/2023 START
    public static class STATUS_EDU {
        public final static String ACTIVE = "1"; // chốt sổ
        public final static String CANCEL = "0"; // tạm tính
    }

    public static class GLOBAL_CODE {
        public final static String STATUS_REVENUE_ALLOCATION = "STATUS_REVENUE_ALLOCATION";
    }
    //TUDN 08/09/2023 END

    public static class PRODUCT_OFFER_TYPE_BAAS {
        public final static String VM_SEVER = "1";
        public final static String PC_LAPTOP = "2";
    }

    public final static class PATTERN {
        public final static class REVENUE_TYPE {
            public final static String REGEXP = "^.{0}$|[1-2]";
            public final static String MESSAGE = "Trường 'revenueType' phải có giá trị nằm trong (1,2)";
        }
        public final static String DATE_YMD = "^.{0}$|([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        public final static String CUSTOMER_GROUP = "^.{0}$|[1-3]";
        public final static String EMAIL = "^.{0}$|^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        public final static String PHONE_NUMBER_VN = "^.{0}$|(0[3|5|7|8|9])+([0-9]{8})";
        public final static String ID_NO = "^.{0}$|^[A-Za-z0-9]*";
        public final static String GENDER = "^.{0}$|(?:Nam|Nữ)$";
        public final static class PRODUCT_OFFER_TYPE {
            public final static String REGEXP = "^.{0}$|[1-3]";
            public final static String MESSAGE = "Trường 'productOfferType' phải có giá trị nằm trong (1,2,3)";
        }

        public final static class SALE_FORM {
            public final static String REGEXP = "^.{0}$|[1-3]";
            public final static String MESSAGE = "Trường 'saleForm' phải có giá trị nằm trong (1,2,3)";
        }

        public final static String AREA = "(?:province|district|commune)$";
        public final static class PROVISION_CODE {
            public final static String REGEXP = "(?:CMP)$";
            public final static String MESSAGE = "Trường 'provisionCode' có giá trị không hợp lệ";
        }

        public final static class COUNTRY_CODE {
            public final static String REGEXP = "(?:VI)$";
            public final static String MESSAGE = "Trường 'countryCode' có giá trị không hợp lệ";
        }
        public final static String ID_NO_V2 = "^.{0}$|^[A-Za-z0-9-]*";
        public final static String POSITIVE_INTEGER = "^.{0}$|^[1-9][0-9]*$";
        public final static String POSITIVE_INTEGER_WITH_ZERO = "^.{0}$|^[0-9]*$";
    }

    // nodo phuonghn
    public static class SUBSCRIBER_POSTPAID_FORM { // Dạng trả sau
        public final static Integer POPULAR = 1; // đại trà
        public final static Integer PROJECT = 2; // dự án
    }

    public static class RECEIPT_TEMP_TRANS_STATUS { // Trạng thái giao dịch
        public final static Long NOT_TRANS = 0L; // Chưa lên giao dịch
        public final static Long WAIT_APPROVE = 1L; // Chờ tích duyệt
        public final static Long APPROVED = 2L; // Đã tích duyệt
    }

    public static class SUB_PO_PRICE_ACTION_TYPE {
        public final static String CONNECT = "0";           // đấu nối
        public final static String EXTEND = "1";           // gia hạn
    }
    // /nodo phuonghn

    public static final List<String> lstExternalSource = Arrays.asList(PROVISION_CODE_LIST.CLOUD, PROVISION_CODE_LIST.EDUCATION);

    public static class ID_TYPE {
        public final static String CCCD  = "1"; // Thẻ căn cước
        public final static String HC  = "2"; // Hộ chiếu
        public final static String CMT  = "3"; // Chứng minh thư
        public final static String CMTQDCA  = "4"; // Chứng minh thư quân đội/công an
        public final static String GPKD  = "5"; // Giấy phép kinh doanh
        public final static String MST  = "6"; // Mã số thuế
        public final static String KHAC  = "7"; // Giấy tờ khác
    }

    public static class CUSTOMER_BUS_TYPE_V2 {
        public final static String CN  = "1"; // Cá nhân
        public final static String DNTN  = "2"; // Doanh nghiệp trong nước
        public final static String DNNN  = "3"; // Doanh nghiệp nước ngoài
        public final static String DVHC  = "4"; // Đơn vị hành chính
        public final static String CQQS  = "5"; // Cơ quan quân sự
        public final static String CTNB  = "6"; // Công ty nội bộ
    }
    public static class APPLICATION_LOG {
        public final static String ALLOCATE_CLOUD = "ALLOCATE_CLOUD"; // phân bổ cloud
    }
    public static class REVENUE_ALLOCATE_REPORT_TRANS_STATUS { // Trạng thái giao dịch
        public final static Integer TRANS_TEMP = -1; // tạm tính (đã lên giao dịch)
        public final static Integer NOT_TRANS = 0; // tạm tính
        public final static Integer TRANS = 1; // Đã lên giao dịch
        public final static Integer CANCEL_TRANS = 2; // Hủy giao dịch
        public final static Integer REDUCE = 3; // Giảm trừ
    }
}

