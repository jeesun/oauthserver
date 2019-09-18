package com.simon.repository;

import com.simon.model.QuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author SimonSun
* @date 2018-12-21
**/
@Repository
public interface QuartzJobRepository extends JpaRepository<QuartzJob, Long> {
    /**
     * 根据任务名和任务组查询定时任务
     * @param jobName 任务名
     * @param jobGroup 任务组
     * @return 定时任务
     */
    QuartzJob findByJobNameAndJobGroup(String jobName, String jobGroup);
}
