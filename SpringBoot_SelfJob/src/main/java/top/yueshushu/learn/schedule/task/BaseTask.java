package top.yueshushu.learn.schedule.task;

/**
 * 基础 Task 工具类
 *
 * @author Yue Jianli
 * @date 2023-01-05
 */

public interface BaseTask {
    /**
     * 执行任务
     * @param param 参数
     */
    void execute(String param);
}
