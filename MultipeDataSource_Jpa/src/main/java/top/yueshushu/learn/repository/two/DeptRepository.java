package top.yueshushu.learn.repository.two;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.yueshushu.learn.pojo.two.Dept;

/**
 * @ClassName:DeptRepository
 * @Description 部门仓库
 * @Author yjl
 * @Date 2021/5/14 14:45
 * @Version 1.0
 **/
public interface DeptRepository extends JpaRepository<Dept, Integer>,
        JpaSpecificationExecutor<Dept>{
    // 其他的方法. 具体使用可以参考 Jpa章节
}
