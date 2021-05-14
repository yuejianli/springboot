package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import java.util.List;

/**
 * @ClassName:QueryTests
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/14 15:27
 * @Version 1.0
 **/
@SpringBootTest
@Log4j2
public class QueryTests {
    @Autowired
    private UserService userService;
    @Test
    public void nameTest(){
        String name="泽霖";
        List<User> userList=userService.findAllQueryByName(name);
        userList.forEach(n->log.info(n));
    }
}
