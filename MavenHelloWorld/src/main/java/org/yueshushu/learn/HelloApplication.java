package org.yueshushu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:HelloApplication
 * @Description 启动类
 * @Author 两个蝴蝶飞
 * @Date 2021/4/9 20:39
 * @Version 1.0
 **/
//1. 添加SpringBootApplication注解
@SpringBootApplication
public class HelloApplication {
    public static void main(String[] args) {
        //2. SpringApplication 类运行
        SpringApplication.run(HelloApplication.class,args);
        System.out.println("欢迎我，我是两个蝴蝶飞，我启动了!!!!");
    }
}
