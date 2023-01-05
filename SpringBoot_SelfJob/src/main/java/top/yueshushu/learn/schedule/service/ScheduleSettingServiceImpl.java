package top.yueshushu.learn.schedule.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.schedule.mapper.ScheduleSettingMapper;
import top.yueshushu.learn.schedule.pojo.ScheduleSetting;

import java.util.List;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-01-05
 */
@Service
@Slf4j
public class ScheduleSettingServiceImpl extends ServiceImpl<ScheduleSettingMapper, ScheduleSetting>
    implements ScheduleSettingService{


    @Override
    public List<ScheduleSetting> findAllRunJob() {
        return this.lambdaQuery()
                .eq(ScheduleSetting::getJobStatus,1)
                .list();
    }
}
