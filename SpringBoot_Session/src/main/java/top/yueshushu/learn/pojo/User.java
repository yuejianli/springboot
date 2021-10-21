package top.yueshushu.learn.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:User
 * @Description 用户实体
 * @Author 两个蝴蝶飞
 * @Date 2021/4/24 20:01
 * @Version 1.0
 **/
@Data   //缓存，必须要使用到 Serializable 序列化
public class User implements Serializable {
    /**
     * @param id id编号
     * @param name 姓名
     * @param sex 性别
     * @param age 年龄
     * @param description 描述
     * @param password 密码
     */
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String description;
    private String account;
    private String password;
}


