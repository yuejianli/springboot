package top.yueshushu.learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import top.yueshushu.log.LogService;
import top.yueshushu.log.LogVo;

/**
 * @ClassName:LogConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/10/25 20:12
 * @Version 1.0
 * @Since 1.0
 **/
//@Configuration
public class LogConfig implements LogService {

    @Override
    public void logHandler(LogVo logVo) {
        System.out.println(">>>将其写入到数据库里面,内容是:"+logVo);
    }
}
