package top.yueshushu.learn.schedule.jobwork;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.ObjectUtils;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

/**
 * 任务， 是 有参数
 *
 * @author yuejianli
 * @date 2023-01-04
 */
@Slf4j
public class MyTask2 extends QuartzJobBean {

    @Autowired
    private UserService userService;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        log.info("   有参数任务 :" + context.getJobDetail().getKey());
        if (ObjectUtils.isEmpty(jobDataMap)) {
            userService.addUser(null);
            return ;
        }
        User user = new User();
        user.setName(jobDataMap.getString("name"));
        user.setAge(jobDataMap.getInt("age"));
        user.setSex(jobDataMap.getString("sex"));
        user.setDescription(jobDataMap.getString("description"));
        userService.addUser(user);
    }
}
