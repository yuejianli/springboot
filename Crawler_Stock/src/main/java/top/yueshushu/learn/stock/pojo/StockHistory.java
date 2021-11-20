package top.yueshushu.learn.stock.pojo;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName:StockHistory
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/14 11:06
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("stock_history")
public class StockHistory {
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    @TableField(value="curr_date")
    private Date currDate;
    @TableField(value="code")
    private String code;
    @TableField(value="name")
    private String name;
    @TableField(value="closing_price")
    private BigDecimal closingPrice;
    @TableField(value="highest_price")
    private BigDecimal highestPrice;
    @TableField(value="lowest_price")
    private BigDecimal lowestPrice;
    @TableField(value="opening_price")
    private BigDecimal openingPrice;
    @TableField(value="yesClosing_price")
    private BigDecimal yesClosingPrice;
    @TableField(value="amplitude")
    private BigDecimal amplitude;
    @TableField(value="amplitude_proportion")
    private BigDecimal amplitudeProportion;
    @TableField(value="trading_volume")
    private long tradingVolume;
    @TableField(value="trading_value")
    private BigDecimal tradingValue;
    @TableField(value="flag")
    private Integer flag;
}
