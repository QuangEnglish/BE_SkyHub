package com.company_management.utils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@NoArgsConstructor
@Slf4j
public class DateTimeUtils {
  private static final String dateFormat1 = "dd/MM/yyyy HH:mm:ss";

  public static String getPreviousMonthDateString() {
    Format formatter = new SimpleDateFormat(dateFormat1);
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MONTH, -1);
    return formatter.format(cal.getTime());
  }

  public static String getTimeError() {
    String result = "";
    try {
      result = " Thời gian:" + convertDateToStringFull(new Date());
    } catch (Exception ex) {
      log.error("Error: ", ex);
    }
    return result;
  }

  public static String convertDateToStringFull(Date date) {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat1);
      return dateFormat.format(date);
    } catch (Exception e) {
      log.error("ERROR", e);
      return "";
    }
  }

  public static Date convertStringToTime(String date, String pattern) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    try {
      return dateFormat.parse(date);
    } catch (ParseException e) {
      log.debug("Date ParseException, string value:" + date);
    }
    return null;
  }

  public static String convertDateToString(Date date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    if (date == null) {
      return "";
    }
    try {
      return dateFormat.format(date);
    } catch (Exception e) {
      throw e;
    }
  }

  public static String convertDateTimeToString(Date date, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    try {
      if (date != null) {
        return dateFormat.format(date);
      } else {
        return null;
      }
    } catch (Exception e) {
      throw e;
    }
  }

  public static String getSysDateTime() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat1);
    try {
      return dateFormat.format(calendar.getTime());
    } catch (Exception e) {
      throw e;
    }
  }

  public static Date convertStringToDateTime(String date) {
    String pattern = dateFormat1;
    return convertStringToTime(date, pattern);
  }


  public static String convertDateToStringByPattern(Date date, String pattern) {
    if (date == null) {
      return "";
    }
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
      return dateFormat.format(date);
    } catch (Exception e) {
      log.error("ERROR", e);
      return "";
    }
  }

  public static String getDateTimeReport() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    try {
      return dateFormat.format(calendar.getTime());
    } catch (Exception e) {
      throw e;
    }
  }

  public static int getDayByDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  public static int getMonthByDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.MONTH) + 1;
  }

  public static int getYearByDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.YEAR);
  }

  public static Integer getDayByMonthAndYear(Integer month, Integer year) {
    if (month < 0 || month > 12) {
      return -1;
    }
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month ==  10 || month == 12) {
      return 31;
    }
    if (month == 4 || month == 6 || month == 9 || month == 11) {
      return 30;
    }
    if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
      return 29;
    }
    return 28;
  }

}
