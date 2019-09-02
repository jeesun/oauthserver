package com.simon.common.plugins.quartz;

import com.simon.model.QuartzJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

/**
 * @author simon
 * @date 2019-05-08
 */
public class QuartzManager {
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    /**
     * @Description: 添加一个定时任务
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void addJob(QuartzJob job, String triggerGroupName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(job.getBeanName());
        Job jobEntity = (Job) clazz.newInstance();
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(jobEntity.getClass()).withIdentity(job.getJobName(), Scheduler.DEFAULT_GROUP).build();

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(job.getTriggerName(), triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            sched.scheduleJob(jobDetail, trigger);

            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 修改一个任务的触发时间
     */
    public static void modifyJobTime(QuartzJob job, String triggerGroupName) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerName(), triggerGroupName);
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(job.getCronExpression())) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(job.getTriggerName(), triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                sched.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                //Class<? extends Job> jobClass = jobDetail.getJobClass();
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 移除一个任务
     *
     */
    public static void removeJob(QuartzJob job, String triggerGroupName) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();

            TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerName(), triggerGroupName);

            sched.pauseTrigger(triggerKey);// 停止触发器
            sched.unscheduleJob(triggerKey);// 移除触发器
            sched.deleteJob(JobKey.jobKey(job.getJobName(), Scheduler.DEFAULT_GROUP));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:启动所有定时任务
     */
    public static void startJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:关闭所有定时任务
     */
    public static void shutdownJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void resumeJob(QuartzJob quartzJob){
        try{
            Scheduler sched = schedulerFactory.getScheduler();
            JobKey jobKey = JobKey.jobKey(quartzJob.getJobName(), Scheduler.DEFAULT_GROUP);
            sched.resumeJob(jobKey);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void pauseJob(QuartzJob quartzJob){
        try{
            Scheduler sched = schedulerFactory.getScheduler();
            JobKey jobKey = JobKey.jobKey(quartzJob.getJobName(), Scheduler.DEFAULT_GROUP);
            sched.pauseJob(jobKey);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static boolean isJobExists(QuartzJob quartzJob) {
        try{
            Scheduler scheduler = schedulerFactory.getScheduler();
            for(String groupName : scheduler.getJobGroupNames()){
                for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))){
                    if (quartzJob.getJobName().equals(jobKey.getName())){
                        return true;
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return false;
    }
}
