package top.yueshushu.starter.service;
import java.util.logging.Logger;

/**
 * @ClassName:UserService
 * @Description 用户提示的服务，表示有这一个类。
 * @Author zk_yjl
 * @Date 2021/10/22 17:02
 * @Version 1.0
 * @Since 1.0
 **/
public class UserPropertiesService {
   //定义日志
    Logger logger=Logger.getLogger(UserPropertiesService.class.getSimpleName());
    //参数信息，自定义starter 提供的全部的参数信息。
    private String name;
    private Integer age;
    private String description;
    public UserPropertiesService() {

    }
    // 接收参数
    public UserPropertiesService(String name,Integer age,String description) {
         this.name=name;
         this.age=age;
         this.description=description;
    }
    /**
     * 要实现的功能点， 这儿只做一个简单的打印。
     * @date 2021/10/29 14:30
     * @author zk_yjl
     * @param
     * @return void
     */
    public String println(){
        String message = String.format("大家好，我叫: %s, 今年 %s岁, 个人描述: %s",
                name, age,description);
        logger.info(">>>用户的信息:"+message);
        return message;
    }
}
