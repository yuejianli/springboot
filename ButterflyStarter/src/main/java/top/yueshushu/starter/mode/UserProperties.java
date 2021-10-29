package top.yueshushu.starter.mode;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.io.Serializable;

/**
 * @ClassName:User
 * @Description 自定义的一个实体
 * @Author zk_yjl
 * @Date 2021/10/22 16:57
 * @Version 1.0
 * @Since 1.0
 **/
/**
    不建议引用 @Data  lombok 第三方插件
 * @author yjl
 */
@SuppressWarnings("serial")
@ConfigurationProperties(prefix = "butterfly")
public class UserProperties implements Serializable {
    /**
      定义 final 常量，用于默认值
     */
    private final String DEFAULT_NAME="两个蝴蝶飞";
    private final Integer DEFAULT_AGE=26;
    private final String DEFAULT_DESCRIPTION="一个快乐的程序员";

    /**
        定义属性,使用默认值.
     */
    private String name=DEFAULT_NAME;
    private Integer age=DEFAULT_AGE;
    private String description=DEFAULT_DESCRIPTION;

    /**
       提示默认的构造方法
     */
    public UserProperties() {

    }

    public UserProperties(String name, Integer age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }
}
