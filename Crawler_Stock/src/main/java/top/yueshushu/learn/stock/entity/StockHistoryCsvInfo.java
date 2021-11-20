package top.yueshushu.learn.stock.entity;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName:StockHistoryCsvInfo
 * @Description 下载的股票历史交易记录的csv文件
 * @Author 岳建立
 * @Date 2021/11/14 10:11
 * @Version 1.0
 **/
@Data
public class StockHistoryCsvInfo {
    /**
     * 当前天
     */
    @Alias("日期")
    private String currDate;

    /**
     * 股票的代码
     */
    @Alias("股票代码")
    private String code;
    /**
     * 股票的名称
     */
    @Alias("名称")
    private String name;

    /**
     * 收盘价
     */
    @Alias("收盘价")
    private BigDecimal closingPrice;


    /**
     * 最高价格
     */
    @Alias("最高价")
    private BigDecimal highestPrice;
    /**
     * 最低价格
     */
    @Alias("最低价")
    private BigDecimal lowestPrice;
    /**
     * 开盘价
     */
    @Alias("开盘价")
    private BigDecimal openingPrice;

    /**
     * 昨天的收盘价
     */
    @Alias("前收盘")
    private BigDecimal yesClosingPrice;

    /**
     * 涨跌幅度
     */
    @Alias("涨跌额")
    private BigDecimal amplitude;

    /**
     * 涨跌幅度百分比
     */
    @Alias("涨跌幅")
    private BigDecimal amplitudeProportion;
    /**
     * 成交量(股)
     */
    @Alias("成交量")
    private long tradingVolume;
    /**
     * 成交量金额
     */
    @Alias("成交金额")
    private BigDecimal tradingValue;
}
