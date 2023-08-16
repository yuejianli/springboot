package top.yueshushu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @ClassName:YamlApplication
 * @Description yaml配置文件的讲解
 * @Author 岳建立
 * @Date 2021/4/10 12:01
 * @Version 1.0
 **/
//添加 @SpringBootApplication 注解

@SpringBootApplication
public class JdbcApplication {
    public static void main(String[] args) {

        SpringApplication.run(JdbcApplication.class,args);
        System.out.println("运行 JdbcTemplate 配置文件");
    }
}
