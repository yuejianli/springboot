package com.zk.template.freemark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @ClassName:IncludeFill
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/11 17:49
 * @Version 1.0
 **/
public class IncludeFill {
    @Test
    public void includeFillTest() throws Exception{
        //1. 通过版本，获取相关的配置
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_29);
        //2. 设置扫描的包的基本路径
        configuration.setDirectoryForTemplateLoading(
                new File("D:\\learncode\\Template\\FreeMark\\Basic\\src\\main\\resources\\freemark")
        );
        //3. 设置基本路径
        configuration.setDefaultEncoding("utf-8");
        //5. 获取对应的模板文件
        Template template=configuration.getTemplate("footinclude.ftl");
        //6. 输出到控制台
        Writer out=new OutputStreamWriter(System.out);
        //7. 通过 process() 方法进行填充数据和输出信息对象.
        template.process(null,out);
    }
}
