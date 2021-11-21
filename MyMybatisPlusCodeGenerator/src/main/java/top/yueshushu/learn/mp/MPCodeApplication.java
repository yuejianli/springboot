package top.yueshushu.learn.mp;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:MPCodeApplication
 * @Description 自动代码生成
 * @Author yjl
 * @Date 2021/6/15 10:54
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan(basePackages = "top.yueshushu.learn.mp.mapper")
public class MPCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MPCodeApplication.class,args);
        System.out.println(">>>>>>>>MyBatisPlusCodeGenerator 自动代码生成");
    }
}
