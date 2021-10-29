package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.service.HelloService;
import top.yueshushu.log.MyLog;

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
    @Autowired
    private HelloService helloService;
    @GetMapping("/")
    public OutputResult toHello(){
        return OutputResult.success("无参数响应");
    }
    //相加
    @GetMapping("/add/{a}/{b}")
    @MyLog
    public OutputResult add(@PathVariable("a") int a, @PathVariable("b") int b){
        System.out.println("进行添加");
        return OutputResult.success(helloService.add(a,b));
    }

    @GetMapping("/div/{a}/{b}")
    @MyLog
    public OutputResult div(@PathVariable("a") int a, @PathVariable("b") int b){
        return OutputResult.success(a/b);
    }
}
