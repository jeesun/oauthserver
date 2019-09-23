package com.simon.client;

import com.simon.common.domain.ResultMsg;
import com.simon.model.QuartzJob;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author simon
 * @version 1.0
 * @date 2019-09-23 10:22
 */
@FeignClient(name = "old-task",fallback = TaskClientFallBack.class)
public interface TaskClient {
    /**
     * 获取所有任务
     * @return 所有任务
     */
    @GetMapping("/api/tasks")
    ResultMsg<List<QuartzJob>> getAllJobs();
}
