package top.yueshushu.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:MyBatisApplication
 * @Description MybatisPlus 讲解
 * @Author 岳建立
 * @Date 2021/4/26 12:01
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan(value="top.yueshushu.learn.mapper")
public class MyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class,args);
        System.out.println("运行 MybatisPlus 配置文件");
    }
}
