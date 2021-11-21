package top.yueshushu.learn.freemark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:FreeMarkerApp
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/15 10:54
 * @Version 1.0
 **/
@SpringBootApplication
public class FreeMarkerApp {
    public static void main(String[] args) {
        SpringApplication.run(FreeMarkerApp.class,args);
        System.out.println(">>>>>>>>FreeMarker整合成功");
    }
}
