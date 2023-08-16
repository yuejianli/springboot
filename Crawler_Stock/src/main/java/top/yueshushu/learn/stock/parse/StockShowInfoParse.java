package top.yueshushu.learn.stock.parse;

import org.jsoup.select.Elements;
import top.yueshushu.learn.model.info.StockShowInfo;

/**
 * @ClassName:StockShowInfoParse
 * @Description 展示股票今天的信息处理
 * @Author 岳建立
 * @Date 2021/11/10 22:40
 * @Version 1.0
 **/
public interface StockShowInfoParse {
    /**
     * 解析元素信息
     * @param elements
     * @return
     */
    default StockShowInfo parse(Elements elements){
        return new StockShowInfo();
    };

    /**
     * 将接口内容转换成股票展示信息，是接口方式
     * @param content
     * @return
     */
    default StockShowInfo parse(String content){
        return new StockShowInfo();
    };
}
