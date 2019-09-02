package com.simon.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        log.info(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
