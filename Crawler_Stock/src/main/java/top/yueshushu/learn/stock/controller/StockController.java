package top.yueshushu.learn.stock.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.ro.stock.StockRo;
import top.yueshushu.learn.stock.service.CrawlerStockService;
import top.yueshushu.learn.stock.service.StockHistoryService;
import top.yueshushu.learn.stock.service.StockService;

/**
 *
 *
 * @author yjl
 * @since 2021-11-13 22:36:35
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private CrawlerStockService crawlerStockService;
    @Autowired
    private StockService stockService;

    @Autowired
    private StockHistoryService stockHistoryService;

    @PostMapping("/getStockInfo")
    public OutputResult getStockInfo(@RequestBody StockRo stockRo){
        return crawlerStockService.getStockInfo(stockRo);
    }
    @PostMapping("/getStockKline")
    public OutputResult getStockKline(@RequestBody StockRo stockRo){
        return crawlerStockService.getStockKline(stockRo);
    }
    @PostMapping("/list")
    public OutputResult list(@RequestBody StockRo stockRo){
        return stockService.list(stockRo);
    }
    @PostMapping("/stockAsync")
    public OutputResult stockAsync(@RequestBody StockRo stockRo){
        return crawlerStockService.stockAsync(stockRo);
    }

    /*关于历史记录的处理*/
    @PostMapping("/stockHistoryAsync")
    public OutputResult stockHistoryAsync(@RequestBody StockRo stockRo){
        return crawlerStockService.stockHistoryAsync(stockRo);
    }
    @PostMapping("/getStockHistory")
    public OutputResult getStockHistory(@RequestBody StockRo stockRo){
        PageHelper.startPage(stockRo.getPageNum(),stockRo.getPageSize());
        return stockHistoryService.getStockHistory(stockRo);
    }

}