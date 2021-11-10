package top.yueshushu.learn.stock.service;

import top.yueshushu.learn.model.info.StockShowInfo;
import top.yueshushu.learn.response.OutputResult;

/**
 * @ClassName:StockService
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/10 22:30
 * @Version 1.0
 **/
public interface StockService {
    /**
     * 展示股票现在的信息
     * @param code 股票的代码
     * @return
     */
    OutputResult<StockShowInfo> showNowInfo(String code);
}
