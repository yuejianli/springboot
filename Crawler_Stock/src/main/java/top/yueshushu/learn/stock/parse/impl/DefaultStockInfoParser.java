package top.yueshushu.learn.stock.parse.impl;

import org.springframework.stereotype.Component;
import top.yueshushu.learn.model.info.StockInfo;
import top.yueshushu.learn.stock.parse.StockInfoParser;

import java.util.List;

/**
 * 股票转换信息实现
 * @author 12905
 */
@Component("defaultStockInfoParser")
public class DefaultStockInfoParser implements StockInfoParser {

    @Override
    public List<StockInfo> parseStockInfoList(String content) {
        return null;
    }
}
