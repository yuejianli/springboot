package top.yueshushu.learn.freemark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.yueshushu.learn.freemark.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:InfoController
 * @Description TODO
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

    /**
     * 基本信息展示
     * @param model
     * @return
     */
    @RequestMapping("/basic")
    public String basic(Model model){
        model.addAttribute("title","学习 Thymeleaf的基本标签");
        model.addAttribute("name","岳泽霖");
        model.addAttribute("girlName","周小欢");
        model.addAttribute("id",1);
        model.addAttribute("utext","<span style='color:red;font-size:20px;'>嘿嘿,总会有人一直记得,岳泽霖和周小欢的故事</span>");
        model.addAttribute("age","26");
        model.addAttribute("inputValue","一个快乐的程序员");
        model.addAttribute("href","https://yuejl.blog.csdn.net/");

        return "basic";
    }

    /**
     * 复杂信息对象展示
     * @param model
     * @return
     */
    @RequestMapping("/complex")
    public String complex(Model model){
        model.addAttribute("title","学习 Thymeleaf的复杂标签");
        //放置 java bean 对象
        User user=new User();
        user.setName("岳泽霖");
        user.setAge(26);
        user.setDescription("一个快乐的程序员");
        model.addAttribute("user",user);
        //放置if 语句
        model.addAttribute("sex","男");
        model.addAttribute("enable","0");
        model.addAttribute("score",85);

        //放置foreach 语句
        String[] hobbies=new String[]{"看书","编程","打游戏"};

        model.addAttribute("hobbies",hobbies);
        List<User> userList=getUserList();
        model.addAttribute("userList",userList);
        return "complex";
    }

    /**
     * 复杂信息对象展示
     * @param model
     * @return
     */
    @RequestMapping("/message")
    public String message(Model model, HttpSession httpSession){
        model.addAttribute("title","学习 Thymeleaf的Message消息标签");
        //放置 java bean 对象
        User user=new User();
        user.setName("岳泽霖");
        user.setAge(26);
        user.setDescription("一个快乐的程序员");
        model.addAttribute("user",user);
        //放置session
        httpSession.setAttribute("loginUser",user);
        return "message";
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
