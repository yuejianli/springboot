package top.yueshushu.learn.service;

import top.yueshushu.learn.pojo.two.Dept;

import java.util.List;

/**
 * @ClassName:DeptService
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
public interface DeptService {
    void batchAddDept(List<Dept> deptList);
    List<Dept> listDept();

}
