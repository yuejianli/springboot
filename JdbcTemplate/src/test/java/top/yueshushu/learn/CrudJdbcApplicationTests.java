package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:YamlApplicationTests
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/10 12:02
 * @Version 1.0
 **/
@SpringBootTest
@Log4j2
public class CrudJdbcApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    public void addTest(){
        //1. 构建对象
        User user=new User();
        user.setName("欢欢");
        user.setAge(22);
        user.setSex("女");
        user.setDescription("一个非常可爱的女孩纸");
        //2. 添加方法
        userService.addUser(user);

    }
    @Test
    public void updateTest(){
        //1. 构建对象
        User user=new User();
        user.setId(2);
        user.setName("欢欢");
        user.setDescription("岳泽霖最好的朋友");
        //2. 修改方法
        userService.updateUser(user);
    }
    @Test
    public void deleteTest(){
        userService.deleteUser(3);
    }

    @Test
    public void batchAddTest(){
        //1. 构建对象
        User user=new User();
        user.setName("小欢欢");
        user.setAge(22);
        user.setSex("女");
        user.setDescription("一个小坏蛋");

        User user1=new User();
        user1.setName("小泽霖");
        user1.setAge(25);
        user1.setSex("男");
        user1.setDescription("一个大坏蛋");
        //2. 放置到集合里面
        List<User> userList=new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userService.batchAddUser(userList);
    }
}

