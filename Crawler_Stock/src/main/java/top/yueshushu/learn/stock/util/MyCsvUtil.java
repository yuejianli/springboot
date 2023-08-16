package top.yueshushu.learn.stock.util;

import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import top.yueshushu.learn.stock.entity.StockHistoryCsvInfo;

import java.io.*;
import java.util.List;

/**
 * @ClassName:MyCsvUtil
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/14 10:42
 * @Version 1.0
 **/
public class MyCsvUtil {
    public static <T> List<T>readFile(InputStream inputStream, Class<T> clazz ) throws Exception{
        //2. 进行配置
        CsvReadConfig csvReadConfig=new CsvReadConfig();
        csvReadConfig.setSkipEmptyRows(true);
        csvReadConfig.setContainsHeader(true);
        //构建 CsvReader 对象
        CsvReader csvReader = CsvUtil.getReader();
        //直接读取，封装成 Bean
        return csvReader.read(
                new InputStreamReader(inputStream, "GBK"), clazz);
    }

    public static List<StockHistoryCsvInfo> readFile(File downloadFile, Class<StockHistoryCsvInfo> clazz)
    throws Exception{
        //2. 进行配置
        CsvReadConfig csvReadConfig=new CsvReadConfig();
        csvReadConfig.setSkipEmptyRows(true);
        csvReadConfig.setContainsHeader(true);
        //构建 CsvReader 对象
        CsvReader csvReader = CsvUtil.getReader();
        //直接读取，封装成 Bean
        return csvReader.read(
                new InputStreamReader(
                        new FileInputStream(downloadFile), "GBK"), clazz);

    }
}
