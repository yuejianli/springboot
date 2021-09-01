package top.yueshushu.learn;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
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
public class MyBatisTests {
    @Autowired
    private UserService userService;
    @Test
    public void insertTest(){
        //1. 构建对象
        User user=new User();
        user.setName("欢欢");
        user.setAge(22);
        user.setSex("女");
        user.setDescription("一个非常可爱的女孩纸");
        //2. 添加方法
        userService.addUser(user);
        log.info("添加成功,{}",user);
    }
    @Test
    public void updateTest(){
        //1. 构建对象
        User user=new User();
        user.setId(13); //id不存在，会添加
        user.setName("欢欢");
        user.setDescription("岳泽霖最好的朋友");
        //2. 修改方法
        userService.updateUser(user);
        log.info("修改成功,{}",user);
    }
    @Test
    public void deleteTest(){

        userService.deleteUser(16);
    }
    // 批量添加，批量修改操作。
    @Test
    public void saveTest(){
        //1. 构建对象
        User user=new User();
        user.setId(2);
        user.setName("欢欢A");
        user.setDescription("岳泽霖最好的朋友");
        //2. 修改方法
        userService.saveUser(user);
        log.info("添加或者修改成功,{}",user);
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
    public void batchUpdateTest(){
        //1. 构建对象
        User user=new User();
        user.setId(2);
        user.setName("小欢欢A");
        user.setAge(22);
        user.setSex("女");
        user.setDescription("一个小坏蛋");

        User user1=new User();
        user1.setId(3);
        user1.setName("小泽霖A");
        user1.setAge(25);
        user1.setSex("男");
        user1.setDescription("一个大坏蛋");

        //
        User user2=new User();
        user2.setId(4);
        user2.setName("岳泽霖A");
        user2.setAge(25);
        user2.setSex("男性");
        user2.setDescription("一个快乐的程序员");

        //2. 放置到集合里面
        List<User> userList=new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        userService.batchUpdateUser(userList);
    }
    @Test
    public void batchDeleteTest(){
        List<Integer> ids= Arrays.asList(3,4,6);
        userService.batchDeleteByIds(ids);
    }
    @Test
    public void findByIdTest(){
        User user=userService.findById(2);
        log.info(user);
    }
    @Test
    public void findAllTest(){
        List<User> userList=userService.findAll();
        userList.forEach(n->log.info(n));
    }
    @Test
    public void findByIdsTest(){
        List<Integer> ids= Arrays.asList(2,4,6);
        List<User> userList=userService.findAllByIds(ids);
        userList.forEach(n->log.info(n));
    }
    @Test
    public void countTest(){
        Long count=userService.count();
        log.info("总数目{}",count);
    }
    @Test
    public void nameAndSexAndDescTest(){
        User user=new User();
        user.setName("小欢欢A");
        user.setSex("女");
        user.setAge(27);
        user.setDescription("小坏蛋");
        List<User> userList=userService.findByNameSexAndDesc(user);
        userList.forEach(n->log.info(n));
    }
    @Test
    public void pageTest(){
        Integer pageNumber=2;
        Integer pageSize=3;
        Page pageInfo=userService.pageUser(pageNumber,pageSize);
        log.info("总数:"+pageInfo.getTotal());
        List<User> userList=pageInfo.getRecords();
        userList.forEach(n->log.info(n));
    }

    @Test
    public void githubPageTest(){
        Integer pageNumber=2;
        Integer pageSize=3;
        PageInfo pageInfo=userService.githubPageUser(pageNumber,pageSize);
        log.info("总数:"+pageInfo.getTotal());
        List<User> userList=pageInfo.getList();
        userList.forEach(n->log.info(n));
    }
}
