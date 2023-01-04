package top.yueshushu.learn.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.pojo.User;

/**
 * @ClassName:UserServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Override
    @Async
    public void addUser(User user) {
        log.info("执行添加员工的操作,{}",user);
    }
}
