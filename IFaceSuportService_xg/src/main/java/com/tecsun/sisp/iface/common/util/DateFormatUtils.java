package com.tecsun.sisp.iface.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhongzhiyong on 15-12-29.
 */
public class DateFormatUtils {
    public static final String DATE_TIME_FORMART = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMART = "yyyy-MM-dd";

    private static ThreadLocal<SimpleDateFormat> dateTimeThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_TIME_FORMART);
        }
    };

    private static ThreadLocal<SimpleDateFormat> dateThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_FORMART);
        }
    };


    public static DateFormat getDefaultTimeFormat() {
        return dateTimeThreadLocal.get();
    }

    public static DateFormat getDefaultDateFormat() {
        return dateThreadLocal.get();
    }


    public static String format(DateFormat formatter, Date date, String defaultValue) {
        if (date == null) {
            return defaultValue;
        }
        else {
            return formatter.format(date);
        }
    }

    public static String format(DateFormat formatter, Date date) {
        return formatter.format(date);
    }

    public static String format(Date date) {
        return getDefaultTimeFormat().format(date);
    }

    public static Date parse(String dateStr) throws Exception {
        return getDefaultTimeFormat().parse(dateStr);
    }

    public static Date parse(DateFormat formatter, String dateStr) throws Exception {
        return formatter.parse(dateStr);
    }
}
