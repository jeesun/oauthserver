package com.simon.common.utils;

import java.util.UUID;

/**
 * UUID生成工具
 *
 * @author simon
 * @create 2018-08-02 17:18
 **/

public class UuidUtils {
    public synchronized static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
