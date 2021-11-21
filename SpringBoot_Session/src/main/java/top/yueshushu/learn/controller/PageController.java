package top.yueshushu.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @ClassName:PageController
 * @Description 页面跳转使用
 * @Author zk_yjl
 * @Date 2021/9/30 10:03
 * @Version 1.0
 * @Since 1.0
 **/
@Controller
public class PageController {
    @RequestMapping("/toLogin")
    //跳转到登录页面
    public String toLogin(){
        return "login";
    }


    @RequestMapping("/toMain")
    //跳转到登录页面
    public String toMain(){
        return "main";
    }
    @RequestMapping("/add")
    //跳转到登录页面
    public String add(){
        return "add";
    }


    @RequestMapping("/update")
    //跳转到登录页面
    public String update(){
        return "update";
    }
    @RequestMapping("/select")
    //跳转到登录页面
    public String select(){
        return "select";
    }

    @RequestMapping("/delete")
    //跳转到登录页面
    public String delete(){
        return "delete";
    }
}
