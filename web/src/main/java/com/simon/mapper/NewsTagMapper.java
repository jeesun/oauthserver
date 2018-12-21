package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.NewsTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NewsTagMapper extends MyMapper<NewsTag> {
    List<NewsTag> getList(@Param("map") Map<String, Object> map);
}