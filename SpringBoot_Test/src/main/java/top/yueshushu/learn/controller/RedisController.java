package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName:RedisController
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/9 13:55
 * @Version 1.0
 * @Since 1.0
 **/
@RestController
public class RedisController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<User> getAll() {
         return userService.findAll();
    }
}
