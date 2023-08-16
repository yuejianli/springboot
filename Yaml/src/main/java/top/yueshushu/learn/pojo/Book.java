package top.yueshushu.learn.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName:Book
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/10 14:58
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "book")
public class Book {
    private String name;
    private String author;
    private Double score;
    private String[] hobby;
}
