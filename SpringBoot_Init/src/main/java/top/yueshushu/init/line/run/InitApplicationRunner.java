package top.yueshushu.init.line.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.yueshushu.init.postconstructor.UserService;

import java.util.Random;

/**
 * @ClassName:InitApplicationRunner
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/30 11:19
 * @Version 1.0
 * @Since 1.0
 **/
//@Component
//@Order(3)
public class InitApplicationRunner implements ApplicationRunner {

    @Autowired
    private UserService userService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //在运行时，进行的操作.
        Random random=new Random();
        System.out.println(">>>输出值:"+random.nextInt(100));

        userService.set();
        userService.get();
    }
}
