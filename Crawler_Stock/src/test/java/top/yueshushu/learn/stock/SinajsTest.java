package top.yueshushu.learn.stock;

import cn.hutool.core.io.FileUtil;
import com.sun.imageio.plugins.common.ImageUtil;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import top.yueshushu.learn.model.info.StockShowInfo;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.ro.stock.StockRo;
import top.yueshushu.learn.stock.crawler.CrawlerService;
import top.yueshushu.learn.stock.entity.DownloadStockInfo;
import top.yueshushu.learn.stock.entity.StockHistoryCsvInfo;
import top.yueshushu.learn.stock.service.CrawlerStockService;
import top.yueshushu.learn.stock.service.StockService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName:SinajsTest
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/7 15:50
 * @Version 1.0
 **/
@SpringBootTest
@Log4j2
public class SinajsTest {
    @Autowired
    private CrawlerStockService crawlerStockService;

    @Resource(name="defaultCrawlerService")
    private CrawlerService crawlerService;
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 当前股票信息展示接口
     */
    @Test
    public void showInfoTest(){
        String code="sz002415";
        StockRo stockRo=new StockRo();
        stockRo.setCode(code);
        OutputResult<StockShowInfo> stockShowInfoOutputResult = crawlerStockService.showNowInfo(stockRo);
        System.out.println(stockShowInfoOutputResult.getData().getOrDefault("result",new StockShowInfo()));
    }

    @Test
    public void minTest(){
        String code="sz002415";
        String content=crawlerService.getMinUrl(code);
        log.info(">>输出内容:"+content);
    }
    @Test
    public void getStockListTest(){
        List<DownloadStockInfo> stockList = crawlerService.getStockList();
        List<DownloadStockInfo> downloadStockInfos = stockList.subList(0, 10);
        downloadStockInfos.stream().forEach(
                n->{
                    log.info(n);
                }
        );
    }
    @Test
    public void restStockListTest(){
        String forObject = restTemplate.getForObject("https://20.push2.eastmoney" +
                        ".com/api/qt/clist/get?pn=1&f13=1&pz=10000000&np=1&fid=f3" +
                        "&fields=f12,f13,f14,f45&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23,b:MK0021,b:MK0022," +
                        "b:MK0023,b:MK0024",
                String.class);
        log.info(">>>输出值:"+forObject.substring(0,100));
    }

    @Test
    public void parseDailyIndexListTest(){
        List<StockHistoryCsvInfo> dailyIndex = crawlerService.parseStockHistoryList(
                1+"002415","20210901","20211112"
        );
        dailyIndex.forEach(
                n->{
                    log.info(n);
                }
        );
    }
}

