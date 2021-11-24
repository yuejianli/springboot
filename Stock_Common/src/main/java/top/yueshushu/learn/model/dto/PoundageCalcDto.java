package top.yueshushu.learn.model.dto;

import lombok.Data;
import top.yueshushu.learn.enumtype.TradingAreaType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName:PoundageCalcDto
 * @Description 交易费用计算结果Dto
 * @Author 岳建立
 * @Date 2021/11/6 16:36
 * @Version 1.0
 **/
@Data
public class PoundageCalcDto implements Serializable {
    /**
     * @param platformFee 平台佣金比例
     */
    private BigDecimal platformFee;
    /**
     * @param tradingArea 交易的地区
     */
    private TradingAreaType tradingAreaType;
    /**
     * @param buyCharge 买入手续费
     */
    private BigDecimal buyCharge;

    /**
     * @param buyTransferFee 买入过户费
     */
    private BigDecimal buyTransferFee;

    /**
     * @param buyCommunications 买入通讯费
     */
    private BigDecimal buyCommunications;
    /**
     * @param sellStampDuty 卖出印花税
     */
    private BigDecimal sellStampDuty;
    /**
     * @param sellCharge 卖出手续费
     */
    private BigDecimal sellCharge;

    /**
     * @param sellTransferFee 卖出过户费
     */
    private BigDecimal sellTransferFee;

    /**
     * @param sellCommunications 卖出通讯费
     */
    private BigDecimal sellCommunications;

    /**
     * @param totalBuyCharge 总的买入费用
     */
    private BigDecimal totalBuyCharge;
    /**
     * @param totalSellCharge 总的卖出费用
     */
    private BigDecimal totalSellCharge;
    /**
     * @param totalCharge 总的手续费用
     */
    private BigDecimal totalCharge;
}
