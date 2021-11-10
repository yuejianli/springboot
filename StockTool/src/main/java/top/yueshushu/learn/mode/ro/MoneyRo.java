package top.yueshushu.learn.mode.ro;

import lombok.Data;

/**
 * @ClassName:MoneyRo
 * @Description 接收价格计算的相关参数
 * @Author 岳建立
 * @Date 2021/11/6 12:21
 * @Version 1.0
 **/
@Data
public class MoneyRo {
    /**
     * @param code 股票的代码
     */
    private String code;
    /**
     * @param price 买入时的价格
     */
    private Double price;
    /**
     * @param number 买入的股票数
     */
    private Integer number;
    /**
     * @param platformFee 平台佣金比例
     */
    private Double platformFee;
    /**
     * @param tradingArea 交易的地区
     */
    private Integer tradingArea=0;
    /**
     * @param nameType 是否更换了户名
     */
    private Integer nameType=0;
    /**
     * @param makeMoney 预期要赚的钱
     */
    private Double makeMoney;
    /**
     * @param makePrice 预期的价格
     */
    private Double makePrice;
    /**
     * @param makeProportion 预期的比例
     */
    private Double makeProportion;
    /**
     * @param type 类型
     */
    private Integer type;
}
