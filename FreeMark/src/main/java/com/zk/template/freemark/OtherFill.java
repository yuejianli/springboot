package com.zk.template.freemark;

import com.zk.template.freemark.pojo.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

/**
 * @ClassName:OtherFill
 * @Description TODO
 * @Author yjl
 * @Date 2021/6/15 9:54
 * @Version 1.0
 **/
public class OtherFill {
    /*
        填充一些其他的数据信息。
     */
    @Test
    public void otherFill() throws Exception{
        //1. 创建 Configuration 对象
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_29);
        //2. 设置基本的目录信息。
        configuration.setDirectoryForTemplateLoading(new File("D:\\learncode\\Template\\FreeMark\\Basic\\src\\main\\resources\\freemark"
                ));
        //3. 设置编码等格式
        configuration.setDefaultEncoding("utf-8");
        //4. 设置要添加的数据信息
        Map<String,Object> root=new HashMap<>();
        root.put("name","两个蝴蝶飞");
        root.put("str","Two Butterfly");
        List<User> userList=getUserList();
        root.put("users",userList);
        root.put("dateTime",new Date());

        //5. 获取对应的模板
        Template template=configuration.getTemplate("other.ftl","utf-8");
        //6. 设置输出对象
        Writer out=new OutputStreamWriter(System.out);
        //7. 调用 template的 process 方法，进行填充
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
