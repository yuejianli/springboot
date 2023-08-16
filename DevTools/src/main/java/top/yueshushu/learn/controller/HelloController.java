package top.yueshushu.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:HelloController
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/12 19:07
 * @Version 1.0
 **/
@RestController
public class HelloController {
    //2.请求地址是 /

    @GetMapping("/")
    public String toHello(){
        return "我是两个蝴蝶飞，我突然不开心了";
    }
    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "你好,世界";
    }

}

