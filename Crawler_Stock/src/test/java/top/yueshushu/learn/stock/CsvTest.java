package top.yueshushu.learn.stock;

import cn.hutool.core.text.csv.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.yueshushu.learn.stock.entity.StockHistoryCsvInfo;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @ClassName:CsvTest
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/14 10:09
 * @Version 1.0
 **/
@SpringBootTest
@Log4j2
public class CsvTest {
    /**
     * 读取csv文件的测试
     */
    @Test
    public void readCsvTest() throws Exception {
        String filePath="Z:\\code\\002415.csv";
        //2. 进行配置
        CsvReadConfig csvReadConfig=new CsvReadConfig();
        csvReadConfig.setSkipEmptyRows(true);
        csvReadConfig.setContainsHeader(true);
        //构建 CsvReader 对象
        CsvReader csvReader = CsvUtil.getReader();
        //直接读取，封装成 Bean
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
        List<StockHistoryCsvInfo> userList = csvReader.read(
               in, StockHistoryCsvInfo.class);
        for(StockHistoryCsvInfo user:userList){
            log.info(">>"+user);
        }
    }
    /**
     * 读取csv文件的测试
     */
    @Test
    public void readCsv2Test() throws FileNotFoundException {
        String filePath="Z:\\code\\399297.csv";
        //2. 进行配置
        CsvReadConfig csvReadConfig=new CsvReadConfig();
        csvReadConfig.setSkipEmptyRows(true);
        //包括标题行
        csvReadConfig.setContainsHeader(true);
        //进行了读取，设置
        //构建 CsvReader 对象
        CsvReader csvReader = CsvUtil.getReader(csvReadConfig);
        //进行读取
        CsvData csvData = csvReader.read(new File(filePath), Charset.forName("UTF-8"));
        //进行处理
        List<String> header = csvData.getHeader();
        log.info(">>>读取的文件标题头为:"+header.toString());
        //获取相应的内容
        int rowCount = csvData.getRowCount();
        log.info(">>>读取了{}行",rowCount);
        //获取行数据
        List<CsvRow> rows = csvData.getRows();
        for(CsvRow csvRow:rows){
            List<String> rawList = csvRow.getRawList();
            log.info(">>获取内容信息:"+rawList);
        }
    }
}
