package top.yueshushu.learn.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.mapper.UserMapper;
import top.yueshushu.learn.pojo.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){

        }
        log.info("执行添加员工的操作");
    }
}
