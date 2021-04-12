package top.yueshushu.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:InfoController
 * @Description 新创建的类，来验证自动步署
 * @Author 岳建立
 * @Date 2021/4/12 19:53
 * @Version 1.0
 **/
@RestController
public class InfoController {
    @GetMapping("/info")
    public String info(){
        return "我是新创建的一个类";
    }
}
