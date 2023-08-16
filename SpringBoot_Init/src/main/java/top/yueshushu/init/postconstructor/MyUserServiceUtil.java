package top.yueshushu.init.postconstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @ClassName:MyUserServiceUtil
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/30 17:01
 * @Version 1.0
 * @Since 1.0
 **/
@Component
public class MyUserServiceUtil {
   @Autowired
   private UserService userService;

    public   void handler(){
        //先设置值
        userService.set();
        //再获取值
        userService.get();
    }
}
