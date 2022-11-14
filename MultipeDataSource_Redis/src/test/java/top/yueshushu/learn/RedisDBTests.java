package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class RedisDBTests {
    @Autowired
    private UserService userService;

    @Test
    public void insertTest() {
        //1. 构建对象
        User user = new User();
        user.setName("欢欢");
        user.setAge(22);
        user.setSex("女");
        user.setDescription("一个非常可爱的女孩纸");
        //2. 添加方法
        userService.addUser(user);
        log.info("添加成功,{}", user);
        findAllTest();
    }

    @Test
    public void updateTest() {
        User user = userService.findById(43);  //id随时更换
        user.setName("我换新的名字了");
        userService.updateUser(user);
        log.info("修改成功{}", user);
        findAllTest();
    }

    @Test
    public void deleteTest() {
        userService.deleteUser(43); //id随时更换
        log.info("删除成功了");
        findAllTest();

    }

    @Test
    public void findByIdTest() {
        User user = userService.findById(43); //id随时更换
        log.info(user);
    }

    @Test
    public void findAllTest() {
        log.info(">>>>>>>>目前数据库中存在的用户信息:");
        List<User> userList = userService.findAll();
        userList.forEach(n -> log.info(n));
    }
}
