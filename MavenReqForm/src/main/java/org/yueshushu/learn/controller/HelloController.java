package org.yueshushu.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yueshushu.learn.annotation.RequestJson;
import org.yueshushu.learn.pojo.User;

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

    /**
      可以同时支持 form-data, www, json 三种格式。
     但这种方式，改变了原先的处理， @ReqeuestBody , 不太好使用。
     */
    @RequestMapping("/add")
    public void add(@RequestJson User user){
       System.out.println(user);
   }
}
