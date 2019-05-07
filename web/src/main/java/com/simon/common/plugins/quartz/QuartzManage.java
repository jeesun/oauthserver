package com.simon.common.plugins.quartz;

import com.simon.model.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 任务管理类
 *
 * @author simon
 * @date 2018-12-21
 **/
@Slf4j
@Component
public class QuartzManage {
    @Resource(name = "scheduler")
    private Scheduler scheduler;

    public void addJob(QuartzJob job) throws SchedulerException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //通过类名获取实体类，即要执行的定时任务的类
        Class<?> clazz = Class.forName(job.getBeanName());
        Job jobEntity = (Job) clazz.newInstance();
        //通过实体类和任务名创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(jobEntity.getClass())
                .withDescription(job.getDescription())
                .withIdentity(job.getJobName(), Scheduler.DEFAULT_GROUP)
                .build();
        //通过触发器名和cron 表达式创建 Trigger
        Trigger cronTrigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                //一旦加入scheduler，立即生效
                /*.startNow()*/
                //使用cron表达式触发器
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                .build();
        //执行定时任务
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 更新job cron表达式
     *
     * @param quartzJob
     * @throws SchedulerException
     */
    public void updateJobCron(QuartzJob quartzJob) throws SchedulerException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        deleteJob(quartzJob.getJobName());
        addJob(quartzJob);
    }

    /**
     * 删除一个job
     *
     * @param jobName
     * @throws SchedulerException
     */
    public void deleteJob(String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param jobName
     * @throws SchedulerException
     */
    public void resumeJob(String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.resumeJob(jobKey);
    }

    /**
     * 暂停一个job
     *
     * @param jobName
     * @throws SchedulerException
     */
    public void pauseJob(String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName);
        scheduler.pauseJob(jobKey);
    }

    /**
     * 判断任务是否存在
     * @param name 任务名称
     * @return 是否存在
     */
    public boolean isJobExists(String name) {
        try {
            for(String groupName : scheduler.getJobGroupNames()){
                for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))){
                    if (name.equals(jobKey.getName())){
                        return true;
                    }
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public TriggerKey getTriggerKeyByJobName(String jobName) throws SchedulerException {
        JobKey jobKey = getJobKeyByJobName(jobName);
        if (null == jobKey){
            return null;
        }
        return TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup());
    }

    public JobKey getJobKeyByJobName(String jobName) throws SchedulerException {
        for(String groupName : scheduler.getJobGroupNames()){
            for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))){
                if (jobName.equalsIgnoreCase(jobKey.getName())){
                    return jobKey;
                }
            }
        }
        return null;
    }
}
