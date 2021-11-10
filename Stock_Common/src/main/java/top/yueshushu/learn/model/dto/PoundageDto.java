package top.yueshushu.learn.model.dto;

import lombok.Data;
import top.yueshushu.learn.enumtype.ExchangeType;
import top.yueshushu.learn.enumtype.NameOperationType;
import top.yueshushu.learn.enumtype.TradingAreaType;

import java.math.BigDecimal;

/**
 * @ClassName:PoundageVo
 * @Description 计算手续费所需要的信息
 * @Author 岳建立
 * @Date 2021/11/6 16:30
 * @Version 1.0
 **/
@Data
public class PoundageDto {
    /**
     * @param platformFee 平台佣金比例
     */
    private BigDecimal platformFee;
    /**
     * @param tradingArea 交易的地区
     */
    private TradingAreaType tradingAreaType;

    /**
     * @param price 买入时的价格
     */
    private BigDecimal buyPrice;

    /**
     * @param price 卖出时的价格
     */
    private BigDecimal sellPrice;
    /**
     * @param number 买入的股票数
     */
    private Integer buyNumber;

    /**
     * @param number 卖出股票数
     */
    private Integer sellNumber;
    /**
     * @param exchangeType 交易所的类型
     */
    private ExchangeType exchangeType;
    /**
     * @param nameOperationType 是否更改了户名
     */
    private NameOperationType nameOperationType;
}
