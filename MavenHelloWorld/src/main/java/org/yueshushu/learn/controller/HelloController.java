package org.yueshushu.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:HelloController
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/9 20:55
 * @Version 1.0
 **/
//1. 添加了一个RestController的注解

@RestController
public class HelloController {
    //2.请求地址是 /

    @GetMapping("/")
    public String toHello(){
        return "谢谢你来访问我，我是两个蝴蝶飞";
    }
    //3. 请示地址是  /info

    @GetMapping("/info")
    public String info(){
        return "我是两个蝴蝶飞，目前在杭州，我是一个快乐的程序员";
    }
}
