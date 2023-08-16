package top.yueshushu.learn.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.yueshushu.learn.jobConfig.MyQuartzJob;

/**
 * @ClassName:QuartzConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/12/1 18:44
 * @Version 1.0
 * @Since 1.0
 **/
// @Configuration
public class QuartzConfig {
    @Bean
    public JobDetail myJobDetail(){
        JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class)
                .withIdentity("myJob1","myJobGroup1")
                //JobDataMap可以给任务execute传递参数
                .usingJobData("name","两个蝴蝶飞")
                .usingJobData("age",26)
                .storeDurably()
                .build();
        return jobDetail;
    }
    @Bean
    public Trigger myTrigger(){
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myTrigger1","myTriggerGroup1")
                .usingJobData("job_trigger_param","job_trigger_param1")
                .startNow()
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        return trigger;
    }
}
