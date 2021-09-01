package top.yueshushu.learn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.yueshushu.learn.pojo.User;

import java.util.List;

/**
 * @ClassName:UserMapper
 * @Description User的基本 Mapper
 * @Author zk_yjl
 * @Date 2021/9/1 9:49
 * @Version 1.0
 * @Since 1.0
 **/
public interface UserMapper extends BaseMapper<User> {
    void batchAdd(@Param("userList") List<User> userList);

    void batchUpdate(@Param("userList") List<User> userList);

    void batchDeleteByIds(@Param("ids") List<Integer> ids);

}
