package top.yueshushu.learn.stock.properties;

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
     * 展示今天的股票信息,接口方式
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
    /**
     * 下载股票的历史记录
     */
    @Value("${default.stock_history_download_url}")
    private String stockHistoryDownloadUrl;
    /**
     * 分钟的K线图
     */
    @Value("${default.min_url}")
    private String minUrl;
    /**
     * 天的K线图
     */
    @Value("${default.daily_url}")
    private String dailyUrl;
    /**
     * 周的K线图
     */
    @Value("${default.weekly_url}")
    private String weeklyUrl;
    /**
     * 月的K线图
     */
    @Value("${default.monthly_url}")
    private String monthlyUrl;

}
