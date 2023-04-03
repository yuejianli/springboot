package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import java.util.List;

/**
 * @ClassName:RedisDBTests
 * @Description Redis测试使用
 * @Author yjl
 * @Date 2021/5/18 17:50
 * @Version 1.0
 **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
@Log4j2
public class RedisDBTests extends BaseTest{
    @Autowired
    private UserService userService;

    @Test
    public void findAllTest(){
        log.info(">>>>>>>>目前数据库中存在的用户信息:");
        List<User> userList=userService.findAll();
        userList.forEach(n->log.info(n));
    }
}
