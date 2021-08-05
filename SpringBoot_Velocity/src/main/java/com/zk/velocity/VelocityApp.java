package com.zk.velocity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:VelocityApp
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/15 19:42
 * @Version 1.0
 **/
@SpringBootApplication
public class VelocityApp {
    public static void main(String[] args) {
        SpringApplication.run(VelocityApp.class,args);
        System.out.println(">>>>>>>>>>>>>运行 Velocity");
    }
}
