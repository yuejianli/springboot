package com.zk.template.freemark;

import com.zk.template.freemark.pojo.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:ListFill
 * @Description list的填充
 * @Author yjl
 * @Date 2021/6/11 17:17
 * @Version 1.0
 **/
public class ListFill {
    @Test
    public void listFillTest1() throws Exception{
        Configuration configuration=new Configuration(
                Configuration.VERSION_2_3_29
        );
        configuration.setDirectoryForTemplateLoading(
                new File(
                        "D:\\learncode\\Template\\FreeMark\\Basic\\src\\main\\resources\\freemark"
                )
        );
        configuration.setDefaultEncoding("utf-8");

        //4. 设置数据
        Map<String,Object> root=new HashMap<>();
        root.put("web","List循环展示");
        List<String> hobbys=new ArrayList<>();
        hobbys.add("看书");
        hobbys.add("打游戏");
        hobbys.add("编程");
        root.put("hobbys",hobbys);
        //5. 获取模板
        Template template=configuration.getTemplate("list.ftl");
        //输出信息
        Writer out=new OutputStreamWriter(System.out);
        //7. 进行填充数据
        template.process(root,out);
    }

    @Test
    public void listFillTest2() throws Exception{
        Configuration configuration=new Configuration(
                Configuration.VERSION_2_3_29
        );
        configuration.setDirectoryForTemplateLoading(
                new File(
                        "D:\\learncode\\Template\\FreeMark\\Basic\\src\\main\\resources\\freemark"
                )
        );
        configuration.setDefaultEncoding("utf-8");

        //4. 设置数据
        Map<String,Object> root=new HashMap<>();
        root.put("web","List循环");
        List<String> hobbys=new ArrayList<>();
        hobbys.add("看书");
        hobbys.add("打游戏");
        hobbys.add("编程");
        root.put("hobbys",hobbys);
        //获取集合对象
        List<User> userList=getUserList();
        root.put("users",userList);
        //5. 获取模板
        Template template=configuration.getTemplate("list.ftl");
        //输出信息
        Writer out=new OutputStreamWriter(System.out);
        //7. 进行填充数据
        template.process(root,out);
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
