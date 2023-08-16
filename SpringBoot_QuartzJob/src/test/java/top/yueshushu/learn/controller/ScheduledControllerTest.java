package top.yueshushu.learn.controller;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.yueshushu.learn.schedule.jobwork.MyTask2;
import top.yueshushu.learn.schedule.pojo.QuartzBean;
import top.yueshushu.learn.schedule.util.QuartzUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yuejianli
 * @since 2023-01-04 14:55
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduledControllerTest {
    @Resource
    private Scheduler scheduler;
    @Test
    public void createJob() {

        QuartzBean  quartzBean = new QuartzBean();
        quartzBean.setJobName("MyTask4");
        quartzBean.setGroupName("system");
        quartzBean.setCronExpression("0/5 * * * * ?");
        quartzBean.setStatus(1);

        Map<String,String> jobParam = new HashMap<>();
        jobParam.put("name","两个蝴蝶飞");
        jobParam.put("age","28");
        jobParam.put("sex","男");
        jobParam.put("description","描述信息");

        String jobParamStr = JSON.toJSONString(jobParam);
        quartzBean.setJobParam(jobParamStr);
        quartzBean.setJobClass(MyTask2.class.getName());
        QuartzUtils.createScheduleJob(scheduler,quartzBean);

        try{
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void pauseJob() {

        QuartzUtils.pauseScheduleJob(scheduler,"MyTask1");
    }

    @Test
    public void runOnce() {

        QuartzUtils.runOnce(scheduler,"MyTask1");
    }

    @Test
    public void resume() {
        QuartzUtils.resumeScheduleJob(scheduler,"MyTask1");
    }

    @Test
    public void update() {

        QuartzBean  quartzBean = new QuartzBean();
        quartzBean.setJobName("MyTask2");
        quartzBean.setGroupName("system");
        quartzBean.setCronExpression("0/2 * * * * ?");
        quartzBean.setStatus(1);

        Map<String,String> jobParam = new HashMap<>();
        jobParam.put("name","两个蝴蝶飞");
        jobParam.put("age","28");
        jobParam.put("sex","男");
        jobParam.put("description","描述信息");

        String jobParamStr = JSON.toJSONString(jobParam);
        quartzBean.setJobParam(jobParamStr);
        quartzBean.setJobClass(MyTask2.class.getName());
        QuartzUtils.updateScheduleJob(scheduler,quartzBean);

        try{
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}