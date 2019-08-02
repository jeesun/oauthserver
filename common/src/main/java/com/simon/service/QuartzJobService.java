package com.simon.service;

import com.simon.common.service.CrudService;
import com.simon.model.QuartzJob;
import org.quartz.SchedulerException;

/**
* @author SimonSun
* @date 2018-12-21
**/
public interface QuartzJobService extends CrudService<QuartzJob, Long> {
    void runJobsOnStart() throws ClassNotFoundException, InstantiationException, SchedulerException, IllegalAccessException;
}