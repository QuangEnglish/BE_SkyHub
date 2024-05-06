package com.company_management.utils;

import com.company_management.model.entity.UserCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;



@Slf4j
public class CommonUtils {

    public Long getCurrentUsername() {
        return getUserLoginName();
    }

    public static Long getLongValue(Object value) {
        if (value != null && !value.toString().isEmpty()) {
            return (Double.valueOf(value.toString())).longValue();
        } else {
            return 0L;
        }
    }

    public static Date convertStringToDate(String date) throws Exception {
        if (date != null && !date.trim().isEmpty()) {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            return dateFormat.parse(date);
        } else {
            return null;
        }
    }

    public static String convertDateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
        }
    }


    public static String convertDateToString(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(date);
        }
    }

    /**
     * lay user id login
     */
    public static Long getUserLoginName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Mã id tài khoản //"+((UserCustom) authentication.getPrincipal()).getId());
        return ((UserCustom) authentication.getPrincipal()).getId();
    }

    public static String getFullName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserCustom) authentication.getPrincipal()).getUsername();
    }

    private static boolean isCollectionEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else {
            if (object instanceof String) {
                if (((String) object).trim().isEmpty()) {
                    return true;
                }
            } else if (object instanceof Collection) {
                return isCollectionEmpty((Collection) object);
            }

            return false;
        }
    }

    public static String buildCountQuery(String baseQuery) {
        String sb = "SELECT COUNT(*) FROM (#BASE_QUERY#) FILTERED_ORDERD_RESULTS ";
        return sb.replaceAll("#BASE_QUERY#", baseQuery);
    }

    public static final String YYYYMMDD_HHMMSS = "yyyyMMdd_HHmmss";

    public static String getLikeCondition(String str) {
        if (str == null || str.trim().isEmpty()) {
            str = "%";

            return str;
        }
        String newStr =
                str.trim()
                        .replace("\\", "\\\\")
                        .replace("\\t", "\\\\t")
                        .replace("\\n", "\\\\n")
                        .replace("\\r", "\\\\r")
                        .replace("\\z", "\\\\z")
                        .replace("\\b", "\\\\b")
                        .replaceAll("_", "\\\\_")
                        .replaceAll("%", "\\\\%");
        str = "%".concat(newStr.trim()).concat("%");
        return str;
    }

    public static ResponseEntity getResponseFromByte(String fileName, byte[] exportInputStream) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("File", fileName);
        headers.set("Content-Disposition", "attachment; filename=" + fileName);
        headers.set("Access-Control-Expose-Headers", "File");
        return ResponseEntity.ok().headers(headers).body(exportInputStream);
    }

    public static ResponseEntity<InputStreamResource> getResponseFile(String fileName, ByteArrayInputStream exportInputStream) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("File", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        headers.add("Access-Control-Expose-Headers", "File");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(exportInputStream));
    }

    public static String insertDot(String str) {
        if (str.length() < 4) {
            return str;
        }
        return insertDot(str.substring(0, str.length() - 3)) + "." + str.substring(str.length() - 3, str.length());
    }

    public static String getFileNameReportUpdate(String preName) {
        String pattern = "yyMMdd_HHmmss";
        DateFormat dtf = new SimpleDateFormat(pattern);
        String date = dtf.format(new Date());
        return preName + "_" + date + ".xlsx";
    }

    public static InputStream getInputStreamByFileName(String fileName) {
        try {
            if (fileName != null && fileName.contains(".")) {
                return new ClassPathResource("/excel-template/" + fileName).getInputStream();
            } else {
                return null;
            }
        } catch (IOException ioE) {
            return null;
        }
    }

}
