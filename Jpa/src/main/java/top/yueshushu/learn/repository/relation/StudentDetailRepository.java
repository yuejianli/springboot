package top.yueshushu.learn.repository.relation;

import org.springframework.data.jpa.repository.JpaRepository;
import top.yueshushu.learn.pojo.relation.StudentDetail;

/**
 * @InterfaceName StudentDetailRepository
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/14 16:08
 * @Version 1.0
 **/
public interface StudentDetailRepository extends JpaRepository<StudentDetail,Integer> {

}
