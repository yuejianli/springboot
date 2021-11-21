package com.zk.template.freemark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:IfFill
 * @Description if的填充
 * @Author yjl
 * @Date 2021/6/11 17:08
 * @Version 1.0
 **/
public class IfFill {
    @Test
    public void ifFillTest() throws Exception{
        //1. 通过版本，获取相关的配置
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_29);
        //2. 设置扫描的包的基本路径
        configuration.setDirectoryForTemplateLoading(
                new File("D:\\learncode\\Template\\FreeMark\\Basic\\src\\main\\resources\\freemark")
        );
        //3. 设置基本路径
        configuration.setDefaultEncoding("utf-8");

        //4. 设置数据
        Map<String,Object> root=new HashMap<>();
        root.put("web","IF验证");
        root.put("user","yjl2");
        Map<String,Object> info=new HashMap<>();
        info.put("url","www.baidu.com");
        info.put("name","百度");
        root.put("info",info);
        root.put("sex","女");
        root.put("score",75);
        //5. 获取对应的模板文件
        Template template=configuration.getTemplate("if.ftl");
        //6. 输出到控制台
        Writer out=new OutputStreamWriter(System.out);
        //7. 通过 process() 方法进行填充数据和输出信息对象.
        template.process(root,out);
    }
}
