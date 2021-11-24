package top.yueshushu.learn.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.yueshushu.learn.enumtype.SyncStockHistoryType;
import top.yueshushu.learn.model.info.StockInfo;
import top.yueshushu.learn.model.info.StockShowInfo;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.ro.stock.StockRo;
import top.yueshushu.learn.service.StockService;

import java.util.Date;

/**
 * @ClassName:StockServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/12 23:07
 * @Version 1.0
 **/
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${restUrl.crawlerUrl}")
    private String crawlerUrl;
    @Override
    public OutputResult<StockInfo> list(StockRo stockRo) {
        String url= crawlerUrl+"list";
        return restTemplate.postForEntity(
               url,stockRo,
                OutputResult.class
        ).getBody();
    }

    @Override
    public OutputResult<StockShowInfo> getStockInfo(StockRo stockRo) {
        String url= crawlerUrl+"getStockInfo";
        return restTemplate.postForEntity(
                url,stockRo,
                OutputResult.class
        ).getBody();
    }

    @Override
    public OutputResult<String> getStockKline(StockRo stockRo) {
        String url= crawlerUrl+"getStockKline";
        return restTemplate.postForEntity(
                url,stockRo,
                OutputResult.class
        ).getBody();
    }

    @Override
    public OutputResult<String> stockAsync(StockRo stockRo) {
        String url= crawlerUrl+"stockAsync";
        return restTemplate.postForEntity(
                url,stockRo,
                OutputResult.class
        ).getBody();
    }

    @Override
    public OutputResult stockHistoryAsync(StockRo stockRo) {
        //处理日期信息
        OutputResult handlerResult=handlerDate(stockRo);
        if(null!=handlerResult){
            return handlerResult;
        }
        String url= crawlerUrl+"stockHistoryAsync";
        return restTemplate.postForEntity(
                url,stockRo,
                OutputResult.class
        ).getBody();
    }



    @Override
    public OutputResult history(StockRo stockRo) {
        if(StringUtils.isBlank(stockRo.getCode())){
            return OutputResult.success();
        }
        String url= crawlerUrl+"getStockHistory";
        return restTemplate.postForEntity(
                url,stockRo,
                OutputResult.class
        ).getBody();
    }

    /**
     * 历史交易信息同步时，处理日期.
     * @param stockRo
     */
    private OutputResult handlerDate(StockRo stockRo) {
        SyncStockHistoryType syncRangeType = SyncStockHistoryType.getSyncRangeType(stockRo.getType());
        if(syncRangeType==null){
            return OutputResult.alert("不支持的同步交易范围");
        }
        final String Date_formatter="yyyyMMdd";
        Date now=DateUtil.date();
        String startDate=DateUtil.format(
                now,Date_formatter
        );
        String endDate=DateUtil.format(
                now,Date_formatter
        );;
        switch (syncRangeType){
            case SELF:{
               startDate= DateUtil.format(
                        DateUtil.parse(
                                stockRo.getStartDate(),
                                "yyyy-MM-dd HH:mm:ss"
                        )
                ,"yyyy-MM-dd");
                endDate= DateUtil.format(
                        DateUtil.parse(
                                stockRo.getEndDate(),
                                "yyyy-MM-dd HH:mm:ss"
                        )
                        ,Date_formatter);
                break;
            }
            case WEEK:{
                DateTime dateTime = DateUtil.offsetWeek(now, -1);
                startDate=DateUtil.format(dateTime,Date_formatter);
                break;
            }
            case MONTH:{
                DateTime dateTime = DateUtil.offsetMonth(now, -1);
                startDate=DateUtil.format(dateTime,Date_formatter);
                break;
            }
            case YEAR:{
                DateTime dateTime = DateUtil.offsetMonth(now, -1*12);
                startDate=DateUtil.format(dateTime,Date_formatter);
                break;
            }
            case THREE_YEAR:{
                DateTime dateTime = DateUtil.offsetMonth(now, -1*12*3);
                startDate=DateUtil.format(dateTime,Date_formatter);
                break;
            }
            case FIVE_YEAR:{
                DateTime dateTime = DateUtil.offsetMonth(now, -1*12*5);
                startDate=DateUtil.format(dateTime,Date_formatter);
                break;
            }
            case TEN_YEAR:{
                DateTime dateTime = DateUtil.offsetMonth(now, -1*12*10);
                startDate=DateUtil.format(dateTime,Date_formatter);
                break;
            }
            case ALL:{
               // 1984年11月18日 中国第一个股票交易
                startDate="19841118";
                break;
            }
        }
        stockRo.setStartDate(startDate);
        stockRo.setEndDate(endDate);
        return null;
    }
}
