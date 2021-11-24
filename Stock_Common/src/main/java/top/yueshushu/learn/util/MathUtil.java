package top.yueshushu.learn.util;

import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @ClassName:MathUtil
 * @Description 计算上的一些小公式
 * @Author 岳建立
 * @Date 2021/11/6 22:11
 * @Version 1.0
 **/
public class MathUtil {
    /**
     * 获取比例
     * @param start
     * @param end
     * @return
     */
    public static BigDecimal getPattern(Double start,Double end){
        Assert.notNull(start,"开始金额不为空");
        Assert.notNull(end,"结束金额不为空");
        return BigDecimal.valueOf((end-start)/(start*1.00)*100.00);
    }

    /**
     * 获取比例
     * @param before
     * @param pattern
     * @return
     */
    public static BigDecimal getNow(Double before,Double pattern){
        Assert.notNull(before,"以前的金额不为空");
        Assert.notNull(pattern,"比例不为空");
        return BigDecimal.valueOf(before*(1+pattern/100.00));
    }
}
