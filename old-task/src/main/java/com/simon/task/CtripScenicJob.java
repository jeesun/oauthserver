package com.simon.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author simon
 * @version 1.0
 * @date 2019-09-10 17:14
 */
@Slf4j
@Component
public class CtripScenicJob implements Job {
    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        log.info("JobName: {}", context.getJobDetail().getKey().getName());
    }
}