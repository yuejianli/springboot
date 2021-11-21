package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:YamlApplicationTests
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/10 12:02
 * @Version 1.0
 **/
@SpringBootTest
@Log4j2
public class QueryJdbcApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    public void queryByIdTest(){
        User user=userService.queryById(2);
        log.info(user);
    }
    @Test
    public void queryAllTest(){
        List<User> userList=userService.queryForAll();
        userList.forEach(n->log.info(n));
    }
    @Test
    public void queryForMapTest(){
        Map<String,Object> userMap=userService.queryForMapById(2);
        log.info(userMap);
    }
    @Test
    public void queryForSingleTest(){
        Integer count=userService.countAll();
        log.info("查询总数目,{}",count);
    }
    @Test
    public void queryForIdsTest(){
        List<Integer> idList=userService.queryForIdList();
        log.info(idList);
    }
    @Test
    public void executeTest(){
        userService.createTable();
    }
    @Test
    public void beanTest(){
        List<User> userList=userService.queryBeanForAll();
        userList.forEach(n->log.info(n));
    }
    @Test
    public void rowSetTest(){
        List<User> userList=userService.queryRowSetForAll();
        userList.forEach(n->log.info(n));
    }
}

