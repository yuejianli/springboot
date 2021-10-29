package top.yueshushu.log;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @ClassName:LogService
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/10/25 10:28
 * @Version 1.0
 * @Since 1.0
 **/
public interface LogService {
    /**
     * 日志处理
     * @date 2021/10/25 19:56
     * @author zk_yjl
     * @param
     * @return void
     */
   public void logHandler(LogVo logVo);
}
