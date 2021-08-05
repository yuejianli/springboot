package com.zk.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:VelocityTests
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/15 11:55
 * @Version 1.0
 **/
public class VelocityTests {
    @Test
    public void basicFill() throws Exception{
        //1. 初始化模板引擎
        VelocityEngine ve=new VelocityEngine();
        //2. 设置相应的属性信息
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        //3. 进行初始化
        ve.init();
        //4.获取模板文件
        Template template=ve.getTemplate("basic.vm","utf-8");
        //5.设置变量
        VelocityContext root=new VelocityContext();
        root.put("name","岳泽霖");
        Map<String,Object> info=new HashMap<>();
        info.put("name","YJL");
        info.put("age",26);
        root.put("info",info);
        //6.进行合并，调用模板的 merge 方法， 将其进行填充。
        StringWriter stringWriter=new StringWriter();
        template.merge(root, stringWriter);
        System.out.println("输出信息值:"
        +stringWriter.toString());
    }
}
