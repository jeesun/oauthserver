package com.simon.mapper;

import com.simon.common.mapper.CrudMapper;
import com.simon.model.NewsTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NewsTagMapper extends CrudMapper<NewsTag> {
    @Override
    List<NewsTag> getList(@Param("map") Map<String, Object> map);
}