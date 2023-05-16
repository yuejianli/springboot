package top.yueshushu.mail.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.yueshushu.mail.service.EmailService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
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
public class EmailServiceImpl implements EmailService {
	@Value("${spring.mail.username}")
	private String from;
	@Resource
	private JavaMailSender javaMailSender;


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
	public boolean sendSimpleMail(String[] toArr, String subject, String content) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(toArr);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(content);
		simpleMailMessage.setFrom(from);
		// 发送时间
		simpleMailMessage.setSentDate(new Date());
		//发送信息
		try {
			javaMailSender.send(simpleMailMessage);
			return true;
		} catch (Exception e) {
			log.error("componentAndSendReqeust simple mail error", e);
			return false;
		}
	}
	
	@Override
	public boolean sendHtmlMail(String[] toArr, String subject, String content) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(toArr);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(content, true);
			mimeMessageHelper.setFrom(from);
			//发送信息
			javaMailSender.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
            log.error("componentAndSendReqeust mail error ", e);
			return false;
		}
	}

	/**
	 * 附件邮件
	 * @param toArr 接收者邮件
	 * @param subject 邮件主题
	 * @param content HTML内容
	 * @param filePaths 附件路径
	 */
	@Override
	public boolean sendAttachmentsMail(String[] toArr, String subject, String content,
									   List<String> filePaths) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {

			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(toArr);
			helper.setSubject(subject);
			helper.setText(content, true);
			helper.setFrom(from);

			if (!CollectionUtils.isEmpty(filePaths)){
				for (String filePath : filePaths) {
					FileSystemResource file = new FileSystemResource(new File(filePath));
					String fileName = file.getFilename();
					// 可以添加多个复杂
					helper.addAttachment(fileName, file);
				}
			}
			javaMailSender.send(message);
			return true;
		} catch (MessagingException e) {
			log.error("componentAndSendReqeust mail error ", e);
			return false;
		}
	}

	@Override
    public boolean sendVelocityMail(String[] toArr, String subject, Map<String, Object> dataMap, String templateName) {
        try {
            String velocityMailText = getVelocityMailText(dataMap,templateName);
            return sendHtmlMail(toArr, subject, velocityMailText);
        } catch (Exception ex) {
            log.error(">>>componentAndSendReqeust email is error,", ex);
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
