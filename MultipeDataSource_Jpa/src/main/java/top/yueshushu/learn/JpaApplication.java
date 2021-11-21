package top.yueshushu.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @ClassName:JpaApplication
 * @Description jpa 讲解
 * @Author 岳建立
 * @Date 2021/4/26 12:01
 * @Version 1.0
 **/
@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class JpaApplication {
    public static void main(String[] args) {

        SpringApplication.run(JpaApplication.class,args);
        System.out.println("运行 多数据源配置 JPA文件");
    }
}
