package top.yueshushu.learn.stock.parse.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.model.info.StockShowInfo;
import top.yueshushu.learn.stock.parse.StockShowInfoParse;
import top.yueshushu.learn.stock.util.MyDateUtil;
import top.yueshushu.learn.util.BigDecimalUtil;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:DefaultStockShowInfoParse
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/10 22:41
 * @Version 1.0
 **/
@Service("defaultStockShowInfoParse")
@Log4j2
public class DefaultStockShowInfoParse implements StockShowInfoParse {

    private static Map<Integer,String> showInfoBindMap;
    static {
        showInfoBindMap=new HashMap<>();
        showInfoBindMap.put(1,"openingPrice");
        showInfoBindMap.put(2,"yesClosingPrice");
        showInfoBindMap.put(3,"nowPrice");
        showInfoBindMap.put(4,"highestPrice");
        showInfoBindMap.put(5,"lowestPrice");
        showInfoBindMap.put(8,"tradingVolume");
        showInfoBindMap.put(9,"tradingValue");
    }
    @Override
    public StockShowInfo parse(Elements elements) {
        StockShowInfo result=new StockShowInfo();
        for(Element trElement:elements){
            //查询所有的td
            Elements tdElements = trElement.select("td");
            for(Element tdElement:tdElements){
                Element gt1 = tdElement.getElementById("gt1");
                if(gt1!=null){
                    log.info("输出值:"+gt1.attr("data-bind"));
                   // result.setOpeningPrice(BigDecimalUtil.toBigDecimal());
                }
                Element gt2 = tdElement.getElementById("gt2");
                if(gt2!=null){
                   // result.setClosingPrice(BigDecimalUtil.toBigDecimal(gt2.text()));
                    log.info("输出值:"+gt2.text());
                }
                Element gt3 = tdElement.getElementById("gt3");
                if(gt2!=null){
                   // result.setHighestPrice(BigDecimalUtil.toBigDecimal(gt3.text()));
                    log.info("输出值:"+gt2.text());
                }
            }
        }
        return result;
    }

    /**
     * 如 接口访问获取的信息:
     * var hq_str_sz002415="海康威视,52.210,52.200,52.050,52.690,51.550,52.050,
     * 52.060,29357010,1531414342.260,
     * 31243,52.050,54800,52.040,4700,52.030,5700,52.020,
     * 5600,
     * 52.010,1600,52.060,
     * 14800,52.070,27400,52.080,1800,52.090,4500,52.100,
     * 2021-11-10,15:00:03,00";
     *
     * 东方财富展示的信息如下:
     * 今开：	52.21	最高：	52.69	涨停：	57.42	换手：	0.36%	成交量：	29.36万手	市盈(动)：	33.24	总市值：	4859亿
     * 昨收：	52.20	最低：	51.55	跌停：	46.98	量比：	1.18	成交额：	15.31亿	市净：	8.45	流通市值：	4238亿
     *
     *对应的信息是:
     * 0：”海康威视”，股票名字；
     * 1：”52.210″，今日开盘价；
     * 2：”52.200″，昨日收盘价；
     * 3：”52.050″，当前价格；
     * 4：”52.690″，今日最高价；
     * 5：”51.550″，今日最低价；
     * 6：”52.050″，竞买价，即“买一”报价；
     * 7：”52.060″，竞卖价，即“卖一”报价；
     * 8：”29357010″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
     * 9：”1531414342.260″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
     * 10：”31243″，“买一”申请4695股；
     * 11：”52.050″，“买一”报价；
     * 12：”54800″，“买二”
     * 13：”52.040″，“买二”
     * 14：”4700″，“买三”
     * 15：”52.030″，“买三”
     * 16：”5700″，“买四”
     * 17：”52.020″，“买四”
     * 18：”5600″，“买五”
     * 19：”52.010″，“买五”
     * 20：”1600″，“卖一”申报3100股，即31手；
     * 21：”52.060″，“卖一”报价
     * (22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
     * 30：”2021-11-10″，日期；
     * 31：”15:00:03,00″，时间；
     *
     *
     * @param content
     * @return
     */
    @Override
    public StockShowInfo parse(String content) {
        StockShowInfo result=new StockShowInfo();

        String[] strs = content.split(",");
        if (strs.length <= 1) {
            return null;
        }
        //定义一个位置和属性值的对应关系 Map
        //获取处理  code
        String code = strs[0].substring(strs[0].lastIndexOf('_') + 1, strs[0].lastIndexOf('='));
        //后面跟的是 " 号，去掉 " 号
        String name = strs[0].replace("\"","").substring(strs[0].lastIndexOf('=')+1);
        result.setCode(code);
        result.setName(name);
        //处理map 里面的信息
        for(Map.Entry<Integer,String> bindEntry:showInfoBindMap.entrySet()){
            //进行处理
            //获取该值，都为数字类型
            BigDecimal bigDecimal=BigDecimalUtil.toBigDecimal(strs[bindEntry.getKey()]);
            //获取该属性
            String property=bindEntry.getValue();
            try {
                BeanUtils.setProperty(result,property,bigDecimal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        /*如果当前时间在下午三点之后*/
        if(!MyDateUtil.between930And15()){
            result.setClosingPrice(result.getNowPrice());
        }
        result.setAmplitude(BigDecimalUtil.subBigDecimal(
            result.getNowPrice(),result.getOpeningPrice()
        ));
        result.setAmplitudeProportion(
                BigDecimalUtil.divPattern(
                        result.getAmplitude(),
                        result.getOpeningPrice()
                )
        );

        //处理一些其他的信息点
        String dateStr=strs[strs.length-3]+" "+strs[strs.length-2];
        result.setDate(dateStr.replace("\"",""));

        return result;
    }



}
