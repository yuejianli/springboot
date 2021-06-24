package top.yueshushu.learn.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import java.util.List;

/**
 * @ClassName:UserController
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/6/24 16:09
 * @Version 1.0
 * @Since 1.0
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.findById(id);
    }
    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }
    @GetMapping("/slowSql/{barcode}")
    public User slowSql(@PathVariable("barcode") String barcode){
        return userService.slowSql(barcode);
    }
    @GetMapping("/getDruidData")
    public Object druidStat(){
        // DruidStatManagerFacade#getDataSourceStatDataList
        // 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        return DruidStatManagerFacade.getInstance().getSqlStatDataList("");
    }
}
