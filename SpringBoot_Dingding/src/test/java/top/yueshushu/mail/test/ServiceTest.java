package top.yueshushu.mail.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yueshushu.mail.service.DingService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-05-16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ServiceTest {

    @Resource
    private DingService dingService;

    @Test
    public void simpleEmailTest() {
        String[] toArr = new String[]{"15734078927"};
        dingService.sendSimpleText(toArr, "钉钉发送消息");
        log.info(">>>钉钉发送消息成功");
    }

    @Test
    public void velocityTest() {
        String[] toArr = new String[]{"15734078927"};
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("line",System.lineSeparator());
        dataMap.put("title","你叫什么名称");
        dataMap.put("content","我叫岳泽霖,是一个快乐的程序员");
        dingService.sendVelocityText("md消息",toArr, dataMap,"interface_tenwhy.vm");
        log.info(">>>发送测试钉钉成功");
    }
}
