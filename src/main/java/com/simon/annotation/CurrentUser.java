package com.simon.annotation;

import java.lang.annotation.*;

/**
 * 当前登录用户
 *
 * @author simon
 * @create 2018-07-22 10:21
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
