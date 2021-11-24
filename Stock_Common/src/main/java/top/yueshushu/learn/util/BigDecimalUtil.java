package top.yueshushu.learn.util;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @ClassName:BigDecimalUtil
 * @Description BigDecimalUtil 的转换工具类. 保留四位小数
 * @Author 岳建立
 * @Date 2021/11/6 17:50
 * @Version 1.0
 **/
public class BigDecimalUtil {
    /**
     * 转换 成四位小数的字符串
     * @param bigDecimal
     * @return
     */
    public static String toString(BigDecimal bigDecimal){
        Assert.notNull(bigDecimal,"格式化的源 BigDecimal不能为空");
        return String.valueOf(bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 转换 成 2小数的字符串
     * @param bigDecimal
     * @return
     */
    public static String toShowString(BigDecimal bigDecimal){
        Assert.notNull(bigDecimal,"格式化的源 BigDecimal不能为空");
        return String.valueOf(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 两个数相除，获取对应的百分比
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    public static String divPattern(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
        Assert.notNull(bigDecimal1,"格式化的源 bigDecimal1 不能为空");
        Assert.notNull(bigDecimal2,"格式化的源 bigDecimal2 不能为空");

        BigDecimal devide =bigDecimal1.divide(bigDecimal2,4, RoundingMode.HALF_UP);
        //将结果百分比
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        return percent.format(devide.doubleValue());
    }

    /**
     * 两个数相加
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    public static BigDecimal addBigDecimal(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
        Assert.notNull(bigDecimal1,"格式化的源 bigDecimal1 不能为空");
        Assert.notNull(bigDecimal2,"格式化的源 bigDecimal2 不能为空");
        return bigDecimal1.add(bigDecimal2);
    }
    /**
     * 两个数相减
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    public static BigDecimal subBigDecimal(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
        Assert.notNull(bigDecimal1,"格式化的源 bigDecimal1 不能为空");
        Assert.notNull(bigDecimal2,"格式化的源 bigDecimal2 不能为空");
        return bigDecimal1.subtract(bigDecimal2);
    }
    /**
     * 转换，成四位小数的 BigDecimal
     * @param str
     * @return
     */
    public static BigDecimal toBigDecimal(String str){
        Assert.notNull(str,"格式化源 str 不能为空");
        BigDecimal bigDecimal=new BigDecimal(str);
        return bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 转换，成四位小数的 BigDecimal
     * @param value
     * @return
     */
    public static BigDecimal toBigDecimal(Double value){
        Assert.notNull(value,"格式化源 value 不能为空");
        BigDecimal bigDecimal=BigDecimal.valueOf(value);
        return bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 计算两个字符串的数字，相乘后得到的新值。
     * @param value1 值2
     * @param value2 值1
     * @return
     */
    public static BigDecimal toBigDecimal (String value1,String value2){
        Assert.notNull(value1,"格式化的源 value1 不能为空");
        Assert.notNull(value2,"格式化的源 value2 不能为空");
       return toBigDecimal(
               new BigDecimal(value1),
               new BigDecimal(value2)
       );
    }

    /**
     * 计算两个字符串的数字，相乘后得到的新值。
     * @param value1 值2
     * @param value2 值1
     * @return
     */
    public static BigDecimal toBigDecimal (Double value1,Double value2) {
        Assert.notNull(value1, "格式化的源 value1 不能为空");
        Assert.notNull(value2, "格式化的源 value2 不能为空");
        BigDecimal bigDecimal = BigDecimal.valueOf(value1)
                .multiply(BigDecimal.valueOf(value2));
        return bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算两个字符串的数字，相乘后得到的新值。
     * @param value1 值2
     * @param value2 值1
     * @return
     */
    public static BigDecimal toBigDecimal (BigDecimal value1,BigDecimal value2) {
        Assert.notNull(value1, "格式化的源 value1 不能为空");
        Assert.notNull(value2, "格式化的源 value2 不能为空");
        BigDecimal bigDecimal = value1
                .multiply(value2);
        return bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 将 BigDecimal 转换成 double
     * @param bigDecimal 值1
     * @return
     */
    public static double toDouble (BigDecimal bigDecimal) {
        Assert.notNull(bigDecimal, "格式化的源 bigDecimal 不能为空");
        return bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }
}
