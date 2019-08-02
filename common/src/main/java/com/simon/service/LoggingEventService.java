package com.simon.service;

import com.simon.common.service.CrudService;
import com.simon.model.LoggingEvent;

/**
* @author SimonSun
* @date 2018-11-09
**/
public interface LoggingEventService extends CrudService<LoggingEvent, Long> {
    void deleteAll();
}