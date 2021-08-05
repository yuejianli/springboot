package com.zk.velocity;

import com.zk.velocity.pojo.User;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:ForeachTests
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/15 15:20
 * @Version 1.0
 **/
public class ForeachTests {
    @Test
    public void foreachTest() throws Exception{
        //1.创建 VelocityEngine 引擎
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
        //4.获取模板
        Template template=velocityEngine.getTemplate("foreach.vm");
        //5.进行填充数据
        Map<String,Object> root=new HashMap<>();
        String[] hobbys=new String[4];
        hobbys[0]="A";
        hobbys[1]="B";
        hobbys[2]="C";
        hobbys[3]="D";
        root.put("hobbys",hobbys);
        List<User> userList=getUserList();
        root.put("users",userList);

        Map<String,Object> infoMap=new HashMap<>();
        infoMap.put("name","YJL");
        infoMap.put("age",26);
        infoMap.put("sex","男");
        root.put("infoMap",infoMap);

        //6. 创建 VelocityContext 对象，填充数据。
        VelocityContext velocityContext=new VelocityContext(root);
        //7.
        StringWriter stringWriter=new StringWriter();
        //8. 通过 merge 方法，进行填充数据。
        template.merge(velocityContext,stringWriter);
        System.out.println("输出内容:"+stringWriter.toString());
    }
    private List<User> getUserList() {
        List<User> userList=new ArrayList<>();
        for(int i=1;i<=10;i++){
            User user=new User();
            user.setId(i);
            user.setName("蝴蝶"+i);
            user.setAge(i*3+1);
            user.setSex(i%2);
            user.setDescription("一个简单的描述");
            userList.add(user);
        }
        return userList;
    }
}
