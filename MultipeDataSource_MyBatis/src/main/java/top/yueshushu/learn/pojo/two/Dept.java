package top.yueshushu.learn.pojo.two;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:Dept
 * @Description SpringBoot
 * @Author zk_yjl
 * @Date 2021/9/2 10:28
 * @Version 1.0
 * @Since 1.0
 **/
@Data
public class Dept implements Serializable {
    /**
     * @param id id编号
     * @param name 部门名称
     */
    private Integer id;
    private String name;
}
