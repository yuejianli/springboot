package top.yueshushu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:HttpsApplication
 * @Description https 讲解
 * @Author 岳建立
 * @Date 2021/4/26 12:01
 * @Version 1.0
 **/
@SpringBootApplication
public class HttpsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpsApplication.class,args);
        System.out.println("运行 Https 的静态资源");
    }
}
