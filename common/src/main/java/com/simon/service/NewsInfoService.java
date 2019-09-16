package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.CrudService;
import com.simon.model.NewsInfo;

import java.util.Map;

/**
* @author SimonSun
* @date 2019-01-20
**/
public interface NewsInfoService extends CrudService<NewsInfo, Long> {
    @Override
    PageInfo<NewsInfo> getList(Map<String, Object> params, Integer pageNo, Integer pageSize, String orderBy);
}