package top.yueshushu.learn.stock.crawler.impl;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.model.info.StockShowInfo;
import top.yueshushu.learn.stock.crawler.CrawlerService;
import top.yueshushu.learn.stock.parse.StockInfoParser;
import top.yueshushu.learn.stock.parse.StockShowInfoParse;
import top.yueshushu.learn.stock.pojo.DefaultProperties;

import java.text.MessageFormat;

/**
 * @ClassName:EasyMoneyServiceImpl
 * @Description 东方财富的相关实现
 * @Author 岳建立
 * @Date 2021/11/7 10:37
 * @Version 1.0
 **/
@Service("defaultCrawlerService")
@Log4j2
public class DefaultCrawlerServiceImpl implements CrawlerService {
    @Autowired
    private StockShowInfoParse stockShowInfoParse;
    @Autowired
    private DefaultProperties defaultProperties;
    @Override
    public StockShowInfo getNowInfo(String code) {
        //处理，拼接成信息
       String url= MessageFormat.format(defaultProperties.getShowDayUrl(),code,code.substring(2));
       log.info(">>>访问地址:"+url);
      try{
          //转换成文档
          Document document = Jsoup.connect(url).get();
          //输出文档
          Elements elements = document.select("#quote-digest > table > tbody > tr");
          //处理接下来的元素信息
          StockShowInfo stockShowInfo=stockShowInfoParse.parse(elements);
          return stockShowInfo;
      }catch (Exception e){
          e.printStackTrace();
          return null;
      }
    }
}
