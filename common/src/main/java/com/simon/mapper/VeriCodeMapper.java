package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.VeriCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VeriCodeMapper extends MyMapper<VeriCode> {
    List<VeriCode> getList(@Param("map") Map<String, Object> map);
}