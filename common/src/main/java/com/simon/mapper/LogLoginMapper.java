package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.LogLogin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LogLoginMapper extends MyMapper<LogLogin> {
    List<LogLogin> getList(@Param("map") Map<String, Object> map);
}