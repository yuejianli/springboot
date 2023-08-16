package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.response.OutputResult;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/addUser")
    private OutputResult addUser(@RequestBody User user){
         userService.addUser(user);
         return OutputResult.success();
    }
    @PostMapping("/updateUser")
    private OutputResult udpateUser(@RequestBody User user){
        userService.updateUser(user);
        return OutputResult.success();
    }
    @PostMapping("/deleteUser")
    private OutputResult deleteUser(@RequestBody User user){
        userService.deleteUser(user.getId());
        return OutputResult.success();
    }
    @GetMapping("/getUserById")
    public OutputResult getUserById(@RequestBody User user){
        User result= userService.findById(user.getId());
        return OutputResult.success(result);
    }
    @GetMapping("/findAll")
    public OutputResult findAll(){
        List<User> userList= userService.findAll();
        return OutputResult.success(userList);
    }
}
