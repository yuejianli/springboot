package top.yueshushu.learn.jobConfig;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.ObjectUtils;
import top.yueshushu.learn.pojo.User;
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

        System.out.println(" 执行定时任务: " + context.getJobDetail().getKey());
        // 获取参数
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        User user = new User();
        if (!ObjectUtils.isEmpty(jobDataMap)) {
            user.setName(jobDataMap.getString("name"));
            user.setAge(jobDataMap.getInt("age"));
            user.setSex(jobDataMap.getString("sex"));
            user.setDescription(jobDataMap.getString("description"));
        }
        userService.addUser(user);
    }
}
