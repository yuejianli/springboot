package top.yueshushu.learn.schedule.business;

import top.yueshushu.learn.schedule.pojo.ScheduleSetting;

import java.util.List;

/**
 * 任务封装处理
 *
 * @author Yue Jianli
 * @date 2023-01-05
 */
public interface JobService {
    /**
        查询所有运行的任务
     */
    List<ScheduleSetting> findAllRunJob();
    /**
     * 创建定时任务
     * @param scheduleSetting 任务参数对象
     */
    boolean createJob(ScheduleSetting scheduleSetting);
    /**
     * 更新定时任务
     * @param scheduleSetting 任务参数对象
     */
    boolean updateJob(ScheduleSetting scheduleSetting);
    /**
     * 删除任务
     * @param id 任务id
     */
    boolean deleteJob(Integer id);
    /**
     * 暂停任务
     * @param id 任务id
     */
    boolean pauseJob(Integer id);
    /**
     * 重新开始任务
     * @param id 任务id
     */
    boolean resumeJob(Integer id);
}
