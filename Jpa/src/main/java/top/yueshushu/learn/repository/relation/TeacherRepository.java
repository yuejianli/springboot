package top.yueshushu.learn.repository.relation;

import org.springframework.data.jpa.repository.JpaRepository;
import top.yueshushu.learn.pojo.relation.Teacher;

/**
 * @ClassName:TeacherRepository
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/14 17:22
 * @Version 1.0
 **/
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
