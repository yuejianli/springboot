package top.yueshushu.learn;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:YamlApplication
 * @Description jpa 讲解
 * @Author 岳建立
 * @Date 2021/4/26 12:01
 * @Version 1.0
 **/
@MapperScan("top.yueshushu.learn.mapper")
@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class,args);
        System.out.println("运行 Redis 配置文件");
    }
}