package top.yueshushu.learn.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.pojo.relation.Student;
import top.yueshushu.learn.pojo.relation.StudentDetail;
import top.yueshushu.learn.pojo.relation.Teacher;
import top.yueshushu.learn.repository.relation.StudentDetailRepository;
import top.yueshushu.learn.repository.relation.StudentRepository;
import top.yueshushu.learn.repository.relation.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName:StudentServiceImpl
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/14 16:10
 * @Version 1.0
 **/
@Service
@Log4j2
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentDetailRepository studentDetailRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void saveInfo() {
        //构建学生对象
        Student student=new Student();
        student.setName("岳泽霖");
        student.setAccount("yzl");
        student.setPassword("123456");
        //构建详情页对象
        StudentDetail studentDetail=new StudentDetail();
        studentDetail.setSex("男");
        studentDetail.setAge(26);
        studentDetail.setDescription("一个快乐的程序员");
        studentDetail.setAddress("杭州江干区");
        //设置对应关系
        student.setStudentDetail(studentDetail);
        //进行保存
        studentRepository.save(student);
    }

    @Override
    public void queryInfo() {

        Optional<Student> optionalStudent=studentRepository.findById(1);
        if(!optionalStudent.isPresent()){
            log.info("学生id不存在");
        }
        Student student=optionalStudent.get();
        //输出信息
        log.info(student);
        StudentDetail studentDetail=student.getStudentDetail();
        log.info(studentDetail);

    }

    @Override
    public void teacherSaveManyInfo() {
        //定义老师
        Teacher teacher=new Teacher();
        teacher.setName("周老师");
        teacher.setDescription("一个善良的周老师");
        //定义学生
        Student student1=new Student();
        student1.setName("岳泽霖1");
        student1.setAccount("yzl1");
        student1.setPassword("1234561");
        Student student2=new Student();
        student2.setName("岳泽霖2");
        student2.setAccount("yzl2");
        student2.setPassword("1234562");
        List<Student> studentList=new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        //配置关系
        teacher.setStudentList(studentList);
        //进行保存
        teacherRepository.save(teacher);
    }

    @Override
    public void teacherQueryInfo() {
        Optional<Teacher> optionalTeacher=teacherRepository.findById(1);
        if(!optionalTeacher.isPresent()){
            log.info("老师id不存在");
        }
        Teacher teacher=optionalTeacher.get();
        //输出信息
        log.info(teacher);
        List<Student> studentList=teacher.getStudentList();
        studentList.forEach(n->log.info(n));

    }
}
