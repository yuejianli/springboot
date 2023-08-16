package top.yueshushu.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import top.yueshushu.init.listener.InitListener;

/**
 * @ClassName:InitApplication
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/29 12:02
 * @Version 1.0
 * @Since 1.0
 **/
@SpringBootApplication
public class InitApplication {
    public static void main(String[] args) {
        SpringApplication.run(InitApplication.class,args);
        System.out.println(">>>>>>>项目启动成功");
        //执行初始化数据.
       // initData();
    }
   /* public static void initData(){
        System.out.println("静态方法初始化数据");
    }
*/
    /**
     * 添加Listener
     * @date 2021/11/30 9:03
     * @author zk_yjl
     * @return
     */
  //  @Bean
    public ServletListenerRegistrationBean<InitListener> initListenerServletListenerRegistrationBean(){
        ServletListenerRegistrationBean<InitListener> registrationBean=new ServletListenerRegistrationBean<>(
                new InitListener()
        );
        return registrationBean;
    }
}
