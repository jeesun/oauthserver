package com.simon.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 侧边栏菜单权限
 *
 * @author simon
 * @date 2019-01-14
 **/

public class SideMenuAuthorityProvider {
    /**
     * 构造SQL查询语句
     * @param param 查询条件
     * @return SQL字符串
     */
    public String getList(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("*");
                FROM("t_side_menu_authority");
                if (null != param.get("sideMenuId")) {
                    WHERE("side_menu_id=#{sideMenuId}");
                }
            }
        }.toString();
    }
}
