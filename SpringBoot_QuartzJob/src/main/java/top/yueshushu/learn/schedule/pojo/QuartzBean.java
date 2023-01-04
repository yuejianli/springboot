package top.yueshushu.learn.schedule.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接收 job 的实体信息
 * @author yuejianli
 * @date 2023-01-04
 */
@Data
public class QuartzBean implements Serializable {
    /** 任务id */
    private String  id;

    /** 任务名称 */
    private String jobName;

    /** 任务执行类 */
    private String jobClass;

    /** 组名 */
    private String groupName;

    /** 任务 参数信息 */
    private String jobParam;

    /** 任务状态 启动还是暂停*/
    private Integer status;

    /** 任务运行时间表达式 */
    private String cronExpression;
}
