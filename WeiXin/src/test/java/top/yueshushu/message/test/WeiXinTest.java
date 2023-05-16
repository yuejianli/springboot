package top.yueshushu.message.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yueshushu.message.service.WeiXinService;

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
public class WeiXinTest {

    @Resource
    private WeiXinService weiXinService;

    @Test
    public void simpleEmailTest() {
        String[] toArr = new String[]{"YueJianLi"};
        weiXinService.sendSimpleText(toArr, "你好啊,岳泽霖");
        log.info(">>>企业微信发送消息");
    }

    @Test
    public void velocityTest() {
        String[] toArr = new String[]{"YueJianLi"};
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("line",System.lineSeparator());
        dataMap.put("title","你叫什么名字");
        dataMap.put("content","我叫岳泽霖,是一个快乐的程序员");
        weiXinService.sendVelocityText("你好啊",toArr, dataMap,"interface_tenwhy.vm");
        log.info(">>>企业微信发送消息成功");
    }
}
