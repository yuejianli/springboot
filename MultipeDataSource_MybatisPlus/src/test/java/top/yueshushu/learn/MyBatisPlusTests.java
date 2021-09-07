package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.yueshushu.learn.pojo.one.User;
import top.yueshushu.learn.pojo.two.Dept;
import top.yueshushu.learn.service.DeptService;
import top.yueshushu.learn.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:MyBatisTests
 * @Description Mybatis测试使用
 * @Author yjl
 * @Date 2021/5/18 17:50
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class MyBatisPlusTests {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Test
    public void addUserTest(){
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

        //这是修改的操作,id=2已经存在这条记录了。
        User user2=new User();
        user2.setName("岳泽霖");
        user2.setAge(25);
        user2.setSex("男性");
        user2.setDescription("一个快乐的程序员");

        //2. 放置到集合里面
        List<User> userList=new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        userService.batchAddUser(userList);
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
        Dept dept1=new Dept();
        dept1.setName("研发部");
        Dept dept2=new Dept();
        dept2.setName("测试部");
        //2. 放置到集合里面
        List<Dept> deptList=new ArrayList<>();
        deptList.add(dept);
        deptList.add(dept1);
        deptList.add(dept2);
        deptService.batchAddDept(deptList);
        log.info("添加部门成功");
    }

    @Test
    public void listDeptTest(){
        List<Dept> deptList=deptService.listDept();
        deptList.forEach(n->log.info(n));
    }
    /**
     * 数据源切换配置
     */
    @Test
    public void allDataSourceTest(){
        addUserTest();
        listDeptTest();
        addDeptTest();
        listUserTest();
    }
}
