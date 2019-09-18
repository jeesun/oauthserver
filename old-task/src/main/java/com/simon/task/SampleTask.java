package com.simon.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

/**
 * 任务示例
 *
 * @author simon
 * @date 2018-12-21
 **/
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SampleTask implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("JobName: {}", jobExecutionContext.getJobDetail().getKey().getName());
    }
}
