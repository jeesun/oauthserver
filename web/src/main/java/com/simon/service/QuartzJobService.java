package com.simon.service;
import com.simon.model.QuartzJob;
import com.simon.common.service.BasicService;
import org.quartz.SchedulerException;

/**
* @author SimonSun
* @date 2018-12-21
**/
public interface QuartzJobService extends BasicService<QuartzJob, Long> {
    void runJobsOnStart() throws ClassNotFoundException, InstantiationException, SchedulerException, IllegalAccessException;
}