package com.simon.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
* 属性UI
* @author SimonSun
* @date 2019-05-03
**/

public class ColumnUiProvider {
    /**
     * 构造SQL查询语句
     * @param param 查询条件
     * @return SQL字符串
     */
    public String getList(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("tb1.*");
                FROM("t_s_column_ui tb1");
            }
        }.toString();
    }
}
