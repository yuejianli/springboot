package top.yueshushu.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public User queryById(Integer id) {
        String sql="select id,name,sex,age,description from user where id=?";
        //用 RowMapper 进行封装对象信息
        User user=jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int num) throws SQLException {
                //根据resultSet 表示结果集， num 表示索引行
                User tempUser=new User();
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setName(resultSet.getString("name"));
                tempUser.setSex(resultSet.getString("sex"));
                tempUser.setAge(resultSet.getInt("age"));
                tempUser.setDescription(resultSet.getString("description"));
                return tempUser;
            }
        },id);
        return user;
    }

    @Override
    public List<User> queryForAll() {
        String sql="select id,name,sex,age,description from user ";
        List<User> userList=jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int num) throws SQLException {
                //根据resultSet 表示结果集， num 表示索引行
                User tempUser=new User();
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
    public Map<String, Object> queryForMapById(Integer id) {
        String sql="select id,name,sex,age,description from user where id=?";
        Map<String,Object> map=jdbcTemplate.queryForMap(sql,id);
        return map;
    }

    @Override
    public Integer countAll() {
        String sql="select count(1) from user";
        Integer count=jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    @Override
    public List<Integer> queryForIdList() {
        String sql="select id from user";
        List<Integer> idList=jdbcTemplate.queryForList(sql,Integer.class);
        return idList;
    }

    @Override
    public void createTable() {
        String sql="create table dept(id int(11),name varchar(200))";
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<User> queryBeanForAll() {
        String sql="select id,name,sex,age,description from user ";
        List<User> userList=jdbcTemplate.query(sql,new MyUserMapper());
        return userList;
    }
    class MyUserMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet resultSet, int num) throws SQLException {
            //根据resultSet 表示结果集， num 表示索引行
            User tempUser=new User();
            tempUser.setId(resultSet.getInt("id"));
            tempUser.setName(resultSet.getString("name"));
            tempUser.setSex(resultSet.getString("sex"));
            tempUser.setAge(resultSet.getInt("age"));
            tempUser.setDescription(resultSet.getString("description"));
            return tempUser;
        }
    }
    @Override
    public List<User> queryRowSetForAll() {
        String sql="select id,name,sex,age,description from user ";
        SqlRowSet sqlRowSet=jdbcTemplate.queryForRowSet(sql);
        List<User> userList=new ArrayList<>();
        while(sqlRowSet.next()){
            User tempUser=new User();
            tempUser.setId(sqlRowSet.getInt("id"));
            tempUser.setName(sqlRowSet.getString("name"));
            tempUser.setSex(sqlRowSet.getString("sex"));
            tempUser.setAge(sqlRowSet.getInt("age"));
            tempUser.setDescription(sqlRowSet.getString("description"));
            userList.add(tempUser);
        }
        return userList;
    }
}
