package top.yueshushu.learn.stock.parse.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.model.info.DailyTradingInfo;
import top.yueshushu.learn.stock.entity.StockHistoryCsvInfo;
import top.yueshushu.learn.stock.parse.DailyTradingInfoParse;
import top.yueshushu.learn.stock.util.MyCsvUtil;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName:DefaultDailyTradingInfoParse
 * @Description 默认的每天交易信息处理器
 * @Author 岳建立
 * @Date 2021/11/10 21:15
 * @Version 1.0
 **/
@Service("defaultDailyTradingInfoParse")
public class DefaultDailyTradingInfoParse implements DailyTradingInfoParse {
    @Value("${uploadFilePath:D:/upload/}")
    private String uploadFilePath;
    @Override
    public List<StockHistoryCsvInfo> parseStockHistoryList(InputStream inputStream) {
        //定义一个默认的文件路径
        Long fileName= DateUtil.date().getTime();
        if(!(uploadFilePath.endsWith("/")||uploadFilePath.endsWith("\\"))){
            uploadFilePath=uploadFilePath+File.separator;
        }
        File downloadFile=new File(uploadFilePath+fileName+".csv");
        //将数据保存到文件里面
        FileUtil.writeFromStream(inputStream,downloadFile);
        //将文件写入进去
        List<StockHistoryCsvInfo> stockHistoryCsvInfos = null;
        try {
            stockHistoryCsvInfos = MyCsvUtil.readFile(downloadFile, StockHistoryCsvInfo.class);
            stockHistoryCsvInfos.stream().forEach(
                    n->{
                        n.setCode(n.getCode().replace("'",""));
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除文件
        downloadFile.delete();
        return stockHistoryCsvInfos;
    }
}
