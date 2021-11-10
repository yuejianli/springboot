package top.yueshushu.learn.stock.parse;


import top.yueshushu.learn.model.info.DailyTradingInfo;

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
     * @param content
     * @return
     */
    public default List<DailyTradingInfo> parseDailyIndexList(String content){
        return Collections.EMPTY_LIST;
    };


}
