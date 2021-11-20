package top.yueshushu.learn.stock.service;

import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.ro.stock.StockRo;

/**
 *
 *
 * @author yjl
 * @since 2021-11-13 22:36:32
 */
public interface CrawlerStockService {

    OutputResult getStockInfo(StockRo stockRo);

    OutputResult getStockKline(StockRo stockRo);

    OutputResult showNowInfo(StockRo stockRo);

    /**
     * 同步
     * @param stockRo
     * @return
     */
    OutputResult stockAsync(StockRo stockRo);

    /**
     * 获取历史同步信息
     * @param stockRo
     * @return
     */
    OutputResult stockHistoryAsync(StockRo stockRo);
}