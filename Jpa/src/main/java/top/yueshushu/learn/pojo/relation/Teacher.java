package top.yueshushu.learn.pojo.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:Teacher
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/14 15:54
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="teacher")
@Entity
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Student> studentList;

   /* @Override
    public String toString(){
        return "name:"+name+",description:"+description;
    }*/
}
