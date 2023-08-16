package top.yueshushu.learn.schedule.controller;

import org.springframework.web.bind.annotation.*;
import top.yueshushu.learn.schedule.business.JobService;
import top.yueshushu.learn.schedule.pojo.ScheduleSetting;

import javax.annotation.Resource;
import java.util.List;

/**
 * 入口类
 *
 * @author yuejianli
 * @date 2023-01-05
 */

@RestController
@RequestMapping("/job")
public class JobController {
    @Resource
    private JobService jobService;


    @RequestMapping("/findAllRunJob")
    public List<ScheduleSetting> findAllRunJob()  {
        try {
            return jobService.findAllRunJob();
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 创建定时任务
     *
     * @param scheduleSetting
     * @return
     */
    @PostMapping("create")
    public String create(@RequestBody ScheduleSetting scheduleSetting) {
        return jobService.createJob(scheduleSetting) ? "创建成功":"创建失败";
    }

    /**
     * 修改定时任务
     *
     * @param scheduleSetting
     * @return
     */
    @PostMapping("update")
    public String update(@RequestBody ScheduleSetting scheduleSetting) {
        return jobService.updateJob(scheduleSetting) ? "更新成功":"更新失败";
    }

    /**
     * 删除任务
     *
     * @param id 任务id
     * @return
     */
    @PostMapping("delete/{jobId}")
    public String del(@PathVariable("jobId") Integer id) {
        return jobService.deleteJob(id) ? "删除成功":"删除失败";
    }

    /**
     * 暂停任务
     *
     * @param id 任务id
     * @return
     */
    @PostMapping("pause/{jobId}")
    public String pause(@PathVariable("jobId") Integer id) {
        return jobService.pauseJob(id) ? "暂停成功":"暂停失败";
    }

    /**
     * 重新开始任务
     *
     * @param id 任务id
     * @return
     */
    @PostMapping("resume/{jobId}")
    public String resume(@PathVariable("jobId") Integer id) {
        return jobService.resumeJob(id) ? "重新开始任务成功":"重新开始任务失败";
    }



}