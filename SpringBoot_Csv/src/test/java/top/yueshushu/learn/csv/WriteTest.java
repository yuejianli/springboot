package top.yueshushu.learn.csv;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.util.DataUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @ClassName:WriteTest
 * @Description 写入 Csv 文件信息
 * @Author zk_yjl
 * @Date 2021/11/8 13:50
 * @Version 1.0
 * @Since 1.0
 **/
@SpringBootTest
@Log4j2
public class WriteTest {

    /**
     * 简单的 csv文件写入
     * @date 2021/11/8 13:51
     * @author zk_yjl
     * @param
     * @return void
     */
    @Test
    public void simpleWriteTest() throws Exception{
        //1. 大批量的业务处理，最后获取组装相应的数据信息。
        List<User> userList= DataUtil.createDataList("两个蝴蝶飞");
        //2. 存储的文件地址
        String filePath="D:\\csv\\simple.csv";
        //获取 CsvWriter
        CsvWriter csvWriter = CsvUtil.getWriter(filePath, Charset.forName("UTF-8"));
        // 写入注释
        csvWriter.writeComment("一个简单的csv文件");
        //写入新的一行
        csvWriter.writeLine();
        //写入内容
        csvWriter.writeBeans(userList);
        //关闭内容
        csvWriter.close();
        log.info("写入文件成功");
    }
    /**
     * 写入标题头文件
     * @date 2021/11/8 14:06
     * @author zk_yjl
     * @param
     * @return void
     */
    @Test
    public void headerTest() throws Exception{
        //1. 大批量的业务处理，最后获取组装相应的数据信息。
        List<User> userList= DataUtil.createDataList("两个蝴蝶飞");
        //2. 存储的文件路径
        String filePath="D:\\csv\\header.csv";
        //3. 获取CsvWriter
        CsvWriter csvWriter = CsvUtil.getWriter(filePath, Charset.forName("UTF-8"));
        //4. 写入注入
        csvWriter.writeComment("带着自定义标题头的文件");
        //写入内容
        csvWriter.writeBeans(userList);
        //关闭
        csvWriter.close();
        log.info(">>写入标题头文件成功");
    }
    /**
     * 写入标题头文件
     * @date 2021/11/8 14:06
     * @author zk_yjl
     * @param
     * @return void
     */
    @Test
    public void header2Test() throws Exception{
        //1. 大批量的业务处理，最后获取组装相应的数据信息。
        List<User> userList= DataUtil.createDataList("两个蝴蝶飞");
        //2. 存储的文件路径
        String filePath="D:\\csv\\header2.csv";
        //3. 获取CsvWriter
        CsvWriter csvWriter = CsvUtil.getWriter(filePath, Charset.forName("UTF-8"));
        //4. 写入注入
        csvWriter.writeComment("带着自定义标题头的文件");
        //写入标题头
       /* csvWriter.writeHeaderLine(
                new String[]{"id编号1","姓名1","性别1","年龄1","描述1"}
        );*/   //会有重突的。 两个都写入，并不会检测.
        //写入内容
        csvWriter.writeBeans(userList);
        //关闭
        csvWriter.close();
        log.info(">>写入标题头文件成功");
    }

    /**
     * 写入CsvData数据
     * @date 2021/11/8 14:06
     * @author zk_yjl
     * @param
     * @return void
     */
    @Test
    public void csvDataTest() throws Exception{
        //1. 大批量的业务处理，最后获取组装相应的数据信息。
        List<User> userList= DataUtil.createDataList("两个蝴蝶飞");
        //2. 存储的文件路径
        String filePath="D:\\csv\\data.csv";
        //3. 获取CsvWriter
        CsvWriter csvWriter = CsvUtil.getWriter(filePath, Charset.forName("UTF-8"));
        //4. 写入注入
        csvWriter.writeComment("写入CsvData文件");
        //写入标题头
        List<String> header=Arrays.asList("编号","姓名","性别","年龄","描述");

        List<CsvRow> csvRowList=new ArrayList<>();

        int i=0;
        //写入行
        for(User user:userList){
            //构建 headerMap
            Map<String, Integer> headerMap=new HashMap<>();
            headerMap.put("编号",0);
            headerMap.put("姓名",1);
            headerMap.put("性别",2);
            headerMap.put("年龄",3);
            headerMap.put("描述",4);

            //放置该行的值
            List<String> fieldList=new ArrayList<>();
            fieldList.add(user.getId()+"");
            fieldList.add(user.getName()+"");
            fieldList.add(user.getSex()+"");
            fieldList.add(user.getAge()+"");
            fieldList.add(user.getDescription()+"");
            //构建 CsvRow 对象
            CsvRow csvRow=new CsvRow(i,headerMap,fieldList);
            csvRowList.add(csvRow);
            i++;
        }
        CsvData csvData=new CsvData(header,csvRowList);
        csvWriter.write(csvData);
        //关闭
        csvWriter.close();
        log.info(">>写入CsvData 文件成功");
    }

}
