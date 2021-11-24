package top.yueshushu.learn.stock.parse;


import top.yueshushu.learn.model.info.DailyTradingInfo;
import top.yueshushu.learn.stock.entity.StockHistoryCsvInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 12905
 * 股票历史交易记录解析器
 */
public interface DailyTradingInfoParse {
    /**
     * 默认的股票历史交易记录解析器
     * @param inputStream 输入流
     * @return
     */
    public default List<StockHistoryCsvInfo> parseStockHistoryList(InputStream inputStream){
        return Collections.EMPTY_LIST;
    };


}
