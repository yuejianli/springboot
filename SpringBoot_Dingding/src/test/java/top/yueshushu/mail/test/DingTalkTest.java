package top.yueshushu.mail.test;

import cn.snowheart.dingtalk.robot.starter.client.DingTalkRobotClient;
import cn.snowheart.dingtalk.robot.starter.entity.*;
import cn.snowheart.dingtalk.robot.starter.type.HideAvatarType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yueshushu.mail.service.DingService;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class DingTalkTest {

    @Autowired
    @Qualifier("dingTalkRobotClient")
    private DingTalkRobotClient client;

    @Test
    public void testSendTextMessage() throws InterruptedException {
        DingTalkResponse response = null;

        response = client.sendTextMessage(new TextMessage("业务报警:构建 TextMessage对象发布!"));
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        response = client.sendTextMessage("业务报警：构建普通字符串发布!");
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        response = client.sendTextMessage("业务报警：通知指定人!", new String[]{"15734078927"});
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        response = client.sendTextMessage("业务报警：通知群内所有人!", true);
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);
    }


    @Test
    public void testSendLinkMessage() throws InterruptedException {
        DingTalkResponse response = null;

        //链接,构建 LinkMessage 对象
        response = client.sendLinkMessage(new LinkMessage("业务报警：习近平等党和国家领导人出席全国政协十三届一次会议闭幕会",
                "全国政协十三届一次会议闭幕 习近平李克强张德江俞正声张高丽栗战书王沪宁赵乐际韩正出席 汪洋发表讲话",
                "http://mp.weixin.qq.com/s/UfmtYLSZL7kgCNnKC6Co0Q",
                "http://www.scps.gov.cn/images/17/03/02/1our9zwzfg/C6065233104C46BBC626EB4FC48A4941.jpg"));
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        // 链接，不构建对象
        response = client.sendLinkMessage("业务报警：习近平等党和国家领导人出席全国政协十三届一次会议闭幕会",
                "全国政协十三届一次会议闭幕 习近平李克强张德江俞正声张高丽栗战书王沪宁赵乐际韩正出席 汪洋发表讲话",
                "http://mp.weixin.qq.com/s/UfmtYLSZL7kgCNnKC6Co0Q");
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);
        // 链接，不构建对象
        response = client.sendLinkMessage("业务报警：习近平等党和国家领导人出席全国政协十三届一次会议闭幕会",
                "全国政协十三届一次会议闭幕 习近平李克强张德江俞正声张高丽栗战书王沪宁赵乐际韩正出席 汪洋发表讲话",
                "http://mp.weixin.qq.com/s/UfmtYLSZL7kgCNnKC6Co0Q",
                "http://www.scps.gov.cn/images/17/03/02/1our9zwzfg/C6065233104C46BBC626EB4FC48A4941.jpg");
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

    }


    private static final String markDownDemoText = "业务报警:标题" +
            "# 一级标题\n" +
            "## 二级标题\n" +
            "### 三级标题\n" +
            "#### 四级标题\n" +
            "##### 五级标题\n" +
            "###### 六级标题\n" ;

    @Test
    public void testSendMarkdownMessage() throws InterruptedException {
        DingTalkResponse response = null;
        // 构建 markdown 对象用法
        response = client.sendMarkdownMessage(new MarkdownMessage("业务报警：钉钉markdown消息支持的语法",
                markDownDemoText));
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        // 不构建对象
        response = client.sendMarkdownMessage("业务报警：钉钉markdown消息支持的语法",
                markDownDemoText);
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        // 发送给指定人
        response = client.sendMarkdownMessage("业务报警：钉钉markdown消息支持的语法",
                markDownDemoText, new String[]{"15734078927"});
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);
        //发送给全体人
        response = client.sendMarkdownMessage("业务报警：钉钉markdown消息支持的语法",
                markDownDemoText, true);
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);
    }

    @Test
    public void testSendActionCardMessage() throws InterruptedException {
        DingTalkResponse response = null;

        response = client.sendActionCardMessage(new ActionCardMessage("业务报警:This is title", "![screenshot](@lADOpwk3K80C0M0FoA)\n" +
                "**Apple Store** 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划"));
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        response = client.sendActionCardMessage("业务报警:This is title", "![screenshot](@lADOpwk3K80C0M0FoA)\n" +
                "**Apple Store** 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划");
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        response = client.sendActionCardMessage("业务报警:This is title", "![screenshot](@lADOpwk3K80C0M0FoA)\n" +
                        "**Apple Store** 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划",
                HideAvatarType.HIDE);
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        response = client.sendActionCardMessage("业务报警:This is title", "![screenshot](@lADOpwk3K80C0M0FoA)\n" +
                        "**Apple Store** 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划",
                ActionCardButton.defaultReadButton("https://www.dingtalk.com"));
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        response = client.sendActionCardMessage("业务报警:This is title", "![screenshot](@lADOpwk3K80C0M0FoA)\n" +
                        "**Apple Store** 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划",
                HideAvatarType.HIDE,
                ActionCardButton.defaultReadButton("https://www.dingtalk.com"));
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

    }

    @Test
    public void testSendFeedCardMessage() throws InterruptedException {
        DingTalkResponse response = null;

        ArrayList<FeedCardMessageItem> items = new ArrayList<>();
        items.add(new FeedCardMessageItem("业务报警:成为架构师的路上，看这一篇文章就足够了，因为……",
                "http://mp.weixin.qq.com/s/CPUaB60pue0Zf3fUL9xqvw",
                "https://mmbiz.qpic.cn/mmbiz_jpg/YriaiaJPb26VMtfgPvTsM9amH5hf3pmTbf40ia6OLE845icrDb0vt4AsMnTyva5mMMpwwxnkVR5UjCEI8ADvSic1qWQ/640"));

        items.add(new FeedCardMessageItem("业务报警:想成为一名Web开发者？你应该学习Node.js而不是PHP",
                "http://mp.weixin.qq.com/s/x8dm9e7gwLXSEzxE6sQYow",
                "https://mmbiz.qpic.cn/mmbiz_jpg/YriaiaJPb26VND0Q0hSBOoyVkr9cXQrFjWI7hOzax1IxIibqanXYD4W8nyeYX5iaicjgiaqia7ly94iawOsGwehbKGwGsA/640"));

        response = client.sendFeedCardMessage(new FeedCardMessage(items));
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);

        response = client.sendFeedCardMessage(items);
        Assert.assertEquals(response.getErrcode().longValue(), 0L);
        log.info(response.toString());
        Thread.sleep(3000);
    }





}
