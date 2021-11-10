package top.yueshushu.learn.stock.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.util.StringUtils;
import top.yueshushu.learn.stock.enumtype.Exchange;
import top.yueshushu.learn.stock.enumtype.StockType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class StockUtil {

    private static final List<String> CODES_SH_A = Arrays.asList("600", "601", "603", "605", "688", "689");
    private static final List<String> CODES_SH_INDEX = Arrays.asList("000001");
    private static final List<String> CODES_SH_ETF = Arrays.asList("51", "56", "58");

    private static final List<String> CODES_SZ_A = Arrays.asList("000", "001", "002", "003", "004", "300", "301");
    private static final List<String> CODES_SZ_INDEX = Arrays.asList("399001", "399006");
    private static final List<String> CODES_SZ_ETF = Arrays.asList("15");

    private StockUtil() {
    }

    private static String getExchange(String code) {
        if (!StringUtils.hasLength(code)) {
            return null;
        }
        if (isCodeStart(code, CODES_SH_A, CODES_SH_ETF)) {
            return Exchange.SH.getName();
        }
        if (isCodeStart(code, CODES_SZ_A, CODES_SZ_ETF)) {
            return Exchange.SZ.getName();
        }
        return null;
    }

    public static String getFullCode(String code) {
        if (!StringUtils.hasLength(code)) {
            return null;
        }
        String exchange = StockUtil.getExchange(code);
        if (exchange == null) {
            return code;
        }
        return exchange + code;
    }

    public static int getStockType(String exchange, String code) {
        if (exchange == null) {
            exchange = StockUtil.getExchange(code);
        }
        if (Exchange.valueOfName(exchange).isSh()) {
            if (CODES_SH_INDEX.contains(code)) {
                return StockType.Index.value();
            }
        } else {
            if (CODES_SZ_INDEX.contains(code)) {
                return StockType.Index.value();
            }
        }
        if (isCodeStart(code, CODES_SH_A, CODES_SZ_A)) {
            return StockType.A.value();
        }
        if (isCodeStart(code, CODES_SH_ETF, CODES_SZ_ETF)) {
            return StockType.ETF.value();
        }
        throw new NoSuchElementException("no stock type exchange " + exchange + ", code " + code);
    }

    public static String getPinyin(String name) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder sb = new StringBuilder();
        for (char ch : name.toLowerCase().toCharArray()) {
            if (ch == '*') {
                continue;
            }
            if (ch > 31 && ch < 127) {
                sb.append(ch);
            } else if (ch == '行') {
                sb.append('h');
            } else {
                try {
                    String[] arr = PinyinHelper.toHanyuPinyinStringArray(ch, defaultFormat);
                    if (arr == null) {
                        throw new RuntimeException("not support character " + name);
                    }
                    sb.append(arr[0].charAt(0));
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    throw new RuntimeException("get pinyin error", e);
                }
            }
        }
        return sb.toString();
    }

    public static BigDecimal calcIncreaseRate(BigDecimal a, BigDecimal b) {
        return DecimalUtil.div(DecimalUtil.sub(a, b), b);
    }

    public static boolean isOriName(String name) {
        for (String namePrefix : Arrays.asList("N", "XD", "XR", "DR")) {
            if (name.startsWith(namePrefix)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCodeStart(String code, List<String> list) {
        return list.stream().anyMatch(code::startsWith);
    }

    private static boolean isCodeStart(String code, List<String> list01, List<String> list02) {
        return isCodeStart(code, list01) || isCodeStart(code, list02);
    }

}