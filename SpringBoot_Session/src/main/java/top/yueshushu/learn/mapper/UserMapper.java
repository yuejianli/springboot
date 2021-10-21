package top.yueshushu.learn.mapper;

import org.apache.ibatis.annotations.Param;
import top.yueshushu.learn.pojo.User;

import java.util.List;

/**
 * @ClassName:UserMapper
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/18 17:51
 * @Version 1.0
 **/
public interface UserMapper {
    /**
     * 根据员工账号和密码进行查询
     */
    User findByAccountAndPassword(@Param("account") String account, @Param("password") String password);
}
