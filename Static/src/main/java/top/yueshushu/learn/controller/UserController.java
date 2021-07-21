package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import java.util.List;

/**
 * @ClassName:UserController
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/6/24 16:09
 * @Version 1.0
 * @Since 1.0
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.findById(id);
    }
    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }
}
