package top.yueshushu.learn.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName:CrawlerApp
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/7 16:08
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("top.yueshushu.learn.stock.mapper")
public class CrawlerApp {
    public static void main(String[] args) {
        SpringApplication.run(CrawlerApp.class,args);
    }
}
