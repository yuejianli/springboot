package top.yueshushu.learn;

//import com.yang.log.hui.annotion.EnableMyLog;
//import com.yang.log.hui.annotion.EnableMyLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.yueshushu.log.EnableMyLog;

/**
 * @ClassName:HelloApplication
 * @Description 启动类
 * @Author 两个蝴蝶飞
 * @Date 2021/4/9 20:39
 * @Version 1.0
 **/
//1. 添加SpringBootApplication注解
@SpringBootApplication
@EnableMyLog
public class HelloApplication {
    public static void main(String[] args) {
        //2. SpringApplication 类运行
        SpringApplication.run(HelloApplication.class,args);
        System.out.println("引入自定义Starter使用");
    }
}
