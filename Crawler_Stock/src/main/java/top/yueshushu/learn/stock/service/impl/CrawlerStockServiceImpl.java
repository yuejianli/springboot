package top.yueshushu.learn.stock.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.yueshushu.learn.enumtype.KType;
import top.yueshushu.learn.model.info.DailyTradingInfo;
import top.yueshushu.learn.model.info.StockInfo;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.response.ResultCode;
import top.yueshushu.learn.ro.stock.StockRo;
import top.yueshushu.learn.stock.crawler.CrawlerService;
import top.yueshushu.learn.stock.entity.DownloadStockInfo;
import top.yueshushu.learn.stock.entity.StockHistoryCsvInfo;
import top.yueshushu.learn.stock.pojo.Stock;
import top.yueshushu.learn.stock.pojo.StockHistory;
import top.yueshushu.learn.stock.service.CrawlerStockService;
import top.yueshushu.learn.stock.service.StockHistoryService;
import top.yueshushu.learn.stock.service.StockService;
import top.yueshushu.learn.stock.util.StockUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 股票信息基本表(Stock)表服务实现类
 *
 * @author yjl
 * @since 2021-11-13 22:36:34
 */
@Service("crawlerStockService")
@Log4j2
public class CrawlerStockServiceImpl implements CrawlerStockService {
    @Autowired
    private CrawlerService crawlerService;
    @Autowired
    private StockService stockService;
    @Autowired
    private StockHistoryService stockHistoryService;

    @Override
    public OutputResult getStockInfo(StockRo stockRo) {
        Stock stock=stockService.selectByCode(stockRo.getCode());
        if(stock==null){
            return OutputResult.error(ResultCode.STOCK_CODE_ERROR);
        }
        return OutputResult.success(crawlerService.getNowInfo(stock.getFullCode()));
    }
    @Override
    public OutputResult getStockKline(StockRo stockRo) {
        //获取类型
        KType kType = KType.getKType(stockRo.getType());
        if(kType==null){
            kType=KType.MIN;
        }
        Stock stock=stockService.selectByCode(stockRo.getCode());
        if(stock==null){
            return OutputResult.error(ResultCode.STOCK_CODE_ERROR);
        }
        String result="";
        switch (kType){
            case MIN:{
                result=crawlerService.getMinUrl(stock.getFullCode());
                break;
            }
            case DAY:{
                result=crawlerService.getDayUrl(stock.getFullCode());
                break;
            }
            case WEEK:{
                result=crawlerService.getWeekUrl(stock.getFullCode());
                break;
            }
            case MONTH:{
                result=crawlerService.getMonthUrl(stock.getFullCode());
                break;
            }
            default:{
                break;
            }
        }

        return OutputResult.success(result);
    }

    @Override
    public OutputResult showNowInfo(StockRo stockRo) {
        return OutputResult.success(crawlerService.getNowInfo(stockRo.getCode()));
    }
    @Override
    public OutputResult stockAsync(StockRo stockRo) {
        //时间计数器
        TimeInterval timer = DateUtil.timer();
        timer.start();
        List<DownloadStockInfo> downloadStockInfoList = crawlerService.getStockList();
        if(CollectionUtils.isEmpty(downloadStockInfoList)){
            return OutputResult.error(ResultCode.STOCK_ASYNC_FAIL);
        }
        log.info(">>获取网络股票信息并转换使用时间:{}",timer.intervalMs());
        //进行删除
        QueryWrapper queryWrapper=new QueryWrapper();
        stockService.remove(queryWrapper);
        //进行批量保存
        List<Stock> stockList=new ArrayList<>();
        downloadStockInfoList.stream().forEach(
                n->{
                    Stock stock=new Stock();
                    BeanUtils.copyProperties(n,stock);
                    stock.setFlag(1);
                    stockList.add(stock);
                }
        );
        stockService.saveBatch(stockList,1000);
        log.info("同步信息到数据库共用时 {}",timer.intervalMs());
        return OutputResult.success(ResultCode.STOCK_ASYNC_SUCCESS);
    }

    @Override
    public OutputResult stockHistoryAsync(StockRo stockRo) {
        Date now=DateUtil.date();
        if(StringUtils.isEmpty(stockRo.getStartDate())){
            //往前推一年
            Date beforeDate=DateUtil.offsetMonth(now,
                    -12);
            stockRo.setStartDate(
                    DateUtil.format(
                            beforeDate,
                            "yyyyMMdd"
                    )
            );

        }
        if(StringUtils.isEmpty(stockRo.getEndDate())){
            stockRo.setEndDate(
                    DateUtil.format(
                            now,
                            "yyyyMMdd"
                    )
            );
        }

        //时间计数器
        TimeInterval timer = DateUtil.timer();
        timer.start();
        //处理读取数据
        List<StockHistoryCsvInfo> stockHistoryCsvInfoList = crawlerService.parseStockHistoryList(
                stockRo.getExchange()+stockRo.getCode(),
                stockRo.getStartDate(), stockRo.getEndDate());
        if(CollectionUtils.isEmpty(stockHistoryCsvInfoList)){
            return OutputResult.error(ResultCode.STOCK_HIS_ASYNC_FAIL);
        }
        log.info(">>获取{}历史信息并转换使用时间:{},转换了{}条",stockRo.getCode(),timer.intervalMs(),
                stockHistoryCsvInfoList.size());
        //进行修订数据，TODO yjl 需要去做的
        //进行删除
        stockHistoryService.deleteAsyncData(stockRo);
        //进行批量保存
        List<StockHistory> stockHistoryList=new ArrayList<>();
        stockHistoryCsvInfoList.stream().forEach(
                n->{
                    StockHistory dailyTradingInfo=new StockHistory();
                    BeanUtils.copyProperties(n,dailyTradingInfo,"currDate");
                    dailyTradingInfo.setCurrDate(
                            DateUtil.parseDate(
                                   n.getCurrDate()
                            )
                    );
                    stockHistoryList.add(dailyTradingInfo);
                }
        );
        stockHistoryService.saveBatch(stockHistoryList,100);
        log.info("同步信息到数据库共用时 {}",timer.intervalMs());
        return OutputResult.success(ResultCode.STOCK_HIS_ASYNC_SUCCESS);
    }
}