package com.simon.task;

import com.simon.common.plugins.quartz.QuartzManager;
import com.simon.mapper.QuartzJobMapper;
import com.simon.model.QuartzJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @author simon
 * @version 1.0
 * @date 2019-09-02
 */
@SpringBootApplication
@MapperScan(value = "com.simon.mapper")
public class TaskApplication implements CommandLineRunner {
    @Autowired
    private QuartzJobMapper quartzJobMapper;

    private String TRIGGER_GROUP_NAME = "XLXXCC_JOB_GROUP";

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<QuartzJob> quartzJobs = quartzJobMapper.selectAll();
        for (QuartzJob quartzJob : quartzJobs) {
            QuartzManager.addJob(quartzJob, TRIGGER_GROUP_NAME);
        }
        quartzJobMapper.updateJobStatus(1);
    }
}
