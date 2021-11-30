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
//@Order(1)
public class InitCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        //args 获取传入的参数。
        //容器启动时，进行的操作。 如获取计算机的 机器码
        String cpuId = HardwareUtil.getCpuId();
        if(!StringUtils.hasText(cpuId)){
            System.out.println(">>>没有机器码");

        }
        String singleSignature = Base64Utils.encodeToString(cpuId.getBytes());
        System.out.println(">>>机器码是:"+singleSignature);
    }
}
