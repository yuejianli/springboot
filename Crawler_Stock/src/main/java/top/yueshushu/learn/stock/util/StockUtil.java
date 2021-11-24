package top.yueshushu.learn.stock.util;

import net.sf.jsqlparser.statement.execute.Execute;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import top.yueshushu.learn.enumtype.ExchangeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:StockUtil
 * @Description 股票的相关工具类
 * @Author 岳建立
 * @Date 2021/11/13 21:21
 * @Version 1.0
 **/
public class StockUtil {
    private static List<String> shList;
    private static List<String> shThreeList;
    static{
        shList= Arrays.asList("5","6","9");
        shThreeList=Arrays.asList("009","126","110","201","202","203","204");
    }

    public static String getFullCode(Integer type,String code){
        Assert.notNull(type,"类型不能为空");
        Assert.notNull(code,"股票代码不能为空");
        ExchangeType exchangeType = ExchangeType.getExchangeType(type);
        if(exchangeType==null){
            return code;
        }
        return exchangeType.getDesc()+code;
    }
    public static String getFullCode(String stockCode){
        if (StringUtils.isBlank(stockCode)||stockCode.length()<3) {
           return stockCode;
        }
        String one = stockCode.substring(0, 1);
        String three = stockCode.substring(0, 3);
        if (shList.contains(one)) {
            return ExchangeType.SH.getDesc()+stockCode;
        } else {
            if (shThreeList.contains(three)) {
                return ExchangeType.SH.getDesc()+stockCode;
            } else {
                return ExchangeType.SZ.getDesc()+stockCode;
            }
        }
    }
    public static Integer convertExchange(Integer exchange){
        Assert.notNull(exchange,"类型不能为空");
        if(ExchangeType.SZ.getCode().equals(exchange)){
            return ExchangeType.SH.getCode();
        }
        if(ExchangeType.SH.getCode().equals(exchange)){
            return ExchangeType.SZ.getCode();
        }
        return ExchangeType.OTHER.getCode();
    }


}
