package top.yueshushu.learn.schedule.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 任务表
 *
 * @author yuejianli
 * @date 2023-01-05
 */
@Data
@TableName("schedule_setting")
public class ScheduleSetting {
    /**
     * 任务ID
     */
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;
    /**
     * bean名称
     */
    @TableField("bean_name")
    private String beanName;
    /**
     * 方法名称
     */
    @TableField("method_name")
    private String methodName;
    /**
     * 方法参数
     */
    @TableField("method_params")
    private String methodParams;
    /**
     * cron表达式
     */
    @TableField("cron_expression")
    private String cronExpression;
    /**
     * 状态（1正常 0暂停）
     */
    @TableField("job_status")
    private Integer jobStatus;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}