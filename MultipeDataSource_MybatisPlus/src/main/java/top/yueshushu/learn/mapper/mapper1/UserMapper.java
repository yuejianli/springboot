package top.yueshushu.learn.mapper.mapper1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.yueshushu.learn.pojo.one.User;

import java.util.List;

/**
 * @ClassName:UserMapper
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/18 17:51
 * @Version 1.0
 **/
//扫描由启动类进行扫描配置
public interface UserMapper extends BaseMapper<User> {
    //走 xml的配置
    void batchAdd(@Param("userList") List<User> userList);
}
