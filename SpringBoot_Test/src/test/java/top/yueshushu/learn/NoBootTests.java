package top.yueshushu.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-04-03
 */
@Slf4j
public class NoBootTests {
    @Test
    public void testA() {
        int a = 10/2;
        log.info("打印信息: {}" ,a);
    }
}
