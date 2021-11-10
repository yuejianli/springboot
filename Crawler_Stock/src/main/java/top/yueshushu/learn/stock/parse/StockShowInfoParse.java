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
    StockShowInfo parse(Elements elements);
}
