package com.simon.common.utils;

import tk.mybatis.mapper.genid.GenId;

/**
 * uuid生成
 *
 * @author simon
 * @create 2018-08-17 13:23
 **/

public class UUIdGenId implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return UuidUtils.getUUID();
    }
}
