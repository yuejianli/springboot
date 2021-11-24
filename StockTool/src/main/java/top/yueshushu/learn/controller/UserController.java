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
}
