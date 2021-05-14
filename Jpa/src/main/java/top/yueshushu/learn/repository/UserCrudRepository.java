package top.yueshushu.learn.repository;

import org.springframework.data.repository.CrudRepository;
import top.yueshushu.learn.pojo.User;

/**
 * @InterfaceName UserCrudRepository
 * @Description Curd仓库演示
 * @Author 岳建立
 * @Date 2021/4/26 19:13
 * @Version 1.0
 **/
public interface UserCrudRepository extends CrudRepository<User,Integer> {

}
