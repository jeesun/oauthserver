package com.simon.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
* 侧边栏菜单多语言
* @author jeesun
* @date 2019-05-30
**/

public class SideMenuMultiLanguageProvider {
    /**
     * 构造SQL查询语句
     * @param param 查询条件
     * @return SQL字符串
     */
    public String getList(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("tb1.*,tu1.username AS create_user,tu2.username AS update_user");
                FROM("t_s_side_menu_multi_languages tb1");
                LEFT_OUTER_JOIN("t_users tu1 ON tb1.create_by = tu1.id");
                LEFT_OUTER_JOIN("t_users tu2 ON tb1.update_by = tu2.id");
            }
        }.toString();
    }
}
