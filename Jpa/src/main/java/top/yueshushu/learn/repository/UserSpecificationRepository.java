package top.yueshushu.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yueshushu.learn.pojo.User;

/**
 * @ClassName:UserSpecificationRepository
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/14 14:45
 * @Version 1.0
 **/
public interface UserSpecificationRepository extends JpaRepository<User, Integer>,
        JpaSpecificationExecutor<User>{


}
