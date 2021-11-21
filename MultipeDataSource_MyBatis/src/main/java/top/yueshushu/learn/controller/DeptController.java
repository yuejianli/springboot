package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.pojo.two.Dept;
import top.yueshushu.learn.service.DeptService;

import java.util.List;

/**
 * @ClassName:DeptController
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/2 12:57
 * @Version 1.0
 * @Since 1.0
 **/
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;
    @RequestMapping("/addDept")
    public String addDept(){
        //1. 构建对象
        Dept dept=new Dept();
        dept.setName("欢欢");
        //2. 添加方法
        deptService.addDept(dept);
        return "添加员工成功";
    }
    @RequestMapping("/listDept")
    public List<Dept> listDept(){
        List<Dept> userList=deptService.listDept();
        return userList;
    }
}
