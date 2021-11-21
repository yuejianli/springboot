package top.yueshushu.starter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.yueshushu.starter.mode.UserProperties;
import top.yueshushu.starter.service.UserPropertiesService;

/**
 * @ClassName:UserServiceConfiguration
 * @Description 用户Service类的配置信息
 * @Author zk_yjl
 * @Date 2021/10/22 17:12
 * @Version 1.0
 * @Since 1.0
 **/
//表示是一个配置类
@Configuration
//用户输入的参数信息，可以放置到 UserProperties参数里面。一一对应起来
@EnableConfigurationProperties(UserProperties.class)
//存在某个条件类时触发, 即寻找到 UserPropertiesService.class时，才生效
@ConditionalOnClass(UserPropertiesService.class)
public class UserPropertiesServiceConfiguration {

    @Autowired
    private UserProperties userProperties;

    @Bean
    public UserPropertiesService userPropertiesService(){
        UserPropertiesService userService=new UserPropertiesService(
                userProperties.getName(),
                userProperties.getAge(),
                userProperties.getDescription()
        );
        return userService;
    }
}
