package top.yueshushu.learn.schedule.util;

import com.alibaba.fastjson.JSON;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.util.ObjectUtils;
import top.yueshushu.learn.schedule.pojo.QuartzBean;

import java.util.*;

/**
 * 任务工具类
 *
 * @author yuejianli
 * @date 2023-01-04
 */
public class QuartzUtils {
    /**
       获取所有的定时任务
     * @throws Exception
     */
    public static List<QuartzBean> getAllJob(Scheduler scheduler){
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        List<QuartzBean> jobList = new ArrayList();
        try {
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    QuartzBean job = new QuartzBean();
                    job.setJobName(jobKey.getName());
                    job.setGroupName(jobKey.getGroup());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.setStatus(Trigger.TriggerState.NORMAL.equals(triggerState)?1:0);
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setCronExpression(cronExpression);
                    }
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    JobDataMap jobDataMap = jobDetail.getJobDataMap();
                    String[] keys = jobDataMap.getKeys();
                    if (keys != null && keys.length > 0){
                        Map<String,String> paramMap = new HashMap<>(keys.length,1.0f);
                        for (String key: keys) {
                            paramMap.put(key, jobDataMap.get(key).toString());
                        }
                        String paramStr = JSON.toJSONString(paramMap);
                        job.setJobParam(paramStr);
                    }
                    Class<? extends Job> jobClass = jobDetail.getJobClass();
                    job.setJobClass(jobClass.getName());
                    jobList.add(job);
                }
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    /**
     * 创建定时任务 定时任务创建之后默认启动状态
     * @param scheduler   调度器
     * @param quartzBean  定时任务信息类
     * @throws Exception
     */
    public static void createScheduleJob(Scheduler scheduler, QuartzBean quartzBean){
        try {
            //获取到定时任务的执行类  必须是类的绝对路径名称
            //定时任务类需要是job类的具体实现 QuartzJobBean是job的抽象类。
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(quartzBean.getJobClass());
            // 构建定时任务信息
            JobBuilder jobBuilder = JobBuilder.newJob(jobClass).withIdentity(quartzBean.getJobName(), quartzBean.getGroupName());

            // 设置参数
            Map<String,String> paramHashMap = JSON.parseObject(quartzBean.getJobParam(), HashMap.class);
            if (!ObjectUtils.isEmpty(paramHashMap)){
                paramHashMap.forEach(
                        (param,paramValue)->{
                            jobBuilder.usingJobData(param,paramValue);
                        }
                );
            }
            JobDetail jobDetail = jobBuilder
                    .storeDurably()
                    .build();
            // 设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzBean.getCronExpression());
            // 构建触发器trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(quartzBean.getJobName()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ClassNotFoundException e) {
            System.out.println("定时任务类路径出错：请输入类的绝对路径");
        } catch (SchedulerException e) {
            System.out.println("创建定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据任务名称暂停定时任务
     * @param scheduler  调度器
     * @param jobKeyName    定时任务名称
     * @throws SchedulerException
     */
    public static void pauseScheduleJob(Scheduler scheduler, String jobKeyName){
        String[] jobNameGroupArr = jobKeyName.split("\\.");
        JobKey jobKey = JobKey.jobKey(jobNameGroupArr[1],jobNameGroupArr[0]);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            System.out.println("暂停定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据任务名称恢复定时任务
     * @param scheduler  调度器
     * @param jobKeyName    定时任务名称
     * @throws SchedulerException
     */
    public static void resumeScheduleJob(Scheduler scheduler, String jobKeyName) {
        String[] jobNameGroupArr = jobKeyName.split("\\.");
        JobKey jobKey = JobKey.jobKey(jobNameGroupArr[1],jobNameGroupArr[0]);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            System.out.println("启动定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据任务名称立即运行一次定时任务
     * @param scheduler     调度器
     * @param jobKeyName       定时任务名称
     * @throws SchedulerException
     */
    public static void runOnce(Scheduler scheduler, String jobKeyName){
        String[] jobNameGroupArr = jobKeyName.split("\\.");
        JobKey jobKey = JobKey.jobKey(jobNameGroupArr[1],jobNameGroupArr[0]);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            System.out.println("运行定时任务出错："+e.getMessage());
        }
    }

    /**
     * 更新定时任务
     * @param scheduler   调度器
     * @param quartzBean  定时任务信息类
     * @throws SchedulerException
     */
    public static void updateScheduleJob(Scheduler scheduler, QuartzBean quartzBean)  {
        deleteScheduleJob(scheduler,quartzBean.getGroupName()+"."+quartzBean.getJobName());
        createScheduleJob(scheduler,quartzBean);
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     * @param scheduler 调度器
     * @param jobKeyName   定时任务名称
     * @throws SchedulerException
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobKeyName) {
        String[] jobNameGroupArr = jobKeyName.split("\\.");
        JobKey jobKey = JobKey.jobKey(jobNameGroupArr[1],jobNameGroupArr[0]);
        try {
            if (ObjectUtils.isEmpty(jobKey)){
                return ;
            }
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            System.out.println("删除定时任务出错："+e.getMessage());
        }
    }
}
