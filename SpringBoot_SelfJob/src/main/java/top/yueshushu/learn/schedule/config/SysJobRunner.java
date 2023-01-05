package top.yueshushu.learn.schedule.config;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.yueshushu.learn.schedule.pojo.ScheduleSetting;
import top.yueshushu.learn.schedule.service.ScheduleSettingService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务启动时处理任务
 *
 * @author yuejianli
 * @date 2023-01-05
 */
@Slf4j
@Component
public class SysJobRunner implements CommandLineRunner {

    @Resource
    private CronTaskRegistrar cronTaskRegistrar;

    @Resource
    private ScheduleSettingService scheduleSettingService;
    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为正常的定时任务
        List<ScheduleSetting> jobList = scheduleSettingService.findAllRunJob();
        if (CollectionUtils.isNotEmpty(jobList)) {
            for (ScheduleSetting job : jobList) {
                SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                cronTaskRegistrar.addCronTask(task, job.getCronExpression());
            }
            log.info("定时任务已加载完毕...");
        }
    }
}