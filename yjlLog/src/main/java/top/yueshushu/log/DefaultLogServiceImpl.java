package top.yueshushu.log;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @ClassName:DefaultLogServiceImpl
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/10/25 19:52
 * @Version 1.0
 * @Since 1.0
 **/
@Log4j2
public class DefaultLogServiceImpl implements LogService{
    @Override
    public void logHandler(LogVo logVo) {
        log.info("默认处理日志:>>>"+logVo);
    }
}
