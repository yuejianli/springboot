package top.yueshushu.learn.mode.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName:PoundageVo
 * @Description 手续费的相关Vo
 * @Author 岳建立
 * @Date 2021/11/6 12:28
 * @Version 1.0
 **/
@Data
public class PoundageVo implements Serializable {
    /**
     * @param platformFee 平台佣金比例
     */
    private String platformFee;
    /**
     * @param tradingArea 交易的地区
     */
    private Integer tradingArea;
    /**
     * @param buyCharge 买入手续费
     */
    private String buyCharge;

    /**
     * @param buyTransferFee 买入过户费
     */
    private String buyTransferFee;

    /**
     * @param buyCommunications 买入通讯费
     */
    private String buyCommunications;
    /**
     * @param sellStampDuty 卖出印花税
     */
    private String sellStampDuty;

    /**
     * @param sellCharge 卖出手续费
     */
    private String sellCharge;

    /**
     * @param sellTransferFee 卖出过户费
     */
    private String sellTransferFee;

    /**
     * @param sellCommunications 卖出通讯费
     */
    private String sellCommunications;

}
