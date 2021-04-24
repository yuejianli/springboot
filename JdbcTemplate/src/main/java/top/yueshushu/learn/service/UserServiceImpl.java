package top.yueshushu.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:UserServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void addUser(User user) {
        //1.sql语句
        String sql="insert into user(name,sex,age,description) values(?,?,?,?) ";
        //2.调用 update语句，执行方法
        jdbcTemplate.update(sql,user.getName(),user.getSex(),user.getAge(),
                user.getDescription());
    }

    @Override
    public void updateUser(User user) {
        //1.sql语句
        String sql="update user set name=?,description=? where id=?  ";
        //2.调用 update语句，执行方法
        jdbcTemplate.update(sql,user.getName(),user.getDescription(),user.getId());
    }

    @Override
    public void deleteUser(Integer id) {
        String sql="delete from user where id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void batchAddUser(List<User> userList) {
        //1.sql语句
        String sql="insert into user(name,sex,age,description) values(?,?,?,?) ";
        //2. 构建参数集合信息
        List<Object[]> objects=new ArrayList<>();
        for(User user:userList){
            Object[] obj=new Object[4];
            obj[0]=user.getName();
            obj[1]=user.getSex();
            obj[2]=user.getAge();
            obj[3]=user.getDescription();
            objects.add(obj);
        }
        //3.调用 batchUpdate 批量更新，执行方法
        jdbcTemplate.batchUpdate(sql,objects);
    }
}
