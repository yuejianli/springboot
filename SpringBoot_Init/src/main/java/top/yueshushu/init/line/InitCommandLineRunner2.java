package top.yueshushu.init.line;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import top.yueshushu.init.postconstructor.UserService;
import top.yueshushu.init.util.HardwareUtil;

/**
 * @ClassName:InitCommandLineRunner
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/30 9:07
 * @Version 1.0
 * @Since 1.0
 **/
//@Component
//@Order(2)
public class InitCommandLineRunner2 implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        userService.set();
        userService.get();
    }
}
