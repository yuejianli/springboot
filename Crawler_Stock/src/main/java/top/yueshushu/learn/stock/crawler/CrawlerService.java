package top.yueshushu.learn.stock.crawler;
import top.yueshushu.learn.model.info.DailyTradingInfo;
import top.yueshushu.learn.model.info.StockInfo;
import top.yueshushu.learn.model.info.StockShowInfo;
import java.util.Collections;
import java.util.List;

/**
 * @InterfaceName CrawlerService
 * @Description 爬虫的相关service接口
 * @Author 岳建立
 * @Date 2021/11/7 10:37
 * @Version 1.0
 **/
public interface CrawlerService {

    public default List<StockInfo> getStockList(){
        return Collections.EMPTY_LIST;
    };

    public default List<DailyTradingInfo> getDailyIndex(String code){
        return Collections.EMPTY_LIST;
    }

    public default StockShowInfo getNowInfo(String code){return new StockShowInfo();}
}
