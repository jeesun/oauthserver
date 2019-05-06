package com.simon.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author SimonSun
 * @date 2019-04-24
 **/
public class CityProvider {

    public String getList(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("*");
                FROM("t_s_city");
            }
        }.toString();
    }
}
