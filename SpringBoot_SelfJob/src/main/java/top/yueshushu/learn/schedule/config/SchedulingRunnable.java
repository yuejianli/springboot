package top.yueshushu.learn.schedule.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import top.yueshushu.learn.schedule.util.SpringContextUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-01-05
 */
@Slf4j
public class SchedulingRunnable implements Runnable {
    
    private String beanName;

    private String methodName;

    private String params;

    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }

    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }

    @Override
    public void run() {
      //  log.info("定时任务开始执行 - bean：{}，方法：{}，参数：{}", beanName, methodName, params);
        long startTime = System.currentTimeMillis();

        try {
            Object target = SpringContextUtils.getBean(beanName);

            Method method = null;
//            if (StringUtils.hasText(params)) {
//                method = target.getClass().getDeclaredMethod(methodName, String.class);
//            } else {
//                method = target.getClass().getDeclaredMethod(methodName);
//            }

            method = target.getClass().getDeclaredMethod(methodName, String.class);

            ReflectionUtils.makeAccessible(method);
//            if (StringUtils.hasText(params)) {
//                method.invoke(target, params);
//            } else {
//                method.invoke(target);
//            }

            method.invoke(target, params);
        } catch (Exception ex) {
            log.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s ", beanName, methodName, params), ex);
        }

        long times = System.currentTimeMillis() - startTime;
      //  log.info("定时任务执行结束 - bean：{}，方法：{}，参数：{}，耗时：{} 毫秒", beanName, methodName, params, times);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchedulingRunnable that = (SchedulingRunnable) o;
        if (params == null) {
            return beanName.equals(that.beanName) &&
                    methodName.equals(that.methodName) &&
                    that.params == null;
        }

        return beanName.equals(that.beanName) &&
                methodName.equals(that.methodName) &&
                params.equals(that.params);
    }

    @Override
    public int hashCode() {
        if (params == null) {
            return Objects.hash(beanName, methodName);
        }

        return Objects.hash(beanName, methodName, params);
    }
}