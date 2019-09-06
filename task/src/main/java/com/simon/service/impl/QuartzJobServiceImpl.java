
package com.simon.service.impl;

import com.simon.common.plugins.quartz.QuartzManager;
import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.mapper.QuartzJobMapper;
import com.simon.model.QuartzJob;
import com.simon.repository.QuartzJobRepository;
import com.simon.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author SimonSun
 * @date 2018-12-21
 **/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class QuartzJobServiceImpl extends CrudServiceImpl<QuartzJob, Long> implements QuartzJobService {
    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzJobRepository quartzJobRepository;

    private String TRIGGER_GROUP_NAME = "XLXXCC_JOB_GROUP";

    @Override
    public void runJobsOnStart() throws ClassNotFoundException, InstantiationException, SchedulerException, IllegalAccessException {
        List<QuartzJob> quartzJobs = quartzJobMapper.selectAll();
        for (QuartzJob quartzJob : quartzJobs) {
            QuartzManager.addJob(quartzJob, TRIGGER_GROUP_NAME);
        }
        quartzJobMapper.updateJobStatus(1);
    }
}