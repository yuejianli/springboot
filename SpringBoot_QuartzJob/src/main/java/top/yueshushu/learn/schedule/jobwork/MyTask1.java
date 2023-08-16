package top.yueshushu.learn.schedule.jobwork;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import top.yueshushu.learn.service.UserService;

/**
 * 任务， 是 空参数
 *
 * @author yuejianli
 * @date 2023-01-04
 */
@Slf4j
public class MyTask1 extends QuartzJobBean {

    @Autowired
    private UserService userService;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
            userService.addUser(null);
            log.info("   空参数任务 :" + context.getJobDetail().getKey());
    }
}
