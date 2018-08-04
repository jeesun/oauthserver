package com.simon.common.config;

import org.springframework.context.i18n.LocaleContextHolder;

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
    private static Locale locale = LocaleContextHolder.getLocale();//messages国际化语言指定

    public static Locale getLocale(){
        return locale;
    }

    public final static int DEFAULT_PAGE_SIZE = 10;
    public final static int DEFAULT_PAGE_NO = 1;
}
