package top.yueshushu.learn.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import top.yueshushu.learn.pojo.User;

/**
 * @ClassName:UserRepository
 * @Description 分页和排序演示
 * @Author 岳建立
 * @Date 2021/4/26 19:36
 * @Version 1.0
 **/
public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User,Integer> {

}
