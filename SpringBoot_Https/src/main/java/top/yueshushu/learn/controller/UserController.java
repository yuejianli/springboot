package top.yueshushu.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName:UserController
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/6/24 16:09
 * @Version 1.0
 * @Since 1.0
 **/
@Controller
public class UserController {
    //跳转到页面
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/hello")
    @ResponseBody
    //实现接口
    public String hello(){
        return "你好啊，我是岳泽霖";
    }
}
