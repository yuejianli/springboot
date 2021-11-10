package top.yueshushu.learn.system;

import java.math.BigDecimal;

/**
 * @ClassName:Const
 * @Description 放置常量的一些信息
 * @Author 岳建立
 * @Date 2021/11/6 16:25
 * @Version 1.0
 **/
public class SystemConst {
    /**
     * 默认空返回值
     */
    public static final BigDecimal DEFAULT_EMPTY=BigDecimal.valueOf(0.0d);
    /**
     * 默认的100 值
     */
    public static final BigDecimal DEFAULT_FULL=BigDecimal.valueOf(100.0d);
    /**
     * 默认的买入最低费用
     */
    public static final BigDecimal DEFAULT_BUY_BLOW=BigDecimal.valueOf(5.0d);
    /**
     * 默认卖出的最低费用
     */
    public static final BigDecimal DEFAULT_SELL_BLOW=BigDecimal.valueOf(5.0d);
    /**
     * 默认的过户费
     */
    public static final BigDecimal DEFAULT_TRANSFER_FEE=BigDecimal.valueOf(1.0d);
    /**
     * 上海，深圳默认的通讯费
     */
    public static final BigDecimal DEFAULT_Communications=BigDecimal.valueOf(1.0d);
    /**
     * 非上海，深圳默认的通讯费
     */
    public static final BigDecimal DEFAULT_OTHER_Communications=BigDecimal.valueOf(5.0d);

    /**
     * 默认的卖出印花税
     */
    public static final BigDecimal DEFAULT_STAMP_DUTY=BigDecimal.valueOf(0.001d);

    /**
     * 默认的比例
     */
    public static final Double DEFAULT_PATTERN_STEP=0.01d;

}
