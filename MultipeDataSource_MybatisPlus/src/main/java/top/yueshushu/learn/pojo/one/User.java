package top.yueshushu.learn.pojo.one;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@TableName("user")
public class User implements Serializable {
    /**
     * @param id id编号
     * @param name 姓名
     * @param sex 性别
     * @param age 年龄
     * @param description 描述
     */
    @TableId(value = "id",type =IdType.AUTO)
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("sex")
    private String sex;
    @TableField("age")
    private Integer age;
    @TableField("description")
    private String description;
}


