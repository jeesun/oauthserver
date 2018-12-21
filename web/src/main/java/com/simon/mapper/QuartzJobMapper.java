package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.QuartzJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuartzJobMapper extends MyMapper<QuartzJob> {
    List<QuartzJob> getList(@Param("map") Map<String, Object> map);
}