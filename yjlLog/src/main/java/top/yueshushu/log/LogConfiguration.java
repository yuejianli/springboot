package top.yueshushu.log;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
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
//存在这个bean时，下面的生效。
@ConditionalOnBean(LogMarkerConfiguration.class)
public class LogConfiguration {

   // @Bean
    @ConditionalOnMissingBean
    public MyLogProperties myLogProperties(){
        return new MyLogProperties();
    }
    @Bean
    @ConditionalOnMissingBean
    public LogService getLogService(){
        return new DefaultLogServiceImpl();
    }
    @Bean
    public LogAspect logAspect(MyLogProperties myLogProperties,LogService logService){
        return new LogAspect(myLogProperties,logService);
    }
}
