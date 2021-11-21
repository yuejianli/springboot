package top.yueshushu.log;

import java.lang.annotation.*;

/**
 * @ClassName:Log
 * @Description 自定义的日志注解
 * @Author zk_yjl
 * @Date 2021/10/25 10:19
 * @Version 1.0
 * @Since 1.0
 **/
// 适用于方法上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String module() default "默认模块";
    String optType() default "默认类型";
    String description() default "默认说明";
}
