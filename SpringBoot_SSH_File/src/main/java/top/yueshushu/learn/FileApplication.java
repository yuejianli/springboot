package top.yueshushu.learn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.yueshushu.learn.config.JavaConfig;

/**
 * @ClassName:FileApplication
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/4 20:34
 * @Version 1.0
 * @Since 1.0
 **/
@SpringBootApplication
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class,args);
    }
}
