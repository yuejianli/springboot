package top.yueshushu.learn.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import top.yueshushu.log.LogService;
import top.yueshushu.log.LogVo;

/**
 * @ClassName:LogConfig
 * @Description 自定义配置日志处理方式
 * @Author zk_yjl
 * @Date 2021/10/25 20:12
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
@Log4j2
public class LogConfig implements LogService {
    //注入数据 Mapper, 进行处理.
    @Override
    public void logHandler(LogVo logVo) {
        //一个简单的输出，让自定义生效
        log.info(">>访问信息:"+logVo);
    }
}
