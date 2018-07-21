package com.simon.config;

import java.util.Locale;

/**
 * 字典
 *
 * @author simon
 * @create 2018-06-01 0:33
 **/

public class AppConfig {
    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";

    //private static Locale locale = LocaleContextHolder.getLocale();
    private static Locale locale = Locale.ENGLISH;

    public static Locale getLocale(){
        return locale;
    }
}
