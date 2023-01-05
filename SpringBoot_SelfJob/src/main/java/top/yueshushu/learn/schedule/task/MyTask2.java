package top.yueshushu.learn.schedule.task;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 任务， 是 有参数
 *
 * @author yuejianli
 * @date 2023-01-04
 */
@Slf4j
@Component("myTask2")
public class MyTask2 implements BaseTask{

    @Resource
    private UserService userService;

    @Override
    public void execute(String param) {
        log.info("   有参数任务 :" + param);
        if (ObjectUtils.isEmpty(param)) {
            userService.addUser(null);
            return ;
        }
        Map<String,String> jobDataMap = JSON.parseObject(param,Map.class);
        User user = new User();
        user.setName(jobDataMap.get("name"));
        user.setAge(Integer.parseInt(jobDataMap.get("age")));
        user.setSex(jobDataMap.get("sex"));
        user.setDescription(jobDataMap.get("description"));
        userService.addUser(user);
    }
}
