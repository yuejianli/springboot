package top.yueshushu.init.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName:InitListener
 * @Description 监听器
 * @Author zk_yjl
 * @Date 2021/11/30 9:00
 * @Version 1.0
 * @Since 1.0
 **/
public class InitListener implements ServletContextListener {
    /**
      容器启动时
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //初始化必要的数据. 常于构建缓存，初始化数据.
        System.out.println(">>>>Listener,容器启动,初始化必要的数据");
    }
    /**
     容器突然销毁时
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //容器关闭了，进行数据备份的处理.
        System.out.println(">>>>Listener,容器销毁，将重要数据备份");
    }
}
