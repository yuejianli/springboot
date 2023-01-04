package top.yueshushu.learn.controller;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-01-03
 */
import org.quartz.Scheduler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.schedule.pojo.QuartzBean;
import top.yueshushu.learn.schedule.util.QuartzUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务 controller
 */
@RestController
@RequestMapping("/quartz/")
public class ScheduledController {
    /**
     注入任务调度
     */
    @Resource
    private Scheduler scheduler;

    @RequestMapping("/getAll")
    public List<QuartzBean>  getAll()  {
        try {
            return QuartzUtils.getAllJob(scheduler);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/createJob")
    public String  createJob(@RequestBody QuartzBean quartzBean)  {
        try {
            QuartzUtils.createScheduleJob(scheduler,quartzBean);
        } catch (Exception e) {
            return "创建失败";
        }
        return "创建成功";
    }

    @RequestMapping("/pauseJob")
    public String pauseJob(String jobName)  {
        try {
            QuartzUtils.pauseScheduleJob (scheduler,jobName);
        } catch (Exception e) {
            return "暂停失败";
        }
        return "暂停成功";
    }

    @RequestMapping("/runOnce")
    @ResponseBody
    public String  runOnce(String jobName)  {
        try {
            QuartzUtils.runOnce (scheduler,jobName);
        } catch (Exception e) {
            return "运行一次失败";
        }
        return "运行一次成功";
    }

    @RequestMapping("/resume")
    @ResponseBody
    public String  resume(String jobName)  {
        try {

            QuartzUtils.resumeScheduleJob(scheduler,jobName);
        } catch (Exception e) {
            return "重新开启失败";
        }
        return "重新开启成功";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String  delete(String jobName)  {
        try {

            QuartzUtils.deleteScheduleJob(scheduler,jobName);
        } catch (Exception e) {
            return "删除失败";
        }
        return "删除成功";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String  update(@RequestBody QuartzBean quartzBean)  {
        try {
            QuartzUtils.updateScheduleJob(scheduler,quartzBean);
        } catch (Exception e) {
            return "更新失败";
        }
        return "更新成功";
    }
}