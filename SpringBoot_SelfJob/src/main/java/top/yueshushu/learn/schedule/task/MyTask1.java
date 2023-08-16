package top.yueshushu.learn.schedule.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.yueshushu.learn.service.UserService;

import javax.annotation.Resource;

/**
 * 任务， 是 空参数
 *
 * @author yuejianli
 * @date 2023-01-04
 */
@Slf4j
@Component("myTask1")
public class MyTask1  implements BaseTask{

    @Resource
    private UserService userService;

    @Override
    public void execute(String param)  {
            log.info("   空参数任务 :" );
            userService.addUser(null);
    }
}
