package top.yueshushu.learn.pojo.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName:StudentDetail
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/14 15:55
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student_detail")
public class StudentDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="sex")
    private String sex;
    @Column(name="age")
    private Integer age;
    @Column(name="description")
    private String description;
    @Column(name="address")
    private String address;
    @OneToOne(mappedBy = "studentDetail")
    private Student student;
    @Override
    public String toString(){
        return "sex:"+sex+",age:"+age;
    }

}
