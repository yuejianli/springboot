package top.yueshushu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:JdbcApplication
 * @Description jdbc多数据源配置
 * @Author 岳建立
 * @Date 2021/4/10 12:01
 * @Version 1.0
 **/
//添加 @SpringBootApplication 注解

@SpringBootApplication
public class JdbcApplication {
    public static void main(String[] args) {

        SpringApplication.run(JdbcApplication.class,args);
        System.out.println("运行 JdbcTemplate 多数据源配置文件");
    }
}
