package top.yueshushu.learn.mode.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:MoneyVo
 * @Description 金额计算展示相关信息
 * @Author 岳建立
 * @Date 2021/11/6 12:22
 * @Version 1.0
 **/
@Data
public class MoneyVo extends PoundageVo implements Serializable{
    /**
     * @param code 股票的代码
     */
    private String code;
    /**
     * @param price 买入时的价格
     */
    private String price;
    /**
     * @param number 买入的股票数
     */
    private Integer number;

    /**
     * @param makeMoney 预期要赚的钱
     */
    private String makeMoney;
    /**
     * @param makePrice 预期的价格
     */
    private String makePrice;
    /**
     * @param makeProportion 预期的比例
     */
    private String makeProportion;
    /**
     * @param type 类型
     */
    private Integer type;

    /**
     * @param buyMoney 买入的金额
     */
    private String buyMoney;

    /**
     * @param sellMoney 卖出的金额
     */
    private String sellMoney;
    /**
     * @param destMoney 最后实际到账的钱，去掉相关的费用之后
     */
    private String destMoney;

    /**
     * @parma realBuyMoney 实际买入的金额,即买入发生金额.
     */
    private String buyActualMoney;

    /**
     * @parma noSellMoney 不卖的话，现在的浮盈数
     */
    private String noSellMoney;

    /**
     * @param realMoney 这次操作实际赚到的钱
     */
    private String realMoney;

    /**
     * @param realPrice 这次操作实际的价格
     */
    private String realPrice;

    /**
     * @param realProportion 这次操作实际的比例
     */
    private String realProportion;

    /**
     * @param totalBuyCharge 总的买入费用
     */
    private String totalBuyCharge;
    /**
     * @param totalSellCharge 总的卖出费用
     */
    private String totalSellCharge;
    /**
     * @param totalCharge 总的手续费用
     */
    private String totalCharge;


}
