package top.yueshushu.learn.schedule.config;

import java.util.concurrent.ScheduledFuture;

/**
 * 调度任务
 *
 * @author yuejianli
 * @date 2023-01-05
 */

public final class ScheduledTask {

    volatile ScheduledFuture<?> future;
    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}