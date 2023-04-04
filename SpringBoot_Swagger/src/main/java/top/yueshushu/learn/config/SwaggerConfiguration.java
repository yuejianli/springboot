package top.yueshushu.learn.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:SwaggerConfig
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/1/1 10:43
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfiguration implements Serializable {
    @Bean
    public Docket createBasisRestApi(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiBasisInfo())
                .groupName("Stock模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.yueshushu.learn"))
                .paths(PathSelectors.ant("/**"))
                .build()
                // 设置全局参数
                .globalOperationParameters(pars)
                .pathMapping("/");
    }
    /**
     * 基础信息的API
     * @return
     */
    private ApiInfo apiBasisInfo(){
        return new ApiInfoBuilder()
                .title("Stock")
                .description("股票信息模块")
                .termsOfServiceUrl("http://localhost/")
                .contact(new Contact("岳建立", "https://www.yueshushu.top/Stock", "1290513799@qq.com"))
                .version("1.0")
                .build();
    }
}
