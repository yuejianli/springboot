package top.yueshushu.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName:LogConfiguration
 * @Description 存在注解 Log 时才启用
 * @Author zk_yjl
 * @Date 2021/10/25 10:34
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
@EnableConfigurationProperties(MyLogProperties.class)
@ConditionalOnBean(LogMarkerConfiguration.class)
public class LogConfiguration {

    @Bean
    public MyLogProperties myLogProperties(){
        return new MyLogProperties();
    }
    /**
     外界没有 LogService 的实现时，用默认的
     */
    @Bean
    @ConditionalOnMissingBean
    public LogService getLogService(){
        return new DefaultLogServiceImpl();
    }
    /**
     * 创建切面
     * @date 2021/10/29 17:57
     * @author zk_yjl
     * @param myLogProperties
     * @param logService
     * @return top.yueshushu.log.LogAspect
     */
    @Bean
    public LogAspect logAspect(MyLogProperties myLogProperties,LogService logService){
        return new LogAspect(myLogProperties,logService);
    }
}
