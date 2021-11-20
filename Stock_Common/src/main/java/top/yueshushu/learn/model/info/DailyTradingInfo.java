package top.yueshushu.learn.model.info;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 12905
 * 股票展示的，每天的交易信息
 */
@Data
public class DailyTradingInfo extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 股票的代码
     */
    private String code;
    /**
     * 当前天
     */
    private Date date;
    /**
     * 昨天的收盘价
     */
    private BigDecimal yesClosingPrice;
    /**
    /**
     * 开盘价
     */
    private BigDecimal openingPrice;
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
    private BigDecimal amplitudeProportion;
}
