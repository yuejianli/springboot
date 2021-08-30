package top.yueshushu.learn.freemark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.yueshushu.learn.freemark.pojo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:InfoController
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/15 10:55
 * @Version 1.0
 **/
@Controller
public class InfoController {
    @RequestMapping("/index")
    public String info(Model model){
        model.addAttribute("web","FreeMarker展示信息");
        model.addAttribute("user","两个蝴蝶飞");
        Map<String,Object> info=new HashMap<>();
        info.put("url","www.yueshushu.top");
        info.put("name","周小欢");
        model.addAttribute("info",info);
        model.addAttribute("userList",getUserList());
        return "index";
    }
    private List<User> getUserList() {
        List<User> userList=new ArrayList<>();
        for(int i=1;i<=10;i++){
            User user=new User();
            user.setId(i);
            user.setName("蝴蝶"+i);
            user.setAge(i*3+1);
            user.setSex(i%2);
            user.setDescription("一个简单的描述");
            userList.add(user);
        }
        return userList;
    }
}