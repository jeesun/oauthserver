package com.simon.common.annotation;

import java.lang.annotation.*;

/**
 * 忽略登录检查
 *
 * @author simon
 * @create 2018-07-22 10:23
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreSecurity {
}
