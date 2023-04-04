package top.yueshushu.learn;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class FilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilterApplication.class,args);
        System.out.println("运行 Filter 文件");
    }
}
