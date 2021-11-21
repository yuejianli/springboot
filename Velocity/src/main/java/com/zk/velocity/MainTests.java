package com.zk.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:MainTests
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/15 19:12
 * @Version 1.0
 **/
public class MainTests {
    @Test
    public void mainTests() throws Exception{
        //1. 初始化模板引擎
        VelocityEngine ve=new VelocityEngine();
        //2. 设置相应的属性信息
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader
                .class.getName());
        //3. 进行初始化
        ve.init();
        //4.获取模板文件
        Template template=ve.getTemplate("main.vm","utf-8");
        //5.设置变量
        Map<String,Object> root=getMainMap();
        VelocityContext velocityContext=new VelocityContext(root);
        //6.进行处理，发送到模板文件里面。
        PrintWriter printWriter=new PrintWriter("src\\main\\java\\com\\zk\\velocity\\VelocityMain.java");
        template.merge(velocityContext,printWriter);
        //刷新并关闭
        printWriter.flush();
        printWriter.close();
        System.out.println("生成文件成功");

    }
    private Map<String,Object> getMainMap() {
        Map<String,Object> root=new HashMap<>();
        root.put("packageName","com.zk.velocity");
        root.put("author","zk");
        root.put("createDate",new Date());
        root.put("classDescription","一个测试Velocity 自动生成类");
        root.put("className","VelocityMain");
        root.put("mainMethodDesc","一个普通的测试方法");
        root.put("info","Velocity 创建类生成信息");
        return root;
    }
}
