
package com.simon.service.impl;

import com.simon.common.domain.ResultMsg;
import com.simon.common.service.impl.CrudServiceImpl;
import com.simon.model.QuartzJob;
import com.simon.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SimonSun
 * @date 2018-12-21
 **/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class QuartzJobServiceImpl extends CrudServiceImpl<QuartzJob, Long> implements QuartzJobService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResultMsg<List<QuartzJob>> getAllJobs() {
        return restTemplate.getForObject("http://old-task/api/tasks", ResultMsg.class);
    }

    @Override
    public ResultMsg addJob(QuartzJob quartzJob) {
        return restTemplate.postForObject("http://old-task/api/tasks", quartzJob, ResultMsg.class);
    }

    @Override
    public ResultMsg pauseJob(String jobName, String jobGroup) {
        return restTemplate.patchForObject("http://old-task/api/tasks/pause?jobName=" + jobName + "&jobGroup=" + jobGroup, null, ResultMsg.class);
    }

    @Override
    public ResultMsg edit(QuartzJob quartzJob) {
        return restTemplate.patchForObject("http://old-task/api/tasks", quartzJob, ResultMsg.class);
    }

    @Override
    public void delete(String jobName, String jobGroup) {
        Map<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("jobName", jobName);
        requestMap.put("jobGroup", jobGroup);
        restTemplate.delete("http://old-task/api/tasks", requestMap);
    }

    @Override
    public ResultMsg resume(String jobName, String jobGroup) {
        return restTemplate.patchForObject("http://old-task/api/tasks/resume?jobName=" + jobName + "&jobGroup=" + jobGroup, null, ResultMsg.class);
    }

    @Override
    public ResultMsg<Boolean> checkExists(String jobName, String jobGroup) {
        return restTemplate.getForObject("http://old-task/api/tasks/checkExists?jobName=" + jobName + "&jobGroup=" + jobGroup, ResultMsg.class);
    }
}