package top.yueshushu.learn.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 部门索引
 *
 * @author yuejianli
 * @date 2023-04-11
 */
@Data
public class Depart implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String description;
}
