package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:JpaRepositoryTests
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/26 20:12
 * @Version 1.0
 **/
@SpringBootTest
@Log4j2
public class JpaRepositoryTests {
    @Autowired
    private UserService userService;
    @Test
    public void findAllTest(){
        List<User> userList=userService.jpaFindAll();
        userList.forEach(n->log.info(n));
    }
    @Test
    public void findByExampleTest(){
        User user=new User();
        user.setName("泽霖");
        user.setAge(25);
        user.setSex("男");
        //1.创建匹配器
        ExampleMatcher exampleMatcher=ExampleMatcher.matching()
                .withMatcher("sex",matcher -> matcher.contains())
                .withMatcher("age",matcher -> matcher.exact())
                .withMatcher("name",matcher ->matcher.contains());
        //2. 生成Example 对象
        Example example=Example.of(user,exampleMatcher);
        //3. 进行查询
        List<User>userList=userService.findByExample(example);
        userList.forEach(n->log.info(n));
    }

    @Test
    public void findByNameTest(){
       List<User> userList=userService.findByName("小欢欢");
        userList.forEach(n->log.info(n));
    }
    @Test
    public void findBySexAndAgeTest(){
        List<User> userList=userService.findBySexAndAge("男",25);
        userList.forEach(n->log.info(n));
    }
    @Test
    public void findAllOrderByTest(){
        List<User> userList=userService.findBySexOrderByAge("女");
        userList.forEach(n->log.info(n));
    }

    @Test
    public void findQueryNameTest(){
        List<Map<String,Object>> userMapList=userService.findQueryByName("小欢欢");
        for(Map<String,Object> map:userMapList){
            log.info("id是:{},name是{}",map.get("id"),map.get("name"));
        }
    }
    @Test
    public void findAllSqlTest(){
        List<User> userList=userService.jpaFindAllSql("小欢欢");
        userList.forEach(n->log.info(n));
    }
}
