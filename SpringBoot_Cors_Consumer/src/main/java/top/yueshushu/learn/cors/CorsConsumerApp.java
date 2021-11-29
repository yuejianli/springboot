package top.yueshushu.learn.cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:CorsConsumerApp
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/15 10:54
 * @Version 1.0
 **/
@SpringBootApplication
public class CorsConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(CorsConsumerApp.class,args);
        System.out.println(">>>>>>>>Cors 消费者启动成功");
    }
}
