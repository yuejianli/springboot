package com.zk.template.freemark.code;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:CodeAuto
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/15 10:11
 * @Version 1.0
 **/
public class CodeAuto {
    public static String FTLTEMPLATE="src/main/java/com/zk/template/freemark/code/";
    public static String CODELOCATION="src/main/java/com/zk/template/freemark/code/";
    @Test
    public void codeGenerate() throws Exception{
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_29);
        configuration.setDirectoryForTemplateLoading(new File(FTLTEMPLATE));
        configuration.setDefaultEncoding("utf-8");
        //填充数据
        Map root=getMainMap();
        //获取对应的模板
        Template template=configuration.getTemplate("main.ftl","utf-8");
        //获取对应的输出流
        Writer out=new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(
                            CODELOCATION+"FreeMain.java"
                    )
                )
        );
        template.process(root,out);
        System.out.println(">>>>>>创建模板类成功");
    }
    private Map<String,Object> getMainMap() {
        Map<String,Object> root=new HashMap<>();
        root.put("packageName","com.zk.template.freemark.code");
        root.put("author","TwoButterfly");
        root.put("createDate",new Date());
        root.put("classDescription","一个测试Freemark 自动生成类");
        root.put("className","FreeMain");
        root.put("mainMethodDesc","一个普通的测试方法");
        root.put("info","Freemark 创建类生成信息");
        return root;
    }
}
