package com.simon.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author simon
 * @version 1.0
 * @date 2019-09-10 17:14
 */
@Component
public class CtripScenicJob implements Job {

    private Logger logger = LoggerFactory.getLogger(CtripScenicJob.class);

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        logger.info("JobName: {}", context.getJobDetail().getKey().getName());
    }
}