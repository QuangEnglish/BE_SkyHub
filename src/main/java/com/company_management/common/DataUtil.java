package com.company_management.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataUtil {
    private static final Logger log = LoggerFactory.getLogger(DataUtil.class);


    public static Boolean listNullOrEmpty(List list) {
        return list == null || list.size() == 0;
    }


    public static boolean isValidEmail(String email) {
        String emailRegex = "[A-Z0-9a-z\\._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]+";
        return email.matches(emailRegex);
    }

    public static Object cloneBean(Object source, Class cl) {
        try {
            if (source == null) {
                return null;
            }
            Object dto = cl.getConstructor().newInstance();
            BeanUtils.copyProperties(source, dto);
            return dto;
        } catch (Exception e) {
            return null;
        }
    }
    public static boolean isNullOrZero(Long value) {
        return (value == null || value.equals(0L));
    }

    /*
     * Kiem tra Long bi null hoac zero
     *
     * @param value
     * @return
     */
    public static boolean isNullOrZero(Double value) {
        return (value == null || value == 0);
    }

    /*
     * Kiem tra Long bi null hoac zero
     *
     * @param value
     * @return
     */
    public static boolean isNullOrZero(String value) {
        return (value == null || safeToLong(value).equals(0L));
    }

    /*
     * Kiem tra Long bi null hoac zero
     *
     * @param value
     * @return
     */
    public static boolean isNullOrZero(Integer value) {
        return (value == null || value.equals(0));
    }

    /*
     * Kiem tra Long bi null hoac zero
     *
     * @param value
     * @return
     */
    public static boolean isNullOrOneNavigate(Long value) {
        return (value == null || value.equals(-1L));
    }

    public static boolean isNullOrNotGreaterZero(Long value) {
        return (value == null || value.compareTo(0L) <= 0);
    }

    /**
     * Kiem tra Bigdecimal bi null hoac zero
     *
     * @param value
     * @return
     */
    public static boolean isNullOrZero(BigDecimal value) {
        return (value == null || value.compareTo(BigDecimal.ZERO) == 0);
    }

    /**
     * Upper first character
     *
     * @param input
     * @return
     */
    public static String upperFirstChar(String input) {
        if (DataUtil.isNullOrEmpty(input)) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Lower first characater
     *
     * @param input
     * @return
     */
    public static String lowerFirstChar(String input) {
        if (DataUtil.isNullOrEmpty(input)) {
            return input;
        }
        return input.substring(0, 1).toLowerCase() + input.substring(1);
    }

    public static String safeTrim(String obj) {
        if (obj == null)
            return null;
        return obj.trim();
    }

    public static String safeToUpperCase(String obj) {
        if (obj == null)
            return null;
        return obj.toUpperCase();
    }

    public static Long safeToLong(Object obj1, Long defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }
        if (obj1 instanceof BigDecimal) {
            return ((BigDecimal) obj1).longValue();
        }
        if (obj1 instanceof BigInteger) {
            return ((BigInteger) obj1).longValue();
        }
        if (obj1 instanceof Double) {
            return ((Double) obj1).longValue();
        }

        try {
            return Long.parseLong(obj1.toString());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * @param obj1 Object
     * @return Long
     */
    public static Long safeToLong(Object obj1) {
        return safeToLong(obj1, 0L);
    }

    public static Double safeToDouble(Object obj1, Double defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(obj1.toString());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Double safeToDouble(Object obj1) {
        return safeToDouble(obj1, 0.0);
    }

    public static Short safeToShort(Object obj1, Short defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }
        try {
            return Short.parseShort(obj1.toString());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Short safeToShort(Object obj1) {
        return safeToShort(obj1, (short) 0);
    }

    /**
     * @param obj1
     * @param defaultValue
     * @return
     * @author phuvk
     */
    public static int safeToInt(Object obj1, int defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(obj1.toString());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * @param obj1 Object
     * @return int
     */
    public static int safeToInt(Object obj1) {
        return safeToInt(obj1, 0);
    }

    /**
     * @param obj1 Object
     * @return String
     */
    public static String safeToString(Object obj1, String defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }

        return obj1.toString().trim();
    }


    public static String safeToLower(String obj1) {
        if (obj1 == null) {
            return null;
        }

        return obj1.toLowerCase();
    }

    /**
     * @param obj1 Object
     * @return String
     */
    public static String safeToString(Object obj1) {
        return safeToString(obj1, "");
    }

    public static Date safeToDate(Object obj1) {
        if (obj1 == null) {
            return null;
        }
        return (Date) obj1;
    }

    public static Integer safeToInteger(Object obj1) {
        if (obj1 == null) {
            return null;
        }
        return ((BigDecimal) obj1).intValue();
    }

    /**
     * safe equal
     *
     * @param obj1 Long
     * @param obj2 Long
     * @return boolean
     */
    public static boolean safeEqual(Long obj1, Long obj2) {
        if (obj1 == obj2)
            return true;
        return ((obj1 != null) && (obj2 != null) && (obj1.compareTo(obj2) == 0));
    }

    /**
     * safe equal
     *
     * @param obj1 Long
     * @param obj2 Long
     * @return boolean
     */
    public static boolean safeEqual(BigInteger obj1, BigInteger obj2) {
        if (obj1 == obj2)
            return true;
        return (obj1 != null) && obj1.equals(obj2);
    }

    /**
     * @param obj1
     * @param obj2
     * @return
     * @date 09-12-2015 17:43:20
     * @author TuyenNT17
     * @description
     */
    public static boolean safeEqual(Short obj1, Short obj2) {
        if (obj1 == obj2)
            return true;
        return ((obj1 != null) && (obj2 != null) && (obj1.compareTo(obj2) == 0));
    }

    /**
     * safe equal
     *
     * @param obj1 String
     * @param obj2 String
     * @return boolean
     */
    public static boolean safeEqual(String obj1, String obj2) {
        if (obj1 == obj2)
            return true;
        return ((obj1 != null) && obj1.equals(obj2));
    }

    /**
     * safe equal
     *
     * @param obj1 String
     * @param obj2 String
     * @return boolean
     */
    public static boolean safeEqualIgnoreCase(String obj1, String obj2) {
        if (obj1 == obj2)
            return true;
        return ((obj1 != null) && obj1.equalsIgnoreCase(obj2));
    }

    /**
     * check null or empty
     * Su dung ma nguon cua thu vien StringUtils trong apache common lang
     *
     * @param cs String
     * @return boolean
     */
    public static boolean isNullOrEmpty(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }

        if (safeToString(cs).isEmpty()) {
            return true;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(final Object[] collection) {
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Tra ve doi tuong default neu object la null, neu khong thi tra object
     *
     * @param object
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T defaultIfNull(final T object, final T defaultValue) {
        return object != null ? object : defaultValue;
    }

    /**
     * Tra ve doi tuong default neu object la null, neu khong thi tra object
     *
     * @param object
     * @param defaultValueSupplier
     * @param <T>
     * @return
     */
    public static <T> T defaultIfNull(final T object, final Supplier<T> defaultValueSupplier) {
        return object != null ? object : defaultValueSupplier.get();
    }

    /**
     * Ham nay mac du nhan tham so truyen vao la object nhung gan nhu chi hoat dong cho doi tuong la string
     * Chuyen sang dung isNullOrEmpty thay the
     *
     * @param obj1
     * @return
     */
    public static boolean isStringNullOrEmpty(Object obj1) {
        return obj1 == null || "".equals(obj1.toString().trim());
    }

    /**
     * @param obj1 Object
     * @return BigDecimal
     */
    public static BigDecimal safeToBigDecimal(Object obj1) {
        if (obj1 == null) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(obj1.toString());
        } catch (final NumberFormatException nfe) {
            return BigDecimal.ZERO;
        }
    }

    public static BigInteger safeToBigInteger(Object obj1, BigInteger defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }
        try {
            return new BigInteger(obj1.toString());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static BigInteger safeToBigInteger(Object obj1) {
        return safeToBigInteger(obj1, BigInteger.ZERO);
    }

    public static BigInteger add(BigInteger obj1, BigInteger obj2) {
        if (obj1 == null) {
            return obj2;
        } else if (obj2 == null) {
            return obj1;
        }

        return obj1.add(obj2);
    }

    public static Character safeToCharacter(Object value) {
        return safeToCharacter(value, '0');
    }

    public static Character safeToCharacter(Object value, Character defaulValue) {
        if (value == null)
            return defaulValue;
        return String.valueOf(value).charAt(0);
    }
    public static boolean isNullObject(Object obj1) {
        if (obj1 == null) {
            return true;
        }
        if (obj1 instanceof String) {
            return isNullOrEmpty(obj1.toString());
        }
        return false;
    }

    public static String toUpper(String input) {
        if (isNullOrEmpty(input)) {
            return input;
        }
        return input.toUpperCase();
    }

    /**
     * Validate Data theo Pattern
     *
     * @author khuongdv
     */
    public static boolean validateStringByPattern(String value, String regex) {
        if (isNullOrEmpty(regex) || isNullOrEmpty(value)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * Bien cac ki tu dac biet ve dang ascii
     *
     * @param input
     * @return
     */
    public static String convertCharacter(String input) {
        if (input == null) {
            return "";
        }
        String[] aList = {"à", "á", "ả", "ã", "ạ", "â", "ầ", "ấ", "ẩ", "ẫ", "ậ", "ă", "ằ", "ắ", "ẳ", "ẵ", "ặ"};
        String[] eList = {"è", "é", "ẻ", "ẽ", "ẹ", "ê", "ề", "ế", "ể", "ễ", "ệ"};
        String[] iList = {"ì", "í", "ỉ", "ĩ", "ị"};
        String[] oList = {"ò", "ó", "ỏ", "õ", "ọ", "ô", "ồ", "ố", "ổ", "ỗ", "ộ", "ơ", "ờ", "ớ", "ở", "ỡ", "ợ"};
        String[] uList = {"ù", "ú", "ủ", "ũ", "ụ", "ư", "ừ", "ứ", "ử", "ữ", "ự"};
        String[] yList = {"ý", "ỳ", "ỷ", "ỹ", "ỵ"};
        String[] AList = {"À", "Á", "Ả", "Ã", "Ạ", "Â", "Ầ", "Ấ", "Ẩ", "Ẫ", "Ậ", "Ă", "Ằ", "Ắ", "Ẳ", "Ẵ", "Ặ"};
        String[] EList = {"È", "É", "Ẻ", "Ẽ", "Ẹ", "Ê", "Ề", "Ế", "Ể", "Ễ", "Ệ"};
        String[] IList = {"Ì", "Í", "Ỉ", "Ĩ", "Ị"};
        String[] OList = {"Ò", "Ó", "Ỏ", "Õ", "Ọ", "Ô", "Ồ", "Ố", "Ổ", "Ỗ", "Ộ", "Ơ", "Ờ", "Ớ", "Ở", "Ỡ", "Ợ"};
        String[] UList = {"Ù", "Ú", "Ủ", "Ũ", "Ụ", "Ư", "Ừ", "Ứ", "Ử", "Ữ", "Ự"};
        String[] YList = {"Ỳ", "Ý", "Ỷ", "Ỹ", "Ỵ"};
        for (String s : aList) {
            input = input.replace(s, "a");
        }
        for (String s : AList) {
            input = input.replace(s, "A");
        }
        for (String s : eList) {
            input = input.replace(s, "e");
        }
        for (String s : EList) {
            input = input.replace(s, "E");
        }
        for (String s : iList) {
            input = input.replace(s, "i");
        }
        for (String s : IList) {
            input = input.replace(s, "I");
        }
        for (String s : oList) {
            input = input.replace(s, "o");
        }
        for (String s : OList) {
            input = input.replace(s, "O");
        }
        for (String s : uList) {
            input = input.replace(s, "u");
        }
        for (String s : UList) {
            input = input.replace(s, "U");
        }
        for (String s : yList) {
            input = input.replace(s, "y");
        }
        for (String s : YList) {
            input = input.replace(s, "Y");
        }
        input = input.replace("đ", "d");
        input = input.replace("Đ", "D");
        return input;
    }

    public static Map<String, String> convertStringToMap(String temp, String regex, String regexToMap) {
        if (isNullOrEmpty(temp)) {
            return null;
        }
        String[] q = temp.split(regex);
        Map<String, String> lstParam = new HashMap<>();
        for (String a : q) {
            String key = a.substring(0, !a.contains(regexToMap) ? 1 : a.indexOf(regexToMap));
            String value = a.substring(a.indexOf(regexToMap) + 1);
            lstParam.put(key.trim(), value.trim());
        }
        return lstParam;
    }

    /*
     * toanld2 ham xu li khoang trang giua xau
     * **/
    public static String replaceSpaceSolr(String inputLocation) {
        if (inputLocation == null || inputLocation.trim().isEmpty()) {
            return "";
        }
        String[] arr = inputLocation.split(" ");
        String result = "";
        for (String s : arr) {
            if (!"".equals(s.trim())) {
                if (!"".equals(result)) {
                    result += "\\ ";
                }
                result += s.trim();
            }
        }
        return result;
    }

    public static boolean isNumber(String string) {
        return !isNullOrEmpty(string) && string.trim().matches("^\\d+$");
    }
    /**
     * Tim nhung phan tu co o collection a ma khong co o collection b
     *
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> List<T> subtract(Collection<T> a, Collection<T> b) {
        if (a == null) {
            return new ArrayList<>();
        }
        if (b == null) {
            return new ArrayList<>(a);
        }
        List<T> list = new ArrayList<>(a);
        list.removeAll(b);
        return list;
    }

    public static <T> List<T> intersection(Collection<T> a, Collection<T> b) {
        if (a == null || b == null) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>(a);
        list.retainAll(b);
        return list;
    }

    public static String trim(String str, String alt) {
        if (str == null) {
            return alt;
        }
        return str.trim();
    }

    public static BigDecimal defaultIfSmallerThanZero(BigDecimal value) {
        return defaultIfSmallerThanZero(value, BigDecimal.ZERO);
    }

    public static BigDecimal defaultIfSmallerThanZero(BigDecimal value, BigDecimal defaultValue) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            return defaultValue;
        }
        return value;
    }

    public static String formatStringDateSubAge(String date) {
        if (date == null) {
            return "";
        }
        String str = date.substring(4, 6) +
                "/" +
                date.substring(0, 4);
        return str;
    }

    public static boolean campareNoSignString(Object obj1, Object obj2) {
        boolean result = false;
        String temp1 = obj1 != null ? convertUnicode2Nosign(obj1.toString()) : "";
        String temp2 = obj2 != null ? convertUnicode2Nosign(obj2.toString()) : "";
        if (temp1.equalsIgnoreCase(temp2)) {
            result = true;
        }
        return result;
    }

    public static String convertUnicode2Nosign(String org) {

        char[] arrChar = org.toCharArray();
        char[] result = new char[arrChar.length];
        for (int i = 0; i < arrChar.length; i++) {
            switch (arrChar[i]) {
                case '\u00E1':
                case '\u00E0':
                case '\u1EA3':
                case '\u00E3':
                case '\u1EA1':
                case '\u0103':
                case '\u1EAF':
                case '\u1EB1':
                case '\u1EB3':
                case '\u1EB5':
                case '\u1EB7':
                case '\u00E2':
                case '\u1EA5':
                case '\u1EA7':
                case '\u1EA9':
                case '\u1EAB':
                case '\u1EAD':
                case '\u0203':
                case '\u01CE': {
                    result[i] = 'a';
                    break;
                }
                case '\u00E9':
                case '\u00E8':
                case '\u1EBB':
                case '\u1EBD':
                case '\u1EB9':
                case '\u00EA':
                case '\u1EBF':
                case '\u1EC1':
                case '\u1EC3':
                case '\u1EC5':
                case '\u1EC7':
                case '\u0207': {
                    result[i] = 'e';
                    break;
                }
                case '\u00ED':
                case '\u00EC':
                case '\u1EC9':
                case '\u0129':
                case '\u1ECB': {
                    result[i] = 'i';
                    break;
                }
                case '\u00F3':
                case '\u00F2':
                case '\u1ECF':
                case '\u00F5':
                case '\u1ECD':
                case '\u00F4':
                case '\u1ED1':
                case '\u1ED3':
                case '\u1ED5':
                case '\u1ED7':
                case '\u1ED9':
                case '\u01A1':
                case '\u1EDB':
                case '\u1EDD':
                case '\u1EDF':
                case '\u1EE1':
                case '\u1EE3':
                case '\u020F': {
                    result[i] = 'o';
                    break;
                }
                case '\u00FA':
                case '\u00F9':
                case '\u1EE7':
                case '\u0169':
                case '\u1EE5':
                case '\u01B0':
                case '\u1EE9':
                case '\u1EEB':
                case '\u1EED':
                case '\u1EEF':
                case '\u1EF1': {
                    result[i] = 'u';
                    break;
                }
                case '\u00FD':
                case '\u1EF3':
                case '\u1EF7':
                case '\u1EF9':
                case '\u1EF5': {
                    result[i] = 'y';
                    break;
                }
                case '\u0111': {
                    result[i] = 'd';
                    break;
                }
                case '\u00C1':
                case '\u00C0':
                case '\u1EA2':
                case '\u00C3':
                case '\u1EA0':
                case '\u0102':
                case '\u1EAE':
                case '\u1EB0':
                case '\u1EB2':
                case '\u1EB4':
                case '\u1EB6':
                case '\u00C2':
                case '\u1EA4':
                case '\u1EA6':
                case '\u1EA8':
                case '\u1EAA':
                case '\u1EAC':
                case '\u0202':
                case '\u01CD': {
                    result[i] = 'A';
                    break;
                }
                case '\u00C9':
                case '\u00C8':
                case '\u1EBA':
                case '\u1EBC':
                case '\u1EB8':
                case '\u00CA':
                case '\u1EBE':
                case '\u1EC0':
                case '\u1EC2':
                case '\u1EC4':
                case '\u1EC6':
                case '\u0206': {
                    result[i] = 'E';
                    break;
                }
                case '\u00CD':
                case '\u00CC':
                case '\u1EC8':
                case '\u0128':
                case '\u1ECA': {
                    result[i] = 'I';
                    break;
                }
                case '\u00D3':
                case '\u00D2':
                case '\u1ECE':
                case '\u00D5':
                case '\u1ECC':
                case '\u00D4':
                case '\u1ED0':
                case '\u1ED2':
                case '\u1ED4':
                case '\u1ED6':
                case '\u1ED8':
                case '\u01A0':
                case '\u1EDA':
                case '\u1EDC':
                case '\u1EDE':
                case '\u1EE0':
                case '\u1EE2':
                case '\u020E': {
                    result[i] = 'O';
                    break;
                }
                case '\u00DA':
                case '\u00D9':
                case '\u1EE6':
                case '\u0168':
                case '\u1EE4':
                case '\u01AF':
                case '\u1EE8':
                case '\u1EEA':
                case '\u1EEC':
                case '\u1EEE':
                case '\u1EF0': {
                    result[i] = 'U';
                    break;
                }
                case '\u00DD':
                case '\u1EF2':
                case '\u1EF6':
                case '\u1EF8':
                case '\u1EF4': {
                    result[i] = 'Y';
                    break;
                }

                case '\u0110':
                case '\u00D0':
                case '\u0089': {
                    result[i] = 'D';
                    break;
                }
                default:
                    result[i] = arrChar[i];
            }
        }
        return new String(result);
    }

    public static Object convertNullToEmpty(Object value) {
        return value == null ? "" : value;
    }


    public static boolean safeEqual(Object obj1, Object obj2) {
        return ((obj1 != null) && (obj2 != null) && obj2.toString().equals(obj1.toString()));
    }

    //thiendn1: format cho don vi tien te khi in hoa don
    public static Object convertCommaToDot(Object value) {
        if (!(value instanceof Number)) {
            return value;
        }
        DecimalFormat formatter = new DecimalFormat("###,###");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(value);
    }

    public static String getMimeType(String fileExtension) {
        switch (fileExtension) {
            case "pdf":
                return "application/pdf";
            case "png":
                return "image/png";
            case "jpg":
                return "image/jpeg";
            case "bmp":
                return "image/bmp";
            case "gif":
                return "image/gif";
            case "jpe":
                return "image/jpeg";
            case "jpeg":
                return "image/jpeg";
            default:
                return "";
        }
    }

    public static boolean compareTwoObj(Object oldObj, Object newObj) {
        try {
            if ((oldObj == null && newObj != null) || (oldObj != null && newObj == null)) {
                return false;
            }
            if (oldObj == null) {
                return true;
            }
            if (!safeEqual(oldObj.getClass().getName(), newObj.getClass().getName())) {
                return false;
            }
            Method[] arrMethod = oldObj.getClass().getDeclaredMethods();
            Method tempMethod;
            for (Method anArrMethod : arrMethod) {
                tempMethod = anArrMethod;
                if (!tempMethod.getName().startsWith("get")) {
                    continue;
                }
                Object oldBO = tempMethod.invoke(oldObj);
                Object newBO = tempMethod.invoke(newObj);
                String oldValue = "";
                if (oldBO != null) {
                    if (oldBO instanceof Date) {
                        //                        oldValue = DateTimeUtils.convertDateTimeToString((Date) oldBO);
                        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        oldValue = yyyyMMdd.format(oldBO);
                    } else {
                        oldValue = oldBO.toString();
                    }
                }
                String newValue = "";
                if (newBO != null) {
                    if (newBO instanceof Date) {
                        //                        newValue = DateTimeUtils.convertDateTimeToString((Date) newBO);
                        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        newValue = yyyyMMdd.format(newBO);
                    } else {
                        newValue = newBO.toString();
                    }
                }
                if (!oldValue.equals(newValue)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            //            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public static String getEndPoint() {
        String endPoint = "";
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"), Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            String hostname = InetAddress.getLocalHost().getHostName();
            InetAddress addresses = InetAddress.getByName(hostname);
            for (ObjectName obj : objs) {
                String port = obj.getKeyProperty("port");
                String host = addresses.getHostAddress();
                endPoint = host + ":" + port;
                return endPoint;
            }
            if (isNullOrEmpty(endPoint)) {
                endPoint = addresses.getHostAddress();
            }
        } catch (Exception ex) {
            //            logger.error(ex.getMessage(), ex);
        }
        return endPoint;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(number.toString(16));

            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }

            return hashtext.toString();
        } catch (Exception e) {
            //            logger.error("File name cannot encrypt: " + input);
            //            logger.error(e.getMessage(), e);
        }
        return "";
    }

    public static boolean checkDigit(String str) {
        return str.matches("(\\d+)");
    }

    public static boolean checkNotSpecialCharacter(String str) {
        return str.matches("^[A-Za-z0-9_]+");
    }


    static private byte[] getKey(byte[] arrBTmp) throws Exception {
        byte[] arrB = new byte[8];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        return arrB;
    }


    public static Long safeAbs(Long number) {
        return safeAbs(number, 0L);

    }

    public static Long safeAbs(Long number, Long defaultValue) {
        if (number == null) {
            if (defaultValue == null) {
                return 0L;
            }
            return defaultValue < 0 ? -defaultValue : defaultValue;
        }

        return number < 0 ? -number : number;
    }

    public static String firstNonEmpty(String... strings) {
        for (String string : strings) {
            if (!isNullOrEmpty(string)) {
                return string;
            }
        }
        return "";
    }

    /**
     * ham làm tron so voi so thap phan sau dau phay
     *
     * @param value
     * @param numberAfterDot
     * @return
     */
    public static BigDecimal roundNumber(BigDecimal value, int numberAfterDot) {
        return value.setScale(numberAfterDot, RoundingMode.HALF_UP);
    }

    /**
     * Distinct by key
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static String createSlug(String str) {
        // Chuyển hết sang chữ thường
        str = str.toLowerCase();

        // xóa dấu
        str = str.replaceAll("(à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ)", "a");
        str = str.replaceAll("(è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ)", "e");
        str = str.replaceAll("(ì|í|ị|ỉ|ĩ)", "i");
        str = str.replaceAll("(ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ)", "o");
        str = str.replaceAll("(ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ)", "u");
        str = str.replaceAll("(ỳ|ý|ỵ|ỷ|ỹ)", "y");
        str = str.replaceAll("đ", "d");

        // Xóa ký tự đặc biệt
        str = str.replaceAll("([^0-9a-z-\\s])", "");

        // Xóa khoảng trắng thay bằng ký tự -
        str = str.replaceAll("(\\s+)", "-");

        // xóa phần dự - ở đầu
        str = str.replaceAll("^-+", "");

        // xóa phần dư - ở cuối
        str = str.replaceAll("-+$", "");

        // return
        return str;
    }

    public static boolean isUnicodeString(String org) {
        char[] arrChar = org.toCharArray();

        for (int i = 0; i < arrChar.length; ++i) {
            switch (arrChar[i]) {
                case '\u0089':
                case 'Ð':
                case 'Đ':
                    return true;
                case 'À':
                case 'Á':
                case 'Â':
                case 'Ã':
                case 'Ă':
                case 'Ǎ':
                case 'Ȃ':
                case 'Ạ':
                case 'Ả':
                case 'Ấ':
                case 'Ầ':
                case 'Ẩ':
                case 'Ẫ':
                case 'Ậ':
                case 'Ắ':
                case 'Ằ':
                case 'Ẳ':
                case 'Ẵ':
                case 'Ặ':
                    return true;
                case 'È':
                case 'É':
                case 'Ê':
                case 'Ȇ':
                case 'Ẹ':
                case 'Ẻ':
                case 'Ẽ':
                case 'Ế':
                case 'Ề':
                case 'Ể':
                case 'Ễ':
                case 'Ệ':
                    return true;
                case 'Ì':
                case 'Í':
                case 'Ĩ':
                case 'Ỉ':
                case 'Ị':
                    return true;
                case 'Ò':
                case 'Ó':
                case 'Ô':
                case 'Õ':
                case 'Ơ':
                case 'Ȏ':
                case 'Ọ':
                case 'Ỏ':
                case 'Ố':
                case 'Ồ':
                case 'Ổ':
                case 'Ỗ':
                case 'Ộ':
                case 'Ớ':
                case 'Ờ':
                case 'Ở':
                case 'Ỡ':
                case 'Ợ':
                    return true;
                case 'Ù':
                case 'Ú':
                case 'Ũ':
                case 'Ư':
                case 'Ụ':
                case 'Ủ':
                case 'Ứ':
                case 'Ừ':
                case 'Ử':
                case 'Ữ':
                case 'Ự':
                    return true;
                case 'Ý':
                case 'Ỳ':
                case 'Ỵ':
                case 'Ỷ':
                case 'Ỹ':
                    return true;
                case 'à':
                case 'á':
                case 'â':
                case 'ã':
                case 'ă':
                case 'ǎ':
                case 'ȃ':
                case 'ạ':
                case 'ả':
                case 'ấ':
                case 'ầ':
                case 'ẩ':
                case 'ẫ':
                case 'ậ':
                case 'ắ':
                case 'ằ':
                case 'ẳ':
                case 'ẵ':
                case 'ặ':
                    return true;
                case 'è':
                case 'é':
                case 'ê':
                case 'ȇ':
                case 'ẹ':
                case 'ẻ':
                case 'ẽ':
                case 'ế':
                case 'ề':
                case 'ể':
                case 'ễ':
                case 'ệ':
                    return true;
                case 'ì':
                case 'í':
                case 'ĩ':
                case 'ỉ':
                case 'ị':
                    return true;
                case 'ò':
                case 'ó':
                case 'ô':
                case 'õ':
                case 'ơ':
                case 'ȏ':
                case 'ọ':
                case 'ỏ':
                case 'ố':
                case 'ồ':
                case 'ổ':
                case 'ỗ':
                case 'ộ':
                case 'ớ':
                case 'ờ':
                case 'ở':
                case 'ỡ':
                case 'ợ':
                    return true;
                case 'ù':
                case 'ú':
                case 'ũ':
                case 'ư':
                case 'ụ':
                case 'ủ':
                case 'ứ':
                case 'ừ':
                case 'ử':
                case 'ữ':
                case 'ự':
                    return true;
                case 'ý':
                case 'ỳ':
                case 'ỵ':
                case 'ỷ':
                case 'ỹ':
                    return true;
                case 'đ':
                    return true;
                default:
                    continue;
            }
        }
        return false;
    }

    public static String getRandomString(String validChars, int lengthString) {
        int rnum;
        String rVal = "";
        for (int i = 0; i < lengthString; i++) {
            rnum = (int) Math.floor(Math.random() * validChars.length());
            rVal += validChars.substring(rnum, rnum + 1);
        }
        return rVal;
    }

    public static String toJson(List<Object> objects) {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            return jsonMapper.writeValueAsString(objects);
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean isWhiteSpace(String input) {
        return input.trim().isEmpty();
    }

    public static boolean isNumberDecimal(String string) {
        return !isNullOrEmpty(string) && string.trim().matches("-?\\d+(\\.\\d+)?");
    }

    public static String genParamContainingWildcardCharacter(String param) {
        if (DataUtil.isNullOrEmpty(param)) {
            return "";
        }

        return param.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
    }

    public static String date2StringByPattern(Date date, String pattern) {
        if (date == null || DataUtil.isNullOrEmpty(pattern)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static <T> List<T> convertObjectsToClassDynamic(List<String> attConvert, List<Object[]> objects, Class<T> clazz) {
        if (DataUtil.isNullOrEmpty(objects)) {
            return new ArrayList<>();
        }
        return objects.stream().map(item -> {
            try {
                return convertObjectsToClassDynamic(attConvert, item, clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public static <T> T convertObjectsToClassDynamic(List<String> attConvert,
                                                     Object[] objects, Class<T> clazz) throws Exception {
        Object object = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < attConvert.size(); i++) {
            Field f;
            int finalIndex = i;
            f = Arrays.stream(fields).filter(item -> attConvert.get(finalIndex).equals(item.getName())).findFirst().orElse(null);
            if (f != null) {
                f.setAccessible(true);
                Class<?> t = f.getType();
                if (objects[i] == null)
                    continue;
                switch (t.getName()) {
                    case "java.lang.String":
                        if (objects[i] instanceof String || objects[i] instanceof Long || objects[i] instanceof BigInteger ||
                                objects[i] instanceof Integer || objects[i] instanceof BigDecimal) {
                            f.set(object, DataUtil.safeToString(objects[i]));
                        } else if (objects[i] instanceof java.sql.Date || objects[i] instanceof Date
                                || objects[i] instanceof Timestamp
                        ) {
                            f.set(object, date2StringByPattern(DataUtil.safeToDate(objects[i]), "dd/MM/yyyy HH:mm:ss"));
                        }
                        break;
                    case "java.lang.Long":
                    case "long":
                        f.set(object, DataUtil.safeToLong(objects[i]));
                        break;
                    case "java.lang.Double":
                    case "double":
                        f.set(object, DataUtil.safeToDouble(objects[i]));
                        break;
                    case "java.lang.Boolean":
                    case "boolean":
                        f.set(object, objects[i]);
                        break;
                    case "java.util.Date":
                        f.set(object, DataUtil.safeToDate(objects[i]));
                        break;
                    case "java.time.LocalDate":
                        f.set(object, DataUtil.safeToLocalDate(objects[i]));
                        break;
                    case "java.sql.Timestamp":
                        f.set(object, DataUtil.safeToTimestamp(objects[i]));
                        break;
                    case "java.lang.Integer":
                    case "int":
                        f.set(object, DataUtil.safeToInteger(objects[i]));
                        break;
                    case "java.math.BigInteger":
                        f.set(object, DataUtil.safeToBigInteger(objects[i]));
                        break;
                    case "java.math.BigDecimal":
                        f.set(object, DataUtil.safeToBigDecimal(objects[i]));
                        break;
                    default:
                        break;
                }
            }
        }
        return (T) object;
    }

    public static Timestamp safeToTimestamp(Object obj) {
        return Optional.ofNullable(obj).map(o -> (Timestamp) o).orElse(null);
    }

    public static LocalDate safeToLocalDate(Object obj) {
        if (obj instanceof LocalDate) {
            return (LocalDate) obj;
        }
        return null;
    }

    public static List<String> changeParamTypeSqlToJava(String sqlType) {
        String[] tmp = sqlType.trim().split(",");
        List<String> stringList = new ArrayList<>();
        for (String s : tmp) {
            s = s.trim().replaceAll("\\s+,", "");
            StringBuilder builder = new StringBuilder();
            String[] words = s.split("[\\W_]+");
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (words.length > 1) {
                    if (i == 0) {
                        word = word.isEmpty() ? word : word.toLowerCase();
                    } else {
                        word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
                    }
                } else {
                    word = word.isEmpty() ? word : word.toLowerCase();
                }
                builder.append(word);
            }
            stringList.add(builder.toString());
        }
        return stringList;
    }

    public static <T> List<T> sortList(List<T> value, Class<T> clazz, List<String> fieldSort) {
        value = value == null ? new ArrayList<>() : value;
        if (DataUtil.isNullOrEmpty(fieldSort)) {
            return value;
        } else
            return value.stream()
                    .sorted((object1, object2) -> {
                        try {
                            for (String propertyName : fieldSort) {
                                try {
                                    Field field = clazz.getDeclaredField(propertyName);
                                    field.setAccessible(true);
                                    Object value1 = field.get(object1);
                                    Object value2 = field.get(object2);
                                    if (value1 == null && value2 == null) {
                                        continue;
                                    }
                                    if (value1 == null) {
                                        return 1;
                                    }
                                    if (value2 == null) {
                                        return -1;
                                    }
                                    int result = compareTo(value1, value2);

                                    if (result != 0) {
                                        return result;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            return 0;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }).collect(Collectors.toList());
    }

    private static int compareTo(Object a, Object b) throws Exception {
        if (a == null || b == null) {
            return -1;
        }
        Class c = a.getClass();
        if (c.equals(Date.class) || c.equals(Timestamp.class)) {
            return ((Date) a).compareTo((Date) b);
        }
        return DataUtil.safeToString(a).compareTo(DataUtil.safeToString(b));
    }

    private static String getValueField(Object a, String name) {
        if (a == null || DataUtil.isNullOrEmpty(name)) {
            return null;
        }
        try {
            Field field = a.getClass().getDeclaredField(name);
            field.setAccessible(true);
            Class<?> c = field.getType();
            if (c.equals(Date.class) || c.equals(Timestamp.class)) {
                return DataUtil.date2StringByPattern((Date) field.get(a), "dd/MM/yyyy HH:mm:ss");
            }
            return DataUtil.safeToString(field.get(a));
        } catch (Exception e) {
            log.error("====NoSuchMethodException do not get value=====");
            return null;
        }

    }

    public static String createInQuery(String prefix, List objs) {
        String inQuery = " IN (";
        for (int idx = 1; idx < objs.size() + 1; idx++) {
            inQuery += ",:" + prefix + idx;
        }
        inQuery += ") ";
        inQuery = inQuery.replaceFirst(",", "");

        return inQuery;
    }

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static String doubleToCommaString(Double number) {
        // Check if the number is null.
        if (number == null) {
            return null;
        }

        // Round the number to 2 digits after the decimal point.
        double roundedNumber = Math.round(number * 100) / 100.0;

        // Split the number into its integer and decimal parts.
        String integerPart = String.valueOf((int) roundedNumber);
        String decimalPart = String.valueOf(roundedNumber).substring(integerPart.length() + 1);

        // Add commas to the integer part.
        integerPart = integerPart.replaceAll("(\\d{3})(?=(\\d{3})+(?!\\d))", "$1,");

        // Return the formatted string.
        return integerPart + "." + decimalPart;
    }

    public static String convertMessageFormat(String pattern, Object ... arguments) {
        if(arguments == null){
            return pattern;
        }else{
            return MessageFormat.format(pattern, arguments);
        }
    }

    public static <T>T cloneBeanV2(T source, Class cl) {
        try {
            if (source == null) {
                return null;
            }
            Object dto = cl.getConstructor().newInstance();
            BeanUtils.copyProperties(source, dto);
            return (T) dto;
        } catch (Exception e) {
            return null;
        }
    }
    //<editor-fold desc="Convert to like conditional">
    public static String convertToLikeConditional(String conditional) {
        if (isNullOrEmpty(conditional)) return "%%";
        return "%" + conditional.replace("%", "\\%").replace("_", "\\_").toUpperCase() + "%";
    }
    //</editor-fold>
    public static <T> T convertObjectsToClass(Object[] objects, Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                if (i > objects.length - 1) break;

                Field f = fields[i];

                f.setAccessible(true);
                Class t = f.getType();

                Object item = objects[i];
                if (item == null) continue;
                if (String.class.getName().equalsIgnoreCase(t.getName())) {
                    if (item instanceof String || item instanceof Long || item instanceof Character) {
                        f.set(object, DataUtil.safeToString(item));
                    } else if (item instanceof java.sql.Date || item instanceof java.util.Date
                            || item instanceof java.sql.Timestamp
                    ) {
                        f.set(object, DateUtil.date2StringByPattern(DataUtil.safeToDate(item), Const.DATE_FORMAT));
                    }
                } else if (Long.class.getName().equalsIgnoreCase(t.getName()) || long.class.getName().equalsIgnoreCase(t.getName())) {
                    f.set(object, DataUtil.safeToLong(item));
                } else if (Double.class.getName().equalsIgnoreCase(t.getName()) || double.class.getName().equalsIgnoreCase(t.getName())) {
                    f.set(object, DataUtil.safeToDouble(item));
                } else if (Integer.class.getName().equalsIgnoreCase(t.getName()) || int.class.getName().equalsIgnoreCase(t.getName())) {
                    f.set(object, DataUtil.safeToInt(item));
                } else if (Boolean.class.getName().equalsIgnoreCase(t.getName()) || boolean.class.getName().equalsIgnoreCase(t.getName())) {
                    f.set(object, item);
                } else if (java.util.Date.class.getName().equalsIgnoreCase(t.getName())) {
                    f.set(object, DataUtil.safeToDate(item));
                }

            }

            return (T) object;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
}

