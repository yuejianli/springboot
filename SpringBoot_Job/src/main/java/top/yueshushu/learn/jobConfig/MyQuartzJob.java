package top.yueshushu.learn.jobConfig;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import top.yueshushu.learn.service.UserService;

/**
 * @ClassName:MyQuartzJob
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/12/1 18:41
 * @Version 1.0
 * @Since 1.0
 **/
//继承 抽象类  QuartzJobBean
public class MyQuartzJob extends QuartzJobBean {
   @Autowired
   private UserService userService;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//        userService.addUser(null);
//        System.out.println("    Hi! :" + context.getJobDetail().getKey());
    }
}
