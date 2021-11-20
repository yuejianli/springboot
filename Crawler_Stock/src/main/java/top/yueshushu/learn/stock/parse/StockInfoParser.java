package top.yueshushu.learn.stock.parse;
import org.springframework.util.CollectionUtils;
import top.yueshushu.learn.model.info.StockInfo;
import top.yueshushu.learn.stock.entity.DownloadStockInfo;
import top.yueshushu.learn.stock.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 股票信息转换接口
 */
public interface StockInfoParser {
    /**
     * 将content 信息转换成对应的股票实体信息
     * @param content
     * @return
     */
    public default List<DownloadStockInfo> parseStockInfoList(String content){
        return Collections.EMPTY_LIST;
    };
}
