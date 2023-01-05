package top.yueshushu.learn.schedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yueshushu.learn.schedule.pojo.ScheduleSetting;

import java.util.List;

/**
 * TODO 用途描述
 *
 * @author Yue Jianli
 * @date 2023-01-05
 */

public interface ScheduleSettingService extends IService<ScheduleSetting> {
    /**
      查询所有运行中的任务
     */
    List<ScheduleSetting> findAllRunJob();
}
