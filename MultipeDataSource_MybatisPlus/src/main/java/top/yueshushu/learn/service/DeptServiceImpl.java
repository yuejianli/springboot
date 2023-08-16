package top.yueshushu.learn.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.mapper.mapper1.UserMapper;
import top.yueshushu.learn.mapper.mapper2.DeptMapper;
import top.yueshushu.learn.pojo.one.User;
import top.yueshushu.learn.pojo.two.Dept;

import java.util.List;

/**
 * @ClassName:DeptServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
@Service
//采用 springboot2 的数据库
@DS("two")
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void batchAddDept(List<Dept> deptList) {
        deptMapper.batchAdd(deptList);
    }
    @Override
    public List<Dept> listDept() {
       return deptMapper.selectList(null);
    }
    //单独另外采用 springboot 的数据库
    @DS("one")
    @Override
    public List<User> listUser() {
        return userMapper.selectList(null);
    }

}
