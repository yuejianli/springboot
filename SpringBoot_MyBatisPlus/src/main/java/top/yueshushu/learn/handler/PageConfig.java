package top.yueshushu.learn.handler;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * @ClassName:PageConfig
 * @Description 分页配置
 * @Author zk_yjl
 * @Date 2021/9/1 10:54
 * @Version 1.0
 * @Since 1.0
 **/
@Component
@MapperScan(value="top.yueshushu.learn.mapper")
public class PageConfig {
    /**
     * 对分页进行配置
     * @return
     */
   // @Bean
    public PaginationInterceptor getPaginationInterceptor(){
        return new PaginationInterceptor();
    }

}
