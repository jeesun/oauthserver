package com.simon.task;

import com.simon.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 任务示例
 *
 * @author simon
 * @date 2018-12-21
 **/
@Slf4j
public class SampleTask implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
