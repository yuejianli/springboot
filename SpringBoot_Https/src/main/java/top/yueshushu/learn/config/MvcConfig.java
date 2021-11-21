package top.yueshushu.learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName:MvcConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/6/29 16:31
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
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

}
