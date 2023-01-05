package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:JobApplication
 * @Description 定时任务
 * @Author zk_yjl
 * @Date 2021/4/26 12:01
 * @Version 1.0
 **/
@SpringBootApplication
@Log4j2
public class JobApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class,args);
        log.info("运行定时任务启动成功");
    }
}