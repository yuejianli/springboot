package top.yueshushu.learn.jobConfig;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 动态 Cron 配置
 *
 * @author yuejianli
 * @date 2023-01-03
 */
@Component
@Log4j2
public class MyDynamicCronJob implements SchedulingConfigurer {
    public static String helloCron = "3/2 * * * * *";
    public void sayHello(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String now=simpleDateFormat.format(new Date());
        System.out.println(">>>你好，当前时间是:"+now);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                () -> {
                    // 调用方法
                    sayHello();
                },
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        // 配置下一次执行时间
                        CronTrigger cronTrigger = new CronTrigger(helloCron);
                        return cronTrigger.nextExecutionTime(triggerContext);
                    }
                }
        );
    }
}
