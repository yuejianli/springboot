package top.yueshushu.learn.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
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
@PropertySource(value = "classpath:myapplication.yml",encoding = "UTF-8")
public class SingleInfo2 {
    @Value("${name}")
    private String name;
    @Value("${single}")
    private Boolean single;
    @Value("${age}")
    private Integer age;
    @Value("${addr}")
    private String addr;
    //暂时不处理
    private Book book;
}
