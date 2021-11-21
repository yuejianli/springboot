package top.yueshushu.learn.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName:YjlEntity
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/21 17:58
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("yjltest")
public class YjlEntity implements Serializable {
    /**
     * @param id id编号
     * @param name 姓名
     * @param sex 性别
     * @param age 年龄
     * @param description 描述
     */
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    @TableField(value="name")
    private String name;
    @TableField(value="sex")
    private String sex;
    @TableField(value="age")
    private Integer age;
    @TableField(value="description")
    private String description;
}

