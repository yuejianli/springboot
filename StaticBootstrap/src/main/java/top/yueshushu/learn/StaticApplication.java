package top.yueshushu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:YamlApplication
 * @Description static 讲解
 * @Author 岳建立
 * @Date 2021/4/26 12:01
 * @Version 1.0
 **/
@SpringBootApplication
public class StaticApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaticApplication.class,args);
        System.out.println("运行 StaticBootstrap 静态资源");
    }
}
