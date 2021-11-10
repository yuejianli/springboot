package top.yueshushu.learn.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.model.info.StockShowInfo;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.stock.crawler.CrawlerService;
import top.yueshushu.learn.stock.service.StockService;

/**
 * @ClassName:StockServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/10 22:30
 * @Version 1.0
 **/
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private CrawlerService crawlerService;
    @Override
    public OutputResult<StockShowInfo> showNowInfo(String code) {
        //处理code 信息
       StockShowInfo stockShowInfo= crawlerService.getNowInfo(code);
       return OutputResult.success(stockShowInfo);
    }
}
