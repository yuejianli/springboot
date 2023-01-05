package top.yueshushu.learn.schedule.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.schedule.config.CronTaskRegistrar;
import top.yueshushu.learn.schedule.config.SchedulingRunnable;
import top.yueshushu.learn.schedule.pojo.ScheduleSetting;
import top.yueshushu.learn.schedule.service.ScheduleSettingService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-01-05
 */
@Service
@Slf4j
public class JobServiceImpl implements JobService{

    @Resource
    private CronTaskRegistrar cronTaskRegistrar;

    @Resource
    private ScheduleSettingService scheduleSettingService;

    @Override
    public List<ScheduleSetting> findAllRunJob() {
        return scheduleSettingService.findAllRunJob();
    }

    @Override
    public boolean createJob(ScheduleSetting scheduleSetting) {


        scheduleSetting.setCreateTime(new Date());
        scheduleSetting.setUpdateTime(new Date());

        boolean insert = scheduleSettingService.save(scheduleSetting);
        if (!insert) {
            return false;
        }
        // 添加成功,并且状态是1，直接放入任务器
        if (scheduleSetting.getJobStatus().equals(1)) {
            SchedulingRunnable task = new SchedulingRunnable(scheduleSetting.getBeanName(), scheduleSetting.getMethodName(), scheduleSetting.getMethodParams());
            cronTaskRegistrar.addCronTask(task, scheduleSetting.getCronExpression());
        }
        return true;
    }

    @Override
    public boolean updateJob(ScheduleSetting scheduleSetting) {

        scheduleSetting.setCreateTime(new Date());
        scheduleSetting.setUpdateTime(new Date());

        // 查询修改前任务
        ScheduleSetting oldJobInfo = scheduleSettingService.getById(scheduleSetting.getId());
        if (null == oldJobInfo) {
            return false;
        }
        // 修改任务
        boolean update = scheduleSettingService.updateById(scheduleSetting);
        if (!update) {
            return false;
        }
        // 修改成功,则先删除任务器中的任务,并重新添加
        SchedulingRunnable task1 = new SchedulingRunnable(oldJobInfo.getBeanName(), oldJobInfo.getMethodName(), oldJobInfo.getMethodParams());
        cronTaskRegistrar.removeCronTask(task1);
        // 如果修改后的任务状态是1就加入任务器
        if (scheduleSetting.getJobStatus().equals(1)) {
            SchedulingRunnable task = new SchedulingRunnable(scheduleSetting.getBeanName(), scheduleSetting.getMethodName(), scheduleSetting.getMethodParams());
            cronTaskRegistrar.addCronTask(task, scheduleSetting.getCronExpression());
        }
        return true;
    }

    @Override
    public boolean deleteJob(Integer id) {

        ScheduleSetting oldJobInfo = scheduleSettingService.getById(id);
        if (null == oldJobInfo) {
            return false;
        }
        // 删除
        boolean del = scheduleSettingService.removeById(id);
        if (!del){
            return false;
        }
        // 删除成功时要清除定时任务器中的对应任务
        SchedulingRunnable task = new SchedulingRunnable(oldJobInfo.getBeanName(), oldJobInfo.getMethodName(), oldJobInfo.getMethodParams());
        cronTaskRegistrar.removeCronTask(task);
        return true;
    }

    @Override
    public boolean pauseJob(Integer id) {
        return changeJobStatus(id,0);
    }

    @Override
    public boolean resumeJob(Integer id) {
        return changeJobStatus(id,1);
    }

    private boolean changeJobStatus(Integer id,Integer jobStatus) {
        // 修改任务状态
        ScheduleSetting scheduleSetting = new ScheduleSetting();
        scheduleSetting.setJobStatus(jobStatus);
        scheduleSetting.setId(id);

        boolean updateJobFlag = scheduleSettingService.updateById(scheduleSetting);
        if (!updateJobFlag) {
            return false;
        }
        // 查询修改后的任务信息
        ScheduleSetting existedSysJob = scheduleSettingService.getById(id);
        // 如果状态是1则添加任务
        if (existedSysJob.getJobStatus().equals(1)) {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            cronTaskRegistrar.addCronTask(task, existedSysJob.getCronExpression());
        } else {
            // 否则清除任务
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            cronTaskRegistrar.removeCronTask(task);
        }
        return true;
    }
}
