package top.yueshushu.learn.pojo.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName:Student
 * @Description 学生
 * @Author yjl
 * @Date 2021/5/14 15:53
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="account")
    private String account;
    @Column(name="password")
    private String password;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "student_detail_id")
    private StudentDetail studentDetail;
    @Override
    public String toString(){
        return "id:"+id+",name:"+name+",account:"+account
                +",password:"+password;
    }
}
