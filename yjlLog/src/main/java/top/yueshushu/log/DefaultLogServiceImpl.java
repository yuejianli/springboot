package top.yueshushu.log;

import lombok.extern.log4j.Log4j2;

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
    /**
     * 默认的日志实现，打印到控制台
     * @date 2021/10/29 17:53
     * @author zk_yjl
     * @param logVo
     * @return void
     */
    @Override
    public void logHandler(LogVo logVo) {
        log.info("默认处理日志:>>>"+logVo);
    }
}
