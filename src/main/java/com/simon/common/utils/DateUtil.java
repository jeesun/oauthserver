package com.simon.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author simon
 * @create 2018-06-10 11:06
 **/

public class DateUtil {
    private static final SimpleDateFormat ISO8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZ");

    public static String formatISO(Date date){
        return ISO8601_DATE_FORMAT.format(date);
    }

    public static String formatISO(long timestamp){
        return formatISO(new Date(timestamp));
    }

    public static String format(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String format(long timestamp, String pattern){
        return format(new Date(timestamp), pattern);
    }
}
