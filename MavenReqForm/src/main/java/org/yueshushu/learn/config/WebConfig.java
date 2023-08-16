package org.yueshushu.learn.config;

/**
 * @ClassName:WebConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/24 9:02
 * @Version 1.0
 * @Since 1.0
 **/
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.yueshushu.learn.resolver.RequestJsonHandlerMethodArgumentResolver;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
@SuppressWarnings("deprecation")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RequestJsonHandlerMethodArgumentResolver());
       // argumentResolvers.add(new RequestBodyHandlerMethodArgumentResolver());
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }


}
