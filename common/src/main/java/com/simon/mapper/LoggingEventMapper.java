package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.LoggingEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LoggingEventMapper extends MyMapper<LoggingEvent> {
    List<LoggingEvent> getList(@Param("map") Map<String, Object> map);
}