package top.yueshushu.learn.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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

    /*处理自动插入创建时间，自动更新修改时间*/
    @TableField(value="create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value="update_time",fill = FieldFill.UPDATE)
    private Date updateTime;

    /*处理标识位*/
    @TableField(value="flag",fill = FieldFill.INSERT)
    @TableLogic(value = "1",delval = "0")
    private Integer flag;
}


