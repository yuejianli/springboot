package top.yueshushu.learn.cors.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName:WebConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/7/29 20:53
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置静态的资源信息
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        //映射 static 目录
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //放置其他 业务页面资源
        registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");
    }
    /**
     进行添加配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
