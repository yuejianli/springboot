package top.yueshushu.learn.pojo.two;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName:Dept
 * @Description SpringBoot
 * @Author zk_yjl
 * @Date 2021/9/2 10:28
 * @Version 1.0
 * @Since 1.0
 **/
@Data
@Table(name = "dept")
@Entity
public class Dept {
    /**
     * @param id id编号
     * @param name 部门名称
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
}
