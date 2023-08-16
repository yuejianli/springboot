package top.yueshushu.learn.repository.one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yueshushu.learn.pojo.one.User;

/**
 * @ClassName:UserRepository
 * @Description 员工仓库
 * @Author yjl
 * @Date 2021/5/14 14:45
 * @Version 1.0
 **/
public interface UserRepository extends JpaRepository<User, Integer>,
        JpaSpecificationExecutor<User>{
    // 其他的方法. 具体使用可以参考 Jpa章节
}
