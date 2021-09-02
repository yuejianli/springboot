package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import java.util.List;

/**
 * @ClassName:UserController
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/2 12:57
 * @Version 1.0
 * @Since 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/addUser")
    public String addUser(){
        //1. 构建对象
        User user=new User();
        user.setName("欢欢");
        user.setAge(22);
        user.setSex("女");
        user.setDescription("一个非常可爱的女孩纸");
        //2. 添加方法
        userService.addUser(user);
        return "添加员工成功";
    }
    @RequestMapping("/listUser")
    public List<User> listUser(){
        List<User> userList=userService.listUser();
        return userList;
    }
}
