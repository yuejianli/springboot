package top.yueshushu.learn.cors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.yueshushu.learn.cors.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:InfoController
 * @Description  Cors 所使用的Controller
 * @Author yjl
 * @Date 2021/6/15 10:55
 * @Version 1.0
 **/
@Controller
//@CrossOrigin(origins = "*")
public class InfoController {
    /**
     * 跳转到主页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String info(Model model){
        return "index";
    }
    //端口号后面不能跟  /
   // @CrossOrigin(origins = "http://localhost:8082")
    @GetMapping("/findById")
    @ResponseBody
    public User findById(Integer id){
        User user=new User();
        user.setId(id);
        user.setName("两个蝴蝶飞");
        user.setDescription("Get 提交 跨域问题");
        return user;
    }
    @PostMapping("/addUser")
    @ResponseBody
   // @CrossOrigin(origins = "*")
    public User addUser(@RequestBody  User user){
        //补充信息
        user.setName("岳泽霖");
        user.setDescription("POST提交 跨域问题");
        return user;
    }
}
