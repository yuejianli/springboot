package top.yueshushu.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.mapper2.DeptMapper;
import top.yueshushu.learn.pojo.Dept;

import java.util.List;

/**
 * @ClassName:DeptServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void addDept(Dept user) {
        deptMapper.addDept(user);
    }
    @Override
    public List<Dept> listDept() {
       return deptMapper.listDept();
    }
}
