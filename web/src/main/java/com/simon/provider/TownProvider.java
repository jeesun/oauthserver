package com.simon.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
* t_s_town
* @author SimonSun
* @date 2019-04-25
**/

public class TownProvider {
    /**
     * 构造SQL查询语句
     * @param param 查询条件
     * @return SQL字符串
     */
    public String getList(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("tb1.*,tu1.username AS create_user,tu2.username AS update_user");
                FROM("t_s_town tb1");
                LEFT_OUTER_JOIN("t_users tu1 ON tb1.create_by = tu1.id");
                LEFT_OUTER_JOIN("t_users tu2 ON tb1.update_by = tu2.id");
            }
        }.toString();
    }
}
