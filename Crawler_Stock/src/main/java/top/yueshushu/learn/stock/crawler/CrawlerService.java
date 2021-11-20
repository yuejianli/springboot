package top.yueshushu.learn.stock.crawler;
import top.yueshushu.learn.model.info.DailyTradingInfo;
import top.yueshushu.learn.model.info.StockInfo;
import top.yueshushu.learn.model.info.StockShowInfo;
import top.yueshushu.learn.stock.entity.DownloadStockInfo;
import top.yueshushu.learn.stock.entity.StockHistoryCsvInfo;

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

    public default List<DownloadStockInfo> getStockList(){
        return Collections.EMPTY_LIST;
    };

    public default List<StockHistoryCsvInfo> parseStockHistoryList(String code,
                                                           String startDate,
                                                           String endDate){
        return Collections.EMPTY_LIST;
    }

    public default StockShowInfo getNowInfo(String code){return new StockShowInfo();}

    public default String getMinUrl(String code){
        return "";
    }

    public default String getDayUrl(String code){
        return "";
    }

    public default String getWeekUrl(String code){
        return "";
    }

    public default String getMonthUrl(String code){
        return "";
    }
}
