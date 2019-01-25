package com.simon.common.plugins.quartz;

import com.simon.model.QuartzJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 任务管理类
 *
 * @author simon
 * @date 2018-12-21
 **/

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
                .withIdentity(job.getJobName())
                .build();
        //通过触发器名和cron 表达式创建 Trigger
        Trigger cronTrigger = newTrigger()
                .withIdentity(job.getTriggerName())
                //一旦加入scheduler，立即生效
                .startNow()
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
    public void updateJobCron(QuartzJob quartzJob) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(quartzJob.getJobName());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 删除一个job
     *
     * @param quartzJob
     * @throws SchedulerException
     */
    public void deleteJob(QuartzJob quartzJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName());
        scheduler.deleteJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param quartzJob
     * @throws SchedulerException
     */
    public void resumeJob(QuartzJob quartzJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 立即执行job
     *
     * @param quartzJob
     * @throws SchedulerException
     */
    public void runAJobNow(QuartzJob quartzJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 暂停一个job
     *
     * @param quartzJob
     * @throws SchedulerException
     */
    public void pauseJob(QuartzJob quartzJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 判断任务是否正在运行
     * @param name 任务名称
     * @return 是否正在运行
     */
    public boolean isRun(String name) {
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

    /**
     * 判断任务是否正在运行
     * @param quartzJob 任务
     * @return 是否正在运行
     */
    public boolean isRun(QuartzJob quartzJob){
        return isRun(quartzJob.getJobName());
    }
}
