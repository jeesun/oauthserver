package com.simon.common.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 用户名工具
 *
 * @author simon
 * @date 2018-12-07
 **/

public class UsernameUtil {
    public static String generateByPhone(String phone){
        return ThreadLocalRandom.current().nextInt(1000000, 9999999) + "" + phone.substring(phone.length() - 4);
    }

    public static String generateByEmail(String email){
        return RandomUtil.randomCharAndNum(11).toLowerCase();
    }
}
