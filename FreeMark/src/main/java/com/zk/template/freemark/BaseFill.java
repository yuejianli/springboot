package com.zk.template.freemark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:BaseFill
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/11 16:15
 * @Version 1.0
 **/
public class BaseFill {

    @Test
    public void fillBaseTest() throws Exception{
        //1. 指定配置，对应着哪一个版本
         Configuration configuration=new Configuration(
                 Configuration.VERSION_2_3_29
         );
         //设置目录
         configuration.setDirectoryForTemplateLoading(
                 new File("D:\\learncode\\Template\\FreeMark\\Basic\\src\\main\\resources\\freemark")
         );
         //指定编码
        configuration.setDefaultEncoding("utf-8");
        //指定异常处理器
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //开始创建数据
        Map<String,Object> root=new HashMap<>();
        root.put("title","Hello Freemark");
        root.put("info","两个蝴蝶飞");
        //找到要设置的，对应的模板
        Template template=configuration.getTemplate("hello.ftl","utf-8");
        //写出到控制台
        Writer out=new OutputStreamWriter(System.out);
        //进行填充 数据和信息。
        template.process(root,out);
    }
    @Test
    public void fillInfoTest() throws Exception{
        //1. 设置相应的 Configuration
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_29);
        //2. 设置基本的目录
        configuration.setDirectoryForTemplateLoading(
                new File("D:\\learncode\\Template\\FreeMark\\Basic\\src\\main\\resources\\freemark")
        );
        //3. 设置编码
        configuration.setDefaultEncoding("utf-8");
        //设置错误处理器
        configuration.setTemplateExceptionHandler(
                TemplateExceptionHandler.RETHROW_HANDLER
        );

        //4. 构建数据
        Map<String,Object> root=new HashMap<>();
        root.put("web","百度");
        root.put("user","两个蝴蝶飞");
        Map<String,Object> info=new HashMap<>();
        info.put("url","www.yueshushu.top");
        info.put("name","百度");
        root.put("info",info);

        //5. 获取对应的模板
        Template template=configuration.getTemplate("info.ftl");
        //6.构建输出对象
        Writer out=new OutputStreamWriter(System.out);
        //7. 调用 process 方法，进行填充数据
        template.process(root,out);
    }
}
