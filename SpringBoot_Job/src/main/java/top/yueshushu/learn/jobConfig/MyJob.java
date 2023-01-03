package top.yueshushu.learn.jobConfig;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.yueshushu.learn.service.UserService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:MyJob
 * @Description 定义任务的相关操作
 * @Author zk_yjl
 * @Date 2021/12/1 17:46
 * @Version 1.0
 * @Since 1.0
 **/
@Component
@Log4j2
public class MyJob {
    //每2秒执行一次，是上一次启动和这一次启动的时间间隔
    // @Scheduled(fixedRate = 2000)
    public void job1(){
        sayHello();
    }
   //每2秒执行一次，是上一次结束和这一次启动的时间间隔
    // @Scheduled(fixedDelay = 2000)
    public void job2(){
        sayHello();
    }
   //fixedRate 与 fixedDelay 的小区别
   // @Scheduled(fixedRate = 2000)
    public void job3() throws Exception{
        log.info(">>>>fixedRate 进来了");
        Thread.sleep(3000);
    }

    // @Scheduled(fixedDelay = 2000)
    public void job31() throws Exception{
        log.info(">>>>fixedDelay 进来了");
        //休眠 3000s
        Thread.sleep(3000);
    }
    //initialDelay 首次任务启动的延迟时间
   // @Scheduled(initialDelay = 3000,fixedRate = 2000)
    public void job4() throws Exception{
        sayHello();
    }
    // cron 表达式
  //  @Scheduled(cron = "3/2 * * * * *")
    public void job5() throws Exception{
        sayHello();
    }

    //里面使用 userService 实例
    @Resource
    private UserService userService;
   //  @Scheduled(cron = "3/2 * * * * *")
    public void job6() throws Exception{
        //执行任务，复杂的情况，不建议使用.
        // 采用 quartz 的方式实现
        userService.addUser(null);
    }

    public void sayHello(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String now=simpleDateFormat.format(new Date());
        System.out.println(">>>你好，当前时间是:"+now);
    }
}
