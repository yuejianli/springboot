package top.yueshushu.learn.stock.util;

import java.math.BigDecimal;

public class DecimalUtil {

    private DecimalUtil() {
    }

    public static BigDecimal sub(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    public static BigDecimal div(BigDecimal a, BigDecimal b) {
        return DecimalUtil.div(a, b, 6);
    }

    public static BigDecimal div(BigDecimal a, BigDecimal b, int scale) {
        return a.divide(b, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static boolean bg(BigDecimal a, BigDecimal b) {
        return DecimalUtil.comprare(a, b) > 0;
    }

    public static boolean ls(BigDecimal a, BigDecimal b) {
        return DecimalUtil.comprare(a, b) < 0;
    }

    public static int comprare(BigDecimal a, BigDecimal b) {
        return a.compareTo(b);
    }

    public static BigDecimal fromStr(String value) {
        value = value.trim();
        value = value.replace(",", "");
        return new BigDecimal(value);
    }

}
