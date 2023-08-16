package top.yueshushu.init.postconstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class MyUserServiceUtil2 {

    //执行的顺序，先 Component, 再Autowired,后 PostConstruct
    //内部注入
    @Autowired
    private UserService userService;

    //定义代理的静态属性
   private static UserService userServiceImpl;

   @PostConstruct
   public void setComponent(){
       //赋予属性值
       userServiceImpl=userService;
   }
   //方法是静态的，那么 调用者必须是静态的.
    public static void handler(){
        //先设置值
        userServiceImpl.set();
        //再获取值
        userServiceImpl.get();
    }
}
