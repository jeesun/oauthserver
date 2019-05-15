package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.BasicService;
import com.simon.model.NewsInfo;

import java.util.Map;

/**
* @author SimonSun
* @date 2019-01-20
**/
public interface NewsInfoService extends BasicService<NewsInfo, Long> {
    PageInfo<NewsInfo> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy);
}