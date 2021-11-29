package top.yueshushu.learn.cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:FreeMarkerApp
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/15 10:54
 * @Version 1.0
 **/
@SpringBootApplication
public class CorsProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(CorsProviderApp.class,args);
        System.out.println(">>>>>>>>Cors 提供者");
    }
}
