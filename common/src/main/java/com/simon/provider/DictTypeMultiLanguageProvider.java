package com.simon.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
* 字典多语言
* @author jeesun
* @date 2019-06-03
**/

public class DictTypeMultiLanguageProvider {
    /**
     * 构造SQL查询语句
     * @param param 查询条件
     * @return SQL字符串
     */
    public String getList(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("tb1.*,tu1.username AS create_user,tu2.username AS update_user");
                FROM("t_s_dt_multi_languages tb1");
                LEFT_OUTER_JOIN("t_users tu1 ON tb1.create_by = tu1.id");
                LEFT_OUTER_JOIN("t_users tu2 ON tb1.update_by = tu2.id");
            }
        }.toString();
    }
}
