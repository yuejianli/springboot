package top.yueshushu.learn.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.annotation.AuthToken;
import top.yueshushu.learn.model.User;
import top.yueshushu.learn.util.ThreadLocalUtils;

import java.util.concurrent.TimeUnit;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "你好啊,岳泽霖";
    }

    @GetMapping("/hello2")
    @AuthToken
    public String hello2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程内获取
        User user = ThreadLocalUtils.getUser();
        return "你好啊,岳泽霖";
    }
}
