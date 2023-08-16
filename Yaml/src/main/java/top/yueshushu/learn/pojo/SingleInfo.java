package top.yueshushu.learn.pojo;

import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName:SingleInfo
 * @Description 放置基本的简单元素
 * @Author 岳建立
 * @Date 2021/4/10 14:21
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties
public class SingleInfo {
    private String name;
    private Boolean single;
    private Integer age;
    private String addr;
    //暂时不处理
    private Book book;
}
