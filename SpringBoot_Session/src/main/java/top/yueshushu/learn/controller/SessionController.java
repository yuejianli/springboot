package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName:RedisController
 * @Description 创建和获取Session的信息
 * @Author zk_yjl
 * @Date 2021/9/9 13:55
 * @Version 1.0
 * @Since 1.0
 **/
@RestController
public class SessionController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/createSession")
    public String createSession(HttpSession httpSession){
        String sessionId=httpSession.getId();
        httpSession.setAttribute("name",port+",两个蝴蝶飞"+sessionId);
        httpSession.setAttribute("sname",port+":abc");
        return sessionId+"创建端口号是:"+port+"的应用创建Session,属性是:"+httpSession.getAttribute("name").toString();
    }

    @RequestMapping("/getSession")
    public String getSession(HttpSession httpSession){
        return "访问端口号是:"+port+",获取Session属性是:"+httpSession.getAttribute("name").toString();
    }
}
