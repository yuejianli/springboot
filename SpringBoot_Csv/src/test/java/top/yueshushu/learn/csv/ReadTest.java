package top.yueshushu.learn.csv;

import cn.hutool.core.text.csv.*;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.yueshushu.learn.pojo.User;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:ReadTest
 * @Description 读取Csv文件的内容
 * @Author zk_yjl
 * @Date 2021/11/8 13:49
 * @Version 1.0
 * @Since 1.0
 **/
@SpringBootTest
@Log4j2
public class ReadTest {
   /**
    * 简单文件的读取
    * @date 2021/11/8 14:15
    * @author zk_yjl
    * @param
    * @return void
    */
    @Test
    public void simpleTest(){
         //1. 读取的文件路径
        String filePath="D:\\csv\\simple.csv";
        //构建 CsvReader 对象
        CsvReader csvReader = CsvUtil.getReader();
        //进行读取
        CsvData csvData = csvReader.read(new File(filePath), Charset.forName("UTF-8"));
        //进行处理
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
    /**
     * 读取信息，读取成 CsvData 形式.
     * @date 2021/11/8 14:39
     * @author zk_yjl
     * @param
     * @return void
     */
    @Test
    public void config1Test(){
        //1. 读取的文件路径
        String filePath="D:\\csv\\simple.csv";
        //2. 进行配置
        CsvReadConfig csvReadConfig=new CsvReadConfig();
        csvReadConfig.setSkipEmptyRows(true);
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

    /**
     * 配置读取的信息
     * @date 2021/11/8 14:39
     * @author zk_yjl
     * @param
     * @return void
     */
    @Test
    public void config2Test(){
        //1. 读取的文件路径
        String filePath="D:\\csv\\simple.csv";
        //2. 进行配置
        CsvReadConfig csvReadConfig=new CsvReadConfig();
        csvReadConfig.setSkipEmptyRows(true);
        csvReadConfig.setContainsHeader(true);
        //设置最开始读取的行号, 注意，这一行，会被当成标题行的.
        csvReadConfig.setBeginLineNo(4);
        //读取的行号
        csvReadConfig.setEndLineNo(6);
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
           /* List<String> rawList = csvRow.getRawList();
            log.info(">>获取内容信息:"+rawList);*/
           log.info(">>>>");
            Map<String, String> fieldMap = csvRow.getFieldMap();
            for(Map.Entry<String,String> fieldEntry:fieldMap.entrySet()){
                log.info(">>>>>>>>:"+fieldEntry.getKey()+":"+fieldEntry.getValue());
            }
        }
    }

  /**
   * 读取信息，放置到bean 里面
   * @date 2021/11/8 14:39
   * @author zk_yjl
   * @param
   * @return void
   */
    @Test
    public void headerTest() throws Exception{
        //1. 读取的文件路径
        String filePath="D:\\csv\\header.csv";
        //进行了读取，设置
        //2. 进行配置
        CsvReadConfig csvReadConfig=new CsvReadConfig();
        csvReadConfig.setSkipEmptyRows(true);
        csvReadConfig.setContainsHeader(true);
        //构建 CsvReader 对象
        CsvReader csvReader = CsvUtil.getReader(csvReadConfig);
        //直接读取，封装成 Bean
        List<User> userList = csvReader.read(new FileReader(new File(filePath)), User.class);
        for(User user:userList){
            log.info(">>"+user);
        }
    }
}
