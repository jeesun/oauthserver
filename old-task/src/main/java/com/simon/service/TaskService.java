package com.simon.service;

import com.simon.model.QuartzJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @author simon
 * @version 1.0
 * @date 2019-09-10 17:07
 */
public interface TaskService {
    List<QuartzJob> list();

    void addJob(QuartzJob quartzJob);

    void edit(QuartzJob quartzJob);

    void delete(String jobName, String jobGroup);

    void pause(String jobName, String jobGroup);

    void resume(String jobName, String jobGroup);

    boolean checkExists(String jobName, String jobGroup)throws SchedulerException;
}
