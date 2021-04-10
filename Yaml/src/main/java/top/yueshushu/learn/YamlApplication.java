package top.yueshushu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:YamlApplication
 * @Description yaml配置文件的讲解
 * @Author 岳建立
 * @Date 2021/4/10 12:01
 * @Version 1.0
 **/
//添加 @SpringBootApplication 注解

@SpringBootApplication
public class YamlApplication {
    public static void main(String[] args) {

        SpringApplication.run(YamlApplication.class,args);
        System.out.println("运行Yaml 配置文件");
    }
}
