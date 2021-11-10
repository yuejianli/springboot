package top.yueshushu.learn.stock.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName:EasyMoneyProperties
 * @Description 默认的配置信息
 * @Author 岳建立
 * @Date 2021/11/7 10:35
 * @Version 1.0
 **/
@Data
@Component
public class DefaultProperties implements Serializable {
    /**
     * 展示今天的股票信息
     */
    @Value("${default.show_day_url}")
    private String showDayUrl;
    /**
     * 所有的股票信息
     */
    @Value("${default.all_stock_url}")
    private String allStockUrl;
    /**
     * 股票的历史url
     */
    @Value("${default.stock_history_url}")
    private String stockHistoryUrl;
}
