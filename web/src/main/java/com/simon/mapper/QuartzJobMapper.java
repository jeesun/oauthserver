package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.QuartzJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* QuartzJob
* @author SimonSun
* @date 2018-12-22
**/
public interface QuartzJobMapper extends MyMapper<QuartzJob> {
    /**
     * 使用Map查询
     * @param map map查询条件
     * @return 查询结果
     */
    List<QuartzJob> getList(@Param("map") Map<String, Object> map);
}