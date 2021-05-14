package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:SpecificationTests
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/14 14:39
 * @Version 1.0
 **/
@SpringBootTest
@Log4j2
public class SpecificationTests {
    @Resource
    private UserService userService;
    @Test
    public void nameAndSexAndDescTest(){
        User user=new User();
       // user.setName("小");
        user.setSex("女");
        user.setAge(26);
        user.setDescription("岳泽霖");
        List<User> userList=userService.findByNameSexAndDesc(user);
        userList.forEach(n->log.info(n));
    }
}
