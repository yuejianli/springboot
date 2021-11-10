package top.yueshushu.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName:PageController
 * @Description 页面跳转使用的Controller
 * @Author 岳建立
 * @Date 2021/11/6 10:39
 * @Version 1.0
 **/
@Controller
public class PageController {
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 跳转到计算金额页
     * @return
     */
    @RequestMapping("/tool/money")
    public String money(){
        return "money";
    }
}
