package com.simon.service;

import com.simon.common.service.BasicService;
import com.simon.model.LoggingEvent;

/**
* @author SimonSun
* @date 2018-11-09
**/
public interface LoggingEventService extends BasicService<LoggingEvent, Long> {
    void deleteAll();
}