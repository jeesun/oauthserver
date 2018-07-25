package com.simon.mapper;

import com.simon.model.NewsTag;

public interface NewsTagMapper {
    NewsTag selectByPrimaryKey(Long id);
}