package top.yueshushu.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName:RestConfig
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/12 23:06
 * @Version 1.0
 **/
@Configuration
public class RestConfig {
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate=  new RestTemplate();
        return restTemplate;
    }
}
