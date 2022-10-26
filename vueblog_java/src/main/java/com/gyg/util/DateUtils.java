package com.gyg.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.StringUtils;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author chengyiyong
 * @Date 2022/10/26 11:05
 **/
@Slf4j
public class DateUtils {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    public final static String STANDARD_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static Long hours =1000 * 60 * 60L;

    public static Long minutes=1000 * 60L;

    public static Long DAYINMILISECOND = 86400000l;

    public static int[] getYmd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int[] ymd = new int[] { year, month, day };
        return ymd;
    }

    public static int[] getHms(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int[] hms = new int[] { hour, minute, second };
        return hms;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        return cal;
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        return cal.getTime();
    }

    public static Calendar getFirstDayOfMonthCal(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        return cal;
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        return cal.getTime();
    }

    public static Calendar getLastDayOfMonthCal(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        return cal;
    }

    public static Date getDate(Integer year, Integer month, Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getDate(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Calendar getTodayCal() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal;
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) {
        DatatypeFactory dataTypeFactory;
        try {
            dataTypeFactory = DatatypeFactory.newInstance();
        }
        catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(date.getTime());
        return dataTypeFactory.newXMLGregorianCalendar(gc);
    }

    public XMLGregorianCalendar dateToXml(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        }
        catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return gc;
    }

    public Date xmlToDate(XMLGregorianCalendar gc) {
        GregorianCalendar ca = gc.toGregorianCalendar();
        return ca.getTime();
    }

    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static Date stringToDate(String dateStr, String pattern) {
        Date date = null;
        try {
            if (dateStr.contains("CST")) {
                date = parse(dateStr, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            }
            else {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                date = sdf.parse(dateStr);
            }
        }
        catch (ParseException e) {
            log.warn(e.getMessage(), e);
            date = parse(dateStr, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        }
        return date;
    }

    public static Date parse(String str, String pattern, Locale locale) {
        if (str == null || pattern == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        }
        catch (ParseException e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    public static int getWorkingDay(java.util.Calendar startCal, java.util.Calendar endCal) {
        int result = -1;
        if (startCal.after(endCal)) {
            java.util.Calendar swap = startCal;
            startCal = endCal;
            endCal = swap;
        }
        int charge_start_date = 0;
        int charge_end_date = 0;
        int stmp;
        int etmp;
        stmp = 7 - startCal.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - endCal.get(Calendar.DAY_OF_WEEK);
        if (stmp != 0 && stmp != 6) {
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6) {
            charge_end_date = etmp - 1;
        }
        result = (getDaysBetween(getNextMonday(startCal).getTime(), getNextMonday(endCal).getTime()) / 7) * 5
                + charge_start_date - charge_end_date;
        return result;
    }

    public static Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        }
        while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }

    public static int getHolidays(Calendar d1, Calendar d2) {
        return getDaysBetween(d1.getTime(), d2.getTime()) - getWorkingDay(d1, d2);
    }

    public static Date getBeforeDate(Date date, int days) {
        Date beforeDate = new Date();
        beforeDate.setTime(date.getTime() - DAYINMILISECOND * days);
        return beforeDate;
    }

    public static String getBeforeDateToString(String date, String pattern, int days) {
        return dateToString(getBeforeDate(stringToDate(date, pattern), days));
    }

    public static Date getAfterDate(Date date, int days) {
        Date beforeDate = new Date();
        beforeDate.setTime(date.getTime() + DAYINMILISECOND * days);
        return beforeDate;
    }

    public static Date getBeforeHourDate(Date date, double hours) {
        Date beforeHourDate = new Date();
        beforeHourDate.setTime(date.getTime() - (long) (60 * 60 * 1000 * hours) - 15 * 60 * 1000);
        return beforeHourDate;
    }

    public static Date getAfterHourDate(Date date, double hours) {
        Date beforeHourDate = new Date();
        beforeHourDate.setTime(date.getTime() + (long) (60 * 60 * 1000 * hours));
        return beforeHourDate;
    }

    public static Date getBeforeMinuteDate(Date date, double minutes) {
        Date beforeHourDate = new Date();
        beforeHourDate.setTime(date.getTime() - (long) (60 * 1000 * minutes) - 5 * 60 * 1000);
        return beforeHourDate;
    }

    public static Date getBeforeMinuteDate(Date date, double minutes, int leg) {
        Date beforeHourDate = new Date();
        beforeHourDate.setTime(date.getTime() - (long) (60 * 1000 * minutes) - leg * 60 * 1000);
        return beforeHourDate;
    }

    public static Date getAfterMinuteDate(Date date, double minutes) {
        Date beforeHourDate = new Date();
        beforeHourDate.setTime(date.getTime() + (long) (60 * 1000 * minutes));
        return beforeHourDate;
    }

    public static Date getYesterdayDate(Date today) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(today.getTime() - DAYINMILISECOND);
        return cal.getTime();
    }

    public static Date getWeekBefore(Date today) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(today.getTime() - DAYINMILISECOND * 7);
        return cal.getTime();
    }

    public static Date getTomorrowDate(Date today) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(today.getTime() + DAYINMILISECOND);
        return cal.getTime();
    }

    public static Date getLastDayInLastMonth(Date date) {
        Date firstDay = getFirstDayOfMonth(date);
        return getYesterdayDate(firstDay);
    }

    public static Date getFirstDayInNextMonth(Date date) {
        Date lastDay = getLastDayOfMonth(date);
        return getTomorrowDate(lastDay);
    }

    public static Boolean isInSameMonth(Date date1, Date date2) {
        int[] ymd1 = getYmd(date1);
        int[] ymd2 = getYmd(date2);
        return (ymd1[1] == ymd2[1]) && (ymd1[0] == ymd2[0]);
    }

    public static boolean isEmptyDate(Calendar testCal) {
        boolean emptyDate = false;
        Calendar c = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        c.clear();
        if (testCal == null) {
            emptyDate = true;
        }
        else {
            int[] ymd = DateUtils.getYmd(testCal.getTime());
            if (ymd[0] == 1970 && ymd[1] == 1 && ymd[2] == 1) {
                emptyDate = true;
            }
            else if (c.getTimeInMillis() == testCal.getTimeInMillis()) {
                emptyDate = true;
            }
        }
        return emptyDate;
    }

    public static boolean isEmptyDate(Date testDate) {
        boolean emptyDate = false;
        Calendar c = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        c.clear();
        if (testDate == null) {
            emptyDate = true;
        }
        else {
            int[] ymd = DateUtils.getYmd(testDate);
            if (ymd[0] == 1970 && ymd[1] == 1 && ymd[2] == 1) {
                emptyDate = true;
            }
            else if (c.getTimeInMillis() == testDate.getTime()) {
                emptyDate = true;
            }
        }
        return emptyDate;
    }

    public static Integer getHoursBetween(Date startTime, Date endTime) {
        Double hoursBetween = (endTime.getTime() - startTime.getTime()) / (60 * 60 * 1000d);
        return (int) hoursBetween.doubleValue();
    }

    public static Integer getDaysBetween(Date startTime, Date endTime) {
        Double daysBetween = (endTime.getTime() - startTime.getTime()) / (24 * 60 * 60 * 1000d);
        return (int) daysBetween.doubleValue();
    }

    public static java.sql.Date toSqlDate(Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public static Date getAmazonDate(String date) {
        if (StringUtils.isNotBlank(date)) {
            String parsePatterns = "yyyy-MM-dd HH:mm:ss";
            if (StrUtil.contains(date, "BST") || StrUtil.contains(date, "GMT")
                    || StrUtil.contains(date, "MEST") || StrUtil.contains(date, "MET")) {
                parsePatterns = "dd/MM/yyyy HH:mm:ss";
            }
            try {
                return new SimpleDateFormat(parsePatterns).parse(date);
            }
            catch (ParseException e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
            }
        }

        return null;
    }

    /**
     * 获取两个日期之间的日期，包括开始结束日期
     *
     * @param start 开始日期
     * @param end 结束日期
     * @return 日期集合
     */
    @SuppressWarnings("unused")
    public static List<String> getBetweenDates(Date start, Date end) {
        List<String> result = new ArrayList<String>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        result.add(dateToString(start, "yyyy-MM-dd"));
        while (tempStart.before(tempEnd)) {
            String dateStr = dateToString(tempStart.getTime(), "yyyy-MM-dd");
            result.add(dateStr);
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        result.add(dateToString(end, "yyyy-MM-dd"));

        return result;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 获取对应的年份 总天数
     */
    public static int getDaysByYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.stringToDate("" + year, "yyyy"));
        int numOfDays = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
        return numOfDays;
    }

    /**
     * 将别的时区时间转为北京时间
     *
     * @param dateStr
     * @return
     */
    public static Date convertDateToLocalDate(String dateStr) {
        // 设置时间默认格式
        String format = "yyyy-MM-dd HH:mm:ss";
        // 解析时区时间
        ZonedDateTime parse = ZonedDateTime.parse(dateStr);
        // 转换为中国时区
        ZonedDateTime zonedDateTime = parse.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
        String format1 = zonedDateTime.format(DateTimeFormatter.ofPattern(format));
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(format1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 转换时间
     * @param date
     * @return
     */
    public static Date convertDateWithOtherZone(String date) {
        if (StringUtils.isNotBlank(date)) {
            String parsePatterns = "yyyy-MM-dd'T'HH:mm:ss";
            if (StrUtil.contains(date, "BST") || StrUtil.contains(date, "GMT")
                    || StrUtil.contains(date, "MEST") || StrUtil.contains(date, "MET")) {
                parsePatterns = "dd/MM/yyyy HH:mm:ss";
            }
            else if (StrUtil.contains(date, "JST")) {
                parsePatterns = "yyyy/MM/dd HH:mm:ss";
            }
            try {
                return new SimpleDateFormat(parsePatterns).parse(date);
            }
            catch (ParseException e) {
                log.error(e.getMessage(), e);
            }
        }

        return null;
    }


    public static Date getFirstDayOfLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        return cal.getTime();
    }

    public static Date getLastDayOfLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        return cal.getTime();
    }

    public static Date getLastDayMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        return cal.getTime();
    }

    /**
     * 获取当前日期 天数差日期
     * @param dayNumber 天数  正+  负-
     */
    public static String getDayDifference(int dayNumber){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(DateUtils.STANDARD_DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        //设置当前时间
        cal.setTime(date);
        cal.add(Calendar.DATE, dayNumber);
        return  format.format(cal.getTime());
    }

    /**
     * 校验时间格式是否正确
     * @param dateStr
     */
    public static boolean isValid(String dateStr, String pattern) {
        if (dateStr==null||pattern==null){
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 获取上个月的第一天
     */
    public static String getLastMonthFirst(){
        SimpleDateFormat df =new SimpleDateFormat(DateUtils.DEFAULT_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return df.format(calendar.getTime())+" 00:00:00";
    }

    /**
     * 获取上个月的最后一天
     */
    public static String getLastMonthFinally(){
        SimpleDateFormat df =new SimpleDateFormat(DateUtils.DEFAULT_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return df.format(calendar.getTime())+" 23:59:59";
    }



    /**
     * 指定过期时间
     * 第二天的凌晨3点
     * @return
     */
    public static Date getMorgenTime(){
        Date currentEndDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentEndDate);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.HOUR, 3);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
}
