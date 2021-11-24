package top.yueshushu.learn.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.ro.stock.StockRo;
import top.yueshushu.learn.stock.pojo.StockHistory;

/**
 * @ClassName:StockHistoryService
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/14 11:24
 * @Version 1.0
 **/
public interface StockHistoryService extends IService<StockHistory> {
    public OutputResult getStockHistory(StockRo stockRo);

    /**
     * 删除这个范围内的数据
     * @param stockRo
     */
    void deleteAsyncData(StockRo stockRo);
}
