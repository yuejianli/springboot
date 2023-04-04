package top.yueshushu.learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @ClassName:MvcConfig
 * @Description Web端配置
 * @Author zk_yjl
 * @Date 2021/6/29 16:31
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * 配置静态的资源信息
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }
}
