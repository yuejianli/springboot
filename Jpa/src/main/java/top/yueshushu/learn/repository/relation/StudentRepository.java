package top.yueshushu.learn.repository.relation;

import org.springframework.data.jpa.repository.JpaRepository;
import top.yueshushu.learn.pojo.relation.Student;

/**
 * @ClassName:StudentRepository
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/14 16:07
 * @Version 1.0
 **/
public interface StudentRepository extends JpaRepository<Student,Integer> {

}
