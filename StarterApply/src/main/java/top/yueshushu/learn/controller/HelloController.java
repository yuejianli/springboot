package top.yueshushu.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.log.MyLog;

/**
 * @ClassName:HelloController
 * @Description 普通的Controller 方法
 * @Author zk_yjl
 * @Date 2021/4/9 20:55
 * @Version 1.0
 **/
//1. 添加了一个RestController的注解

@RestController
public class HelloController {
    // 无参
    @GetMapping("/")
    public OutputResult toHello(){
        return OutputResult.success("无参数响应");
    }
    //相加
    @GetMapping("/add/{a}/{b}")
    public OutputResult add(@PathVariable("a") int a, @PathVariable("b") int b){
        System.out.println("进行添加");
        return OutputResult.success(a+b);
    }
    //可能会出现异常的方法
    @GetMapping("/div/{a}/{b}")
    @MyLog(module = "测试Controller",optType = "测试",description = "可能有异常的情况")
    public OutputResult div(@PathVariable("a") int a, @PathVariable("b") int b){
        return OutputResult.success(a/b);
    }
}
