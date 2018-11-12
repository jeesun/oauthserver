package com.simon.service;

import com.github.pagehelper.PageInfo;
import com.simon.common.service.BasicService;
import com.simon.model.LoggingEvent;

import java.util.Map;

/**
* @author SimonSun
* @date 2018-11-09
**/
public interface LoggingEventService extends BasicService<LoggingEvent> {
    PageInfo<LoggingEvent> getList(Map<String, Object> params, Integer limit, Integer offset, String orderBy);
    void deleteAll();
}