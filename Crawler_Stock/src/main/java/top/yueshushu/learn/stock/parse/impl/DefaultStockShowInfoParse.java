package top.yueshushu.learn.stock.parse.impl;

import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.model.info.StockShowInfo;
import top.yueshushu.learn.stock.parse.StockShowInfoParse;
import top.yueshushu.learn.util.BigDecimalUtil;

/**
 * @ClassName:DefaultStockShowInfoParse
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/10 22:41
 * @Version 1.0
 **/
@Service("stockShowInfoParse")
@Log4j2
public class DefaultStockShowInfoParse implements StockShowInfoParse {

    @Override
    public StockShowInfo parse(Elements elements) {
        StockShowInfo result=new StockShowInfo();
        for(Element trElement:elements){
            //查询所有的td
            Elements tdElements = trElement.select("td");
            for(Element tdElement:tdElements){
                Element gt1 = tdElement.getElementById("gt1");
                if(gt1!=null){
                    log.info("输出值:"+gt1.attr("data-bind"));
                   // result.setOpeningPrice(BigDecimalUtil.toBigDecimal());
                }
                Element gt2 = tdElement.getElementById("gt2");
                if(gt2!=null){
                   // result.setClosingPrice(BigDecimalUtil.toBigDecimal(gt2.text()));
                    log.info("输出值:"+gt2.text());
                }
                Element gt3 = tdElement.getElementById("gt3");
                if(gt2!=null){
                   // result.setHighestPrice(BigDecimalUtil.toBigDecimal(gt3.text()));
                    log.info("输出值:"+gt2.text());
                }
            }
        }
        return result;
    }
}
