package top.yueshushu.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-05-18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class LogTest {

    @Test
    public void logTest() {
        log.info("两个蝴蝶飞学习日志配置");
        log.debug(" debug 日志");
        log.warn("warn 级别日志");
        log.error(" error 级别日志");
        log.trace("trace 级别日志");
    }
}
