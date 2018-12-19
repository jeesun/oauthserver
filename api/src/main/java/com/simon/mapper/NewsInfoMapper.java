package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.NewsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NewsInfoMapper extends MyMapper<NewsInfo> {
    List<NewsInfo> getList(@Param("map") Map<String, Object> map);
}