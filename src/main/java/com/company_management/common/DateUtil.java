package com.company_management.common;



import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Admin
 * @version 1.0
 */
public final class DateUtil {

//    private static Logger logger = Logger.getLogger(DateUtil.class);

    public static Date xmlGregorianCalenderToDate(XMLGregorianCalendar xmlDate) {
        int year = xmlDate.getYear();
        int month = xmlDate.getMonth();
        int day = xmlDate.getDay();

        return new GregorianCalendar(year, month - 1, day).getTime();
    }

    public static XMLGregorianCalendar dateToXmlGregorianCalender(Date date) throws DatatypeConfigurationException {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

        return xmlGregCal;
    }
    public static boolean compare(Date date1, Date date2, String format) throws Exception {
        if (date1 == null || date2 == null) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date1).equals(dateFormat.format(date2));
    }

    public static boolean equalOnlyField(Date date1, Date date2, int type) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        return c1.get(type) == c2.get(type);

    }
    /**
     * @param date1 Date
     * @param date2 Date
     * @return long
     */
    public static long minusDate(Date date1, Date date2) {
        return date1.getTime() - date2.getTime();
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2MMyyString(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("MM/yyyy");
            return date.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2ddMMyyNoSlashString(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("ddMMyyyy");
            return date.format(value);
        }
        return "";
    }

    public static String date2yyyyMMddStringWithSlash(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
            return date.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2yyyyMMddHHString(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHH");
            return date.format(value);
        }
        return "";
    }

    public static Date string2DateTime(String value) throws ParseException {
        if (!DataUtil.isNullOrEmpty(value)) {
            SimpleDateFormat dateTime = new SimpleDateFormat(
                    "dd/MM/yyyy hh:mm:ss");
            return dateTime.parse(value);
        }
        return null;
    }

    public static Date string2Date(String value) {
        return DateUtil.string2DateByPattern(value, "dd/MM/yyyy");
    }

    public static Date string2DateByPattern(String value, String pattern) {
        if (!DataUtil.isNullOrEmpty(value)) {
            SimpleDateFormat dateTime = new SimpleDateFormat(pattern);
            dateTime.setLenient(false);
            try {
                return dateTime.parse(value);
            } catch (ParseException ex) {
                return null;
            }
        }
        return null;
    }

    /**
     * @param value String
     * @return Date
     */
    public static Date string2Date(String value, String format) {
        try {
            SimpleDateFormat dbUpdateDateTime = new SimpleDateFormat(format);
            return dbUpdateDateTime.parse(value);
        } catch (ParseException ex) {
        }

        return new Date();
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2String(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
            return date.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2StringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateNoSlash = new SimpleDateFormat("yyyyMMdd");
            return dateNoSlash.format(value);
        }
        return "";
    }

    public static Date addDate(Date date, int numDate) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, numDate);
            date = cal.getTime();
        }
        return date;
    }

    /**
     * @param value Date
     * @return String
     */
    public static String dateH2StringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateNoSlash = new SimpleDateFormat("yyyyMMddHH");
            return dateNoSlash.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String dateTime2StringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat(
                    "yyyyMMddHHmmss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String dateTime2String(Date value) {
        if (value != null) {
            SimpleDateFormat dateTime = new SimpleDateFormat(
                    "yyyy/MM/dd HH:mm:ss");
            return dateTime.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String dbUpdateDateTime2String(Date value) {
        if (value != null) {
            SimpleDateFormat dbUpdateDateTime = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            return dbUpdateDateTime.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String convertDataToMonthYearString(Date value) {
        if (value != null) {
            SimpleDateFormat dbUpdateDateTime = new SimpleDateFormat(
                    "yyyy-MM");
            return dbUpdateDateTime.format(value);
        }
        return "";
    }


    /**
     * @param value Date
     * @return Timestamp
     */
    public static Timestamp date2Timestamp(Date value) {
        if (value != null) {
            return new Timestamp(value.getTime());
        }
        return null;
    }

    /**
     * @return Date
     */
    public static Date sysDate() {
        return new Date();
    }

    /**
     * return now if input date is null, otherwise return date
     *
     * @param date
     * @return
     */
    public static Date dateToNow(Date date) {
        return date == null ? new Date() : date;
    }

    /**
     * @return Date
     */
    public static Date sysdateYmd() {
        return nextdate(0);
    }

    /**
     * @param day integer
     * @return Date
     */
    public static Date nextdate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE) + day, 0, // hour
                0, // min
                0); // sec
        /**
         * clear millisecond field
         */
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    /**
     * @param date Date
     * @param day  integer
     * @return Date
     */
    public static Date nextdate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE) + day, 0, // hour
                0, // min
                0); // sec
        /**
         * clear millisecond field
         */
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    /**
     * get the next n month.
     *
     * @param date  Date
     * @param month number of next month
     * @return Date
     */
    public static Date nextMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + month,
                calendar.get(Calendar.DATE),
                0, // hour
                0, // min
                0); // sec
        /**
         * clear millisecond field
         */
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    public static Date nextMonthDateTime(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * get the previos n month
     *
     * @param date  Date
     * @param month integer
     * @return Date
     */
    public static Date getPreMonthDate(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) - month,
                calendar.get(Calendar.DATE),
                0, // hour
                0, // min
                0); // sec
        /**
         * clear millisecond field
         */
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();

    }

    /**
     * @return String
     */
    public static String sysdateString() {
        SimpleDateFormat dbUpdateDateTime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return dbUpdateDateTime.format(new Date());
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDate() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        return date;
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDateTime() {
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateTime;
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDateTimeMinute() {
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return dateTime;
    }

    /**
     * [timestampToStringFF function.].<br>
     * [Detail description of method.]
     *
     * @param date Timestamp
     * @return String
     */
    public static String timestampToStringFF(Timestamp date) {
        if (date != null) {
            SimpleDateFormat dbDateTimeString = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.SSS");
            return dbDateTimeString.format(date);
        }
        return "";
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDbUpdateDateTime() {
        SimpleDateFormat dbUpdateDateTime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return dbUpdateDateTime;
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getYYYYMM() {
        SimpleDateFormat yyyymm = new SimpleDateFormat("yyyyMM");
        return yyyymm;
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getMMdd() {
        SimpleDateFormat mmdd = new SimpleDateFormat("MM/dd");
        return mmdd;
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2ddMMyyyyString(Date value) {
        if (value != null) {
            SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
            return ddMMyyyy.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String getFirstDateOfMonth(Date value) {
        if (value != null) {
            SimpleDateFormat MMyyyy = new SimpleDateFormat("MM/yyyy");
            return "01/" + MMyyyy.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2MMyyyyString(Date value) {
        if (value != null) {
            SimpleDateFormat yyyyMM = new SimpleDateFormat("MM/yyyy");
            return yyyyMM.format(value);
        }
        return "";
    }

    /**
     * date to yyMMddString
     *
     * @param value Date
     * @return String
     */
    public static String date2yyMMddString(Date value) {
        if (value != null) {
            SimpleDateFormat yyMMdd = new SimpleDateFormat("yy/MM/dd");
            return yyMMdd.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2yyMMddStringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd");
            return yyMMdd.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2yyyyMMStringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat yyyymm = new SimpleDateFormat("yyyyMM");
            return yyyymm.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2yyyyMMddStringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat yyyymm = new SimpleDateFormat("yyyyMMdd");
            return yyyymm.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2yyMMStringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat yymm = new SimpleDateFormat("yyMM");
            return yymm.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2MMddString(Date value) {
        if (value != null) {
            SimpleDateFormat mmdd = new SimpleDateFormat("MM/dd");
            return mmdd.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String second2String(Date value) {
        if (value != null) {
            return SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM).format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return []String
     */
    public static String[] getSplitDate(Date value) {
        if (value != null) {
            DecimalFormat df = new DecimalFormat("00");
            String[] dateTime = dateTime2String(value).split(" ");
            String[] date = new String[6];
            String[] tmpDate = dateTime[0].split("/");
            date[0] = df.format(Integer.parseInt(tmpDate[0]));
            date[1] = df.format(Integer.parseInt(tmpDate[1]));
            date[2] = df.format(Integer.parseInt(tmpDate[2]));
            tmpDate = dateTime[1].split(":");
            date[3] = df.format(Integer.parseInt(tmpDate[0]));
            date[4] = df.format(Integer.parseInt(tmpDate[1]));
            date[5] = df.format(Integer.parseInt(tmpDate[2]));
            return date;
        }
        return new String[6];
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2MMddTime(Date value) {
        if (value != null) {
            SimpleDateFormat mmdd = new SimpleDateFormat("MM/dd HH:mm:ss");
            return mmdd.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2YYYYMMddTime(Date value) {
        if (value != null) {
            SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return yyyyMMdd.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2HHMMssNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("HHmmss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2ddMMyyyyHHMMssNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("ddMMyyyyHHmmss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }


    /**
     * @param value Date
     * @return String
     */
    public static String dateyyyyMMddHHmmSS(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     * @Editor Nguyen Van Trung
     * @Update 14/10/2020
     */
    public static String dateTimeHyphenyyyyMMdd(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeHyphen = new SimpleDateFormat("yyyy-MM-dd");
            return dateTimeHyphen.format(value);
        }
        return "";
    }

    /**
     * @return
     */
    public static Timestamp nowDateMilli() {
        return new Timestamp(sysDate().getTime());
    }

    /**
     * @param date Date
     * @return integer
     */
    public static int getYY(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.YEAR)) % 100;
    }

    /**
     * @param nowDate Date
     * @return integer
     */
    public static int getMonth(Date nowDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * @param nowDate Date
     * @return integer
     */
    public static int getDay(Date nowDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDate(Date nowDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        return calendar.get(Calendar.DATE);
    }

    //==========================================================================

    /**
     * addMilli.<br>
     *
     * @param nowDate Date
     * @param period  integer
     * @return Timestamp
     */
    //==========================================================================
    public static Timestamp addMilli(Timestamp nowDate, int period) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.MILLISECOND, period);

        Timestamp stopTerm = date2Timestamp(calendar.getTime());

        return stopTerm;
    }
    public static Date getddMMyyyy(Date date) throws Exception {
        Date result = date;
        if (result != null) {
            String strDate = DateUtil.date2ddMMyyyyString(date);
            result = DateUtil.string2Date(strDate);
        }
        return result;
    }

    public static Date convertSqlDateToUtilDate(java.sql.Date sqlDate) {
        return new Date(sqlDate.getTime());
    }

    public static Date convertStringToTime(String date, String pattern) throws ParseException {
        if (date == null || "".equals(date.trim())) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);

    }

    /**
     * Ham Reset thoi gian ve cung mot thoi diem
     *
     * @param dateTime
     * @return
     */
    public static Date truncTime(Date dateTime) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(dateTime);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
            return null;
        }
        return cal.getTime();
    }

    public static Date safeStringToDateByPattern(String dateStr) {
        String pattern = Constants.DATE_FORMAT;
        try {
            if (dateStr == null || "".equals(dateStr.trim())) {
                return null;
            }

            Date date = new SimpleDateFormat(pattern).parse(dateStr);
            return date;
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date convertStringToDate(String dateStr, String pattern) throws ParseException {
        if (dateStr == null || "".equals(dateStr.trim())) {
            return null;
        }

        return new SimpleDateFormat(pattern).parse(dateStr);
    }

    public static String safeDate2StringByPattern(Date date) {
        String pattern = Constants.DATE_FORMAT;
        try {
            if (date == null || DataUtil.isNullOrEmpty(pattern)) {
                return null;
            }

            DateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String date2StringByPattern(Date date, String pattern) throws ParseException {
        if (date == null || DataUtil.isNullOrEmpty(pattern)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static void main(String[] args) {
    }

    public static String dateToStringWithPattern(Date date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(date);
        }
    }

    public static String dateToStringWithPatternIfNull(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return dateToStringWithPattern(date, pattern);
    }

    public static Long date2LongUpdateDateTime(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
            String updateDateTime = date.format(value);
            return Long.parseLong(updateDateTime);
        }
        return null;
    }

    /**
     * kiem tra tuoi khach hang o nam trong khoang min - max hay ko
     *
     * @param minOld
     * @param maxOld
     * @param customerBirthDay
     * @param sysDate
     * @return
     * @throws Exception
     * @author quangkm
     */
    public static boolean checkCustomerAge(int minOld, int maxOld, Date customerBirthDay, Date sysDate) throws Exception {
        try {
            Calendar birthDateCalendar = Calendar.getInstance();
            birthDateCalendar.setTime(customerBirthDay);
            Calendar currDateCalendar = Calendar.getInstance();
            currDateCalendar.setTime(sysDate);

            int yearOfBirthDate = birthDateCalendar.get(Calendar.YEAR);

            int yearOfCurrDate = currDateCalendar.get(Calendar.YEAR);

            return minOld <= yearOfCurrDate - yearOfBirthDate && maxOld >= yearOfCurrDate - yearOfBirthDate;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * convert date sang string dinh dang nam-thang-ngay-gio-phut-giay
     *
     * @param value
     * @return
     * @author quangkm
     */
    public static String date2yyyyMMddHHmmSsString(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
            return date.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2ddMMyyyyHHMMss(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }
    public static Date addMonth(Date date, int... numMonth) {
        for (int i : numMonth) {
            date = addMonth(date, i);
        }
        return date;
    }

    public static XMLGregorianCalendar convertToXmlDate(Date date) throws DatatypeConfigurationException {
        if (date == null) {
            return null;
        }
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }

    //date1 < date2 (return <0)

    /**
     * @param date1
     * @param date2
     * @description So sanh chinh xac 2 ngay
     */
    public static boolean greaterOrEqualsddMMyyyy(Date date1, Date date2, int type) {
        return date1.compareTo(date2) > 0 || equalsByTypeddMMyyyy(date1, date2, type);
    }

    /**
     * @param date1
     * @param date2
     * @description So sanh chinh xac
     */
    public static boolean equalsByTypeddMMyyyy(Date date1, Date date2, int type) {
        return equalOnlyField(date1, date2, type) && equalOnlyField(date1, date2, Calendar.YEAR);
    }

    public static boolean isDate(String str, String datePattern) {
        if (str.length() != datePattern.length()) return false;

        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        try {
            sdf.setLenient(false);
            Date date = sdf.parse(str);
            if (date == null) {
                return false;
            }
        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
            return false;
        }
        return true;
    }
    public static boolean isDuplicateDateTime(Date effectFrom, Date until, Date oldEffectFrom, Date oldUntil) {
        //
        if (oldUntil == null) {
            //Ngay moi nhap fai co ngay ket thuc va ngay ket thuc nho hon oldEffectFrom
            if (until == null) {
                //Neu den ngay` moi cung~ == null thi coi la trung lap
                return true;
            } else {
                //neu den ngay moi != null
                //neu den ngay moi >= den ngay cu~
                return until.equals(oldEffectFrom) || until.after(oldEffectFrom);
            }
        } else {
            //oldUntil != null
            if (until == null) {
                //neu den ngay moi == null
                //Neu tu ngay moi <= den ngay` cu~
                return effectFrom.equals(oldUntil) || effectFrom.before(oldUntil);
            } else {
                //den ngay moi != null
                if (effectFrom.equals(oldEffectFrom) || effectFrom.equals(oldUntil) || until.equals(oldEffectFrom) || until.equals(oldUntil)) {
                    //Neu truong tu ngay hoac den ngay moi bi trung voi tu ngay, den ngay cu~
                    return true;
                }
                if (effectFrom.after(oldEffectFrom) && effectFrom.before(oldUntil)) {
                    //Neu tu ngay moi nam trong khoang ngay` cu~
                    return true;
                }
                if (until.after(oldEffectFrom) && until.before(oldUntil)) {
                    //Neu den ngay moi nam trong khoang ngay` cu~
                    return true;
                }
                return effectFrom.before(oldEffectFrom) && until.after(oldUntil);
            }
        }
    }

    public static java.time.LocalDateTime toLocalDateTime(Long epochMilli) {
        return java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
    }

    public static java.time.LocalDate toLocalDate(Date input) {
        return input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public static String object2ddMMyyyyHHmmss(Object oldBO) {
        String value = "";
        if (oldBO != null) {
            if (oldBO instanceof Date || oldBO instanceof java.sql.Date) {
//                        oldValue = DateTimeUtils.convertDateTimeToString((Date) oldBO);
                SimpleDateFormat yyyyMMdd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                value = yyyyMMdd.format(oldBO);
            } else {
                value = oldBO.toString();
            }
        }
        return value;
    }

    public static boolean isValidDate(String dateToValidate) {
        if (DataUtil.isStringNullOrEmpty(dateToValidate)) {
            return true;
        }
        return isValidFormatDate(dateToValidate, Constants.DATE_FORMAT);
    }

    public static boolean isValidFormatDate(String dateToValidate, String datePartern) {

        if (dateToValidate == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(datePartern);
        sdf.setLenient(false);
        try {
            sdf.parse(dateToValidate);
        } catch (ParseException e) {
//            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
    public static Date setTimeByCalendar(Date date, int hourVal, int minuteVal, int secondVal) {
        Calendar calendarEffectNewProm = Calendar.getInstance();
        calendarEffectNewProm.setTime(date);
        calendarEffectNewProm.set(Calendar.HOUR, hourVal);
        calendarEffectNewProm.set(Calendar.MINUTE, minuteVal);
        calendarEffectNewProm.set(Calendar.SECOND, secondVal);
        return calendarEffectNewProm.getTime();
    }

    public static Date truncateToStartOfDay(Date date) {
        if (DataUtil.isNullObject(date)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Set hours, minutes, seconds, and milliseconds to zero
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
    public static Date truncateToFirstDayOfMonth(Date date) {
        if (DataUtil.isNullObject(date)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}

