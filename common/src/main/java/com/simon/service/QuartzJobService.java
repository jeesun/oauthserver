package com.simon.service;

import com.simon.common.domain.ResultMsg;
import com.simon.common.service.CrudService;
import com.simon.model.QuartzJob;

import java.util.List;

/**
* @author SimonSun
* @date 2018-12-21
**/
public interface QuartzJobService extends CrudService<QuartzJob, Long> {
    ResultMsg<List<QuartzJob>> getAllJobs();

    ResultMsg addJob(QuartzJob quartzJob);

    ResultMsg pauseJob(String jobName, String jobGroup);

    ResultMsg edit(QuartzJob quartzJob);

    void delete(String jobName, String jobGroup);

    ResultMsg resume(String jobName, String jobGroup);

    ResultMsg<Boolean> checkExists(String jobName, String jobGroup);
}