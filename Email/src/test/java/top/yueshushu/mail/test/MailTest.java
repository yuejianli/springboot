package top.yueshushu.mail.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yueshushu.mail.service.EmailService;

import javax.annotation.Resource;
import java.util.Arrays;
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
public class MailTest {

    @Resource
    private EmailService emailService;

    @Test
    public void simpleEmailTest() {
        String[] toArr = new String[]{"1290513799@qq.com"};
        emailService.sendSimpleMail(toArr, "发送测试简单文件", "一封简单的测试文件");
        log.info(">>>发送邮件成功");
    }

    @Test
    public void htmlEmailTest() {
        String[] toArr = new String[]{"1290513799@qq.com"};
        emailService.sendHtmlMail(toArr, "发送测试HTML文件", "<a href='https://wwww.baidu.com'>百度</a>");
        log.info(">>>发送邮件成功");
    }
    @Test
    public void sendAttachmentsMailTest() {
        String[] toArr = new String[]{"1290513799@qq.com"};
        emailService.sendAttachmentsMail(toArr, "发送附件文件", "<a href='https://wwww.baidu.com'>百度</a>",
                Arrays.asList("E:\\Alan\\logoold.jpg", "E:\\Alan\\logo.jpg"));
        log.info(">>>发送邮件成功");
    }



    @Test
    public void velocityTest() {
        String[] toArr = new String[]{"1290513799222@qq.com"};
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("line",System.lineSeparator());
        dataMap.put("title","你叫什么名称");
        dataMap.put("content","我叫岳泽霖,是一个快乐的程序员");
        emailService.sendVelocityMail(toArr, "发送velocity  文件", dataMap,"interface_tenwhy.vm" );
        log.info(">>>发送测试邮件成功");
    }
}
