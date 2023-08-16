package top.yueshushu.learn.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description 用户实体信息
 * @Author yuejianli
 * @Date 2022/5/20 22:57
 **/
@Data
public class User {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String account;
    private String name;
    private String token;
    // ... 其他属性信息
}
