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
 * @ClassName:IncludeTests
 * @Description 放置Include 语句
 * @Author yjl
 * @Date 2021/6/15 14:39
 * @Version 1.0
 **/
public class IncludeTests {
    @Test
    public void includeFillTest()throws Exception{
        //1. 创建模板引擎
        VelocityEngine velocityEngine=new VelocityEngine();
        //2. 设置属性
        velocityEngine.setProperty(
                RuntimeConstants.RESOURCE_LOADER,"classpath"
        );
        velocityEngine.setProperty(
                "classpath.resource.loader.class",
                ClasspathResourceLoader.class.getName()
        );
        //3. 进行初始化
        velocityEngine.init();
        //4.获取对应的模板信息
        Template template=velocityEngine.getTemplate("footInclude.vm");
        //5. 构建 VelocityContext 对象，里面放置内容
        Map<String,Object> root=new HashMap<>();
        root.put("name","两个蝴蝶飞");
        VelocityContext velocityContext=new VelocityContext(root);
        //6. 找到StringWriter对象，进行合并
        StringWriter stringWriter=new StringWriter();
        template.merge(velocityContext,stringWriter);
        System.out.println("输出信息:"+stringWriter.toString());

    }
}
