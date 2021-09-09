package top.yueshushu.learn;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:MybatisApplication
 * @Description Mybatis 讲解
 * @Author 岳建立
 * @Date 2021/4/26 12:01
 * @Version 1.0
 **/
@MapperScan(value = "top.yueshushu.learn.mapper")
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class MyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class,args);
        System.out.println("运行 MybatisPlus 多数据源配置文件");
    }
}
