package top.yueshushu.learn.config;

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
//@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置静态的资源信息
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      /* registry.addResourceHandler(
               "/**"
       ).addResourceLocations(
               "classpath:/templates/"
       );*/
       /* registry.addResourceHandler(
                "/yjl/**"   //对应路径的路径是  yjl/
        ).addResourceLocations(
                "classpath:/templates/"  //目录是  templates
        );*/
      /*  registry.addResourceHandler(
                "s1/**"   //目录s1
        ).addResourceLocations(
                "classpath:/s1/"
        );
        registry.addResourceHandler("s2/**")   //目录s2
                .addResourceLocations("classpath:/s2/");*/

       registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");
    }
}
