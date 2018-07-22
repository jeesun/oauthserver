package com.simon.mapper;

import com.simon.model.NewsInfo;

public interface NewsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsInfo record);

    int insertSelective(NewsInfo record);

    NewsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsInfo record);

    int updateByPrimaryKey(NewsInfo record);
}