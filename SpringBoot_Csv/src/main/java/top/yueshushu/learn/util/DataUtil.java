package top.yueshushu.learn.util;

import top.yueshushu.learn.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:DataUtil
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/8 13:51
 * @Version 1.0
 * @Since 1.0
 **/
public class DataUtil {
    /**
     * 获取用户的数据
     * @date 2021/11/8 13:51
     * @author zk_yjl
     * @param name
     * @return java.util.List<top.yueshushu.learn.pojo.User>
     */
    public static List<User> createDataList(String name) {
        List<User> result=new ArrayList<>();
        for(int i=1;i<=10;i++){
            User user=new User();
            user.setId(i);
            user.setName(name+"_"+i);
            user.setSex(i%2==0?"女":"男");
            user.setAge(20+i);
            user.setDescription("我是第"+i+"个，我的名字是:"+user.getName());
            result.add(user);
        }
        return result;
    }

}
