package top.yueshushu.log;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName:EnableMyLog
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/10/25 20:28
 * @Version 1.0
 * @Since 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(LogMarkerConfiguration.class)
public @interface EnableMyLog {

}
