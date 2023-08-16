package top.yueshushu.learn;

import cn.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author yuejianli
 * @date 2023-04-10
 */
@SpringBootApplication
@EsMapperScan("top.yueshushu.learn.esmapper")
public class EasyESApp {
    public static void main(String[] args) {
        SpringApplication.run(EasyESApp.class,args);
    }
}
