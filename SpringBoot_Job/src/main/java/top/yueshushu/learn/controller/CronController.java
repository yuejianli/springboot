package top.yueshushu.learn.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.jobConfig.MyDynamicCronJob;

import java.util.Random;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-01-03
 */
@RestController
public class CronController {

    @RequestMapping("/setCron")
    public String setCron() {
        int maxRate = new Random().nextInt(10)+4;
        String cron = "3/"+maxRate+" * * * * *";
        MyDynamicCronJob.helloCron = cron;
        return cron;
    }
}
