package top.yueshushu.learn.model.info;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName:StockShowInfo
 * @Description 基金展示的信息
 * @Author 岳建立
 * @Date 2021/11/10 21:30
 * @Version 1.0
 **/
@Data
public class StockShowInfo implements Serializable {
    /**
     * 股票的代码
     */
    private String code;
    /**
     * 股票的名称
     */
    private String name;
    /**
     * 当前天
     */
    private String date;
    /**
     * 开盘价
     */
    private BigDecimal openingPrice;

    /**
     * 昨天的收盘价
     */
    private BigDecimal yesClosingPrice;
    /**
     * 最高价格
     */
    private BigDecimal highestPrice;
    /**
     * 最低价格
     */
    private BigDecimal lowestPrice;

    /**
     * 收盘价
     */
    private BigDecimal closingPrice;

    /**
     * 当前的价格
     */
    private BigDecimal nowPrice;

    /**
     * 成交量(股)
     */
    private long tradingVolume;
    /**
     * 成交量金额
     */
    private BigDecimal tradingValue;
    /**
     * 涨跌幅度
     */
    private BigDecimal amplitude;

    /**
     * 涨跌幅度百分比
     */
    private String amplitudeProportion;
    /**
     * 市盈率
     */
    private BigDecimal peRatio;
}
