package top.yueshushu.learn.cors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.yueshushu.learn.cors.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:InfoController
 * @Description 整合Thymeleaf所使用的Controller
 * @Author yjl
 * @Date 2021/6/15 10:55
 * @Version 1.0
 **/
@Controller
public class InfoController {
    /**
     * 普通展示
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String info(Model model){
        return "index";
    }

}
