package top.yueshushu.mail.service.impl;

import cn.snowheart.dingtalk.robot.starter.client.DingTalkRobotClient;
import cn.snowheart.dingtalk.robot.starter.entity.DingTalkResponse;
import cn.snowheart.dingtalk.robot.starter.entity.MarkdownMessage;
import cn.snowheart.dingtalk.robot.starter.entity.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.yueshushu.mail.service.DingService;

import javax.annotation.PostConstruct;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * 邮件发送信息
 *
 * @author yuejianli
 * @date 2022-06-09
 */
@Service
@Slf4j
public class DingServiceImpl implements DingService {
	@Autowired
	@Qualifier("dingTalkRobotClient")
	private DingTalkRobotClient client;

	@Value("${dingtalk.robot.webhook}")
	private String webhook;

	private VelocityEngine velocityEngine;
	

	
	@PostConstruct
	public void initVelocityEngine() {
		velocityEngine = new VelocityEngine();
		Properties p = new Properties();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		// 配置资源
		// velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "/usr/local/templates/");
		velocityEngine.init(p);
	}
	
	@Override
	public boolean sendSimpleText(String[] toArr, String content) {
		//发送信息
		try {
			TextMessage textMessage = new TextMessage(content,toArr);
			DingTalkResponse response = client.sendMessageByURL(webhook, textMessage);
			return response.getErrcode() == 0? true :false;
		} catch (Exception e) {
			log.error("componentAndSendReqeust simple mail error", e);
			return false;
		}
	}

	@Override
    public boolean sendVelocityText(String title, String[] toArr, Map<String, Object> dataMap, String templateName) {
		//发送信息
		try {
			String velocityMailText = getVelocityMailText(dataMap,templateName);
			MarkdownMessage markdownMessage = new MarkdownMessage(title,velocityMailText,toArr);
			DingTalkResponse response = client.sendMessageByURL(webhook, markdownMessage);
			return response.getErrcode() == 0? true :false;
		} catch (Exception e) {
			log.error("componentAndSendReqeust simple mail error", e);
			return false;
		}
    }

    private String getVelocityMailText(Map<String, Object> dataMap, String templateName) {
        VelocityContext velocityContext = new VelocityContext(dataMap);
        StringWriter writer = new StringWriter();
        velocityEngine.mergeTemplate(templateName, "UTF-8", velocityContext, writer);
        return writer.toString();
    }
}
