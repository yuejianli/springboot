package top.yueshushu.learn.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import top.yueshushu.learn.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:UserService
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
public interface UserService {
    /**
     * 这是 CrudRepository 接口的用法
     */
    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    void batchAddUser(List<User> userList);

    User findById(Integer id);

    List<User> findAll();

    List<User> findAllByIds(List<Integer> ids);

    Long count();

    /**
     * 这是 PagingAndSortingRepository 接口的用法
     */
    List<User> findAllOrderBySexAndAge();

    Page<User> pageAll();

    /**
     * 这是 jpa的相关接口
     * @return
     */
    List<User> jpaFindAll();



    List<User> findByExample(Example example);

    List<User> findByName(String name);

    List<User> findBySexAndAge(String sex, Integer age);


    List<User> findBySexOrderByAge(String sex);

    List<Map<String,Object>> findQueryByName(String name);

    List<User> jpaFindAllSql(String name);
    /**
     * 这是动态查询的相关接口
     * @param user
     * @return
     */
    List<User> findByNameSexAndDesc(User user);


    List<User> findAllQueryByName(String name);


}
