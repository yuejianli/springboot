package top.yueshushu.learn.pojo.two;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("dept")
public class Dept implements Serializable {
    /**
     * @param id id编号
     * @param name 部门名称
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("name")
    private String name;
}
