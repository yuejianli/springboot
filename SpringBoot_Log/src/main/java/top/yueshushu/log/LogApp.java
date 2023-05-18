package top.yueshushu.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 日志配置
 *
 * @author yuejianli
 * @date 2023-05-18
 */
@Slf4j
@SpringBootApplication
public class LogApp {
    public static void main(String[] args) {
        SpringApplication.run(LogApp.class,args);
    }
}
