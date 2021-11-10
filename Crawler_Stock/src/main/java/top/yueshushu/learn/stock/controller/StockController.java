package top.yueshushu.learn.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.stock.service.StockService;

/**
 * @ClassName:StockController
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/10 21:55
 * @Version 1.0
 **/
@RestController
public class StockController {
    @Autowired
    private StockService stockService;
    /**
     * 展示这只股票现在的信息
     * @param code
     * @return
     */
    @GetMapping("/showNowInfo")
    public OutputResult showNowInfo(String code){
       return stockService.showNowInfo(code);
    }
}
