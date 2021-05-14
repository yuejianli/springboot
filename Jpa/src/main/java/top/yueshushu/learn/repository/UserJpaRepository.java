package top.yueshushu.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import top.yueshushu.learn.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:UserJpaRepository
 * @Description Jpa仓库演示
 * @Author 岳建立
 * @Date 2021/4/26 20:08
 * @Version 1.0
 **/
public interface UserJpaRepository extends JpaRepository<User,Integer> {

    List<User> findByName(String name);

    List<User> findBySexAndAge(String sex, Integer age);

    List<User> findBySexOrderByAgeDesc(String sex);

    @Query(value="select id as id,name as name from user where name=:name",nativeQuery = true)
    List<Map<String,Object>> findQueryByName(@Param("name") String name);
    @Query(value="select * from user where name like concat('%',:name,'%')",nativeQuery = true)
    List<User> findAllQueryByName(@Param("name") String name);
}
