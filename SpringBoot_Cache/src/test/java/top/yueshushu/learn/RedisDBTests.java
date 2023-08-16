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
    public void insertTest(){
        //1. 构建对象
        User user=new User();
        user.setName("岳泽霖");
        user.setAge(26);
        user.setSex("男");
        user.setDescription("一个快乐的程序员");
        //2. 添加方法
        userService.addUser(user);
        log.info("添加成功,{}",user);
    }
    @Test
    public void updateTest(){
       User user=userService.findById(40);  //id随时更换
       user.setName("我换新的名字了");
       userService.updateUser(user);
       log.info("修改成功{}",user);
       findByIdTest();;
    }

    @Test
    public void deleteTest(){
        userService.deleteUser(56); //id随时更换
    }
    @Test
    public void findByIdTest(){
        User user=userService.findById(40); //id随时更换
        log.info(user);
    }
    @Test
    public void findAllTest(){
        log.info(">>>>>>>>目前数据库中存在的用户信息:");
        List<User> userList=userService.findAll();
        userList.forEach(n->log.info(n));
    }

    @Test
    public void findNameAndSexTest(){
        log.info(">>>>>>>>目前数据库中存在的用户信息:");
        List<User> userList=userService.findByNameAndSex("周小欢","女");
        userList.forEach(n->log.info(n));
    }
    @Test
    public void findNameAndSexAndIdTest(){
        log.info(">>>>>>>>目前数据库中存在的用户信息:");
        List<User> userList=userService.findByNameAndSexAndId("欢欢","女",40);
        userList.forEach(n->log.info(n));
    }
}
