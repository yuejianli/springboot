package top.yueshushu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:MybatisApplication
 * @Description Mybatis 讲解
 * @Author 岳建立
 * @Date 2021/4/26 12:01
 * @Version 1.0
 **/
//可以排除依赖，也可以不排除。
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class MyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class,args);
        System.out.println("运行 Mybatis 多数据源配置文件");
    }
}
