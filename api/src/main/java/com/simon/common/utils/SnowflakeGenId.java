package com.simon.common.utils;

import com.simon.common.utils.snowflake.Sequence;
import tk.mybatis.mapper.genid.GenId;

/**
 * 基于Twitter的Snowflake算法
 *
 * @author simon
 * @create 2018-08-17 22:18
 **/

public class SnowflakeGenId implements GenId<Long> {
    private Sequence sequence = new Sequence(1, 1);
    @Override
    public Long genId(String table, String column) {
        return sequence.nextId();
    }
}
