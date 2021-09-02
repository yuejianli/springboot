package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yueshushu.learn.pojo.Dept;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

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
public class MultipeDataSourceApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    public void addUserTest(){
        //1. 构建对象
        User user=new User();
        user.setName("欢欢");
        user.setAge(22);
        user.setSex("女");
        user.setDescription("一个非常可爱的女孩纸");
        //2. 添加方法
        userService.addUser(user);
        log.info("添加员工成功");
    }

    @Test
    public void listUserTest(){
        List<User> userList=userService.listUser();
        userList.forEach(n->log.info(n));
    }
    @Test
    public void addDeptTest(){
        //1. 构建对象
        Dept dept=new Dept();
        dept.setName("信息管理部");
        //2. 添加方法
        userService.addDept(dept);
        log.info("添加部门成功");
    }

    @Test
    public void listDeptTest(){
        List<Dept> deptList=userService.listDept();
        deptList.forEach(n->log.info(n));
    }
}

