package com.simon.common.config;

import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * 字典
 *
 * @author simon
 * @date 2018-06-01
 **/
public class AppConfig {
    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";

    //private static Locale locale = LocaleContextHolder.getLocale();
    //messages国际化语言指定
    private static Locale locale = LocaleContextHolder.getLocale();

    public static Locale getLocale(){
        return locale;
    }

    public final static int DEFAULT_PAGE_SIZE = 10;
    public final static int DEFAULT_PAGE_NO = 1;

    //一级菜单
    public static final Integer MENU_LEVEL_1 = 1;
    //二级菜单
    public static final Integer MENU_LEVEL_2 = 2;

    public static final String DATE_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_DAY = "yyyy-MM-dd";
    public static final String DATE_PATTERN_TIME = "HH:mm:ss";
    public static final String DATE_TIMEZONE = "GMT+8";

    /**
     * 文件上传保存路径
     */
    public static String FILE_UPLOAD_DIR;

    /**
     * 文件上传方式，支持local和qiniu
     */
    public static String FILE_UPLOAD_TYPE;
    public static String FILE_UPLOAD_TYPE_LOCAL = "local";
    public static String FILE_UPLOAD_TYPE_QINIU = "qiniu";

    public static String SMS_TEMPLATE = "【thymelte】您的验证码是:%s，在5分钟内有效。如非本人操作请忽略本短信。";

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_SU = "ROLE_SU";

    /**
     * 可选值yzxSmsServiceImpl，aliSmsServiceImpl
     */
    public static final String SMS_SERVICE_IMPL = "yzxSmsServiceImpl";

    public static String SERVER_PORT;
    static {
        Properties prop = new Properties();
        try {
            prop.load(AppConfig.class.getResourceAsStream("/application-common.properties"));
            FILE_UPLOAD_DIR = prop.getProperty("file.upload.dir");
            FILE_UPLOAD_TYPE = prop.getProperty("file.upload.type");
            SERVER_PORT = prop.getProperty("server.port");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存储在session中的验证码key
     */
    public static final String SESSION_VERI_CODE = "code";
}