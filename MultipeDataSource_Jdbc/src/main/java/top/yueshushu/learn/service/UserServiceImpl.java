package top.yueshushu.learn.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.pojo.Dept;
import top.yueshushu.learn.pojo.User;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    @Resource
    private JdbcTemplate jdbcTemplateOne;
    @Resource
    private JdbcTemplate jdbcTemplateTwo;
    @Override
    public void addUser(User user) {
        //1.sql语句
        String sql="insert into user(name,sex,age,description) values(?,?,?,?) ";
        //2.调用 update语句，执行方法
        jdbcTemplateOne.update(sql,user.getName(),user.getSex(),user.getAge(),
                user.getDescription());
    }
    @Override
    public List<User> listUser() {
        String sql = "select id,name,sex,age,description from user ";
        List<User> userList = jdbcTemplateOne.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int num) throws SQLException {
                //根据resultSet 表示结果集， num 表示索引行
                User tempUser = new User();
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setName(resultSet.getString("name"));
                tempUser.setSex(resultSet.getString("sex"));
                tempUser.setAge(resultSet.getInt("age"));
                tempUser.setDescription(resultSet.getString("description"));
                return tempUser;
            }
        });
        return userList;
    }

    @Override
    public void addDept(Dept dept) {
        //1.sql语句
        String sql="insert into dept(name) values(?) ";
        //2.调用 update语句，执行方法
        jdbcTemplateTwo.update(sql,dept.getName());
    }
    @Override
    public List<Dept> listDept() {
        String sql = "select id,name from dept ";
        List<Dept> deptList = jdbcTemplateTwo.query(sql, new RowMapper<Dept>() {
            @Override
            public Dept mapRow(ResultSet resultSet, int num) throws SQLException {
                //根据resultSet 表示结果集， num 表示索引行
                Dept tempDept = new Dept();
                tempDept.setId(resultSet.getInt("id"));
                tempDept.setName(resultSet.getString("name"));
                return tempDept;
            }
        });
        return deptList;
    }
}
