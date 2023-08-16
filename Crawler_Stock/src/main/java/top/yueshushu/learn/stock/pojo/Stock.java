package top.yueshushu.learn.stock.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:Stock
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/13 23:59
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("stock")
public class Stock {
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    @TableField(value="code")
    private String code;
    @TableField(value="name")
    private String name;
    @TableField(value="exchange")
    private Integer exchange;
    @TableField(value="full_code")
    private String fullCode;
    @TableField(value="flag")
    private Integer flag;

}
