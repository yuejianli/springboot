package top.yueshushu.learn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import top.yueshushu.learn.pojo.relation.UserListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName:User
 * @Description 用户实体
 * @Author 两个蝴蝶飞
 * @Date 2021/4/24 20:01
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
@EntityListeners(value = {UserListener.class})
@Proxy(lazy = false)
public class User implements Serializable {
    /**
     * @param id id编号
     * @param name 姓名
     * @param sex 性别
     * @param age 年龄
     * @param description 描述
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name="sex")
    private String sex;
    @Column(name="age")
    private Integer age;
    @Column(name="description")
    private String description;
}


