package top.yueshushu.mail.service;

import java.util.List;
import java.util.Map;

/**
 * 邮件发送的接口信息
 *
 * @author yuejianli
 * @date 2022-06-09
 */

public interface EmailService {
	/**
	 * 发送普通的文本消息
	 *
	 * @param toArr   发送人， 之间用 ,号分隔
	 * @param subject 发送主题
	 * @param content 发送的内容, 普通文本内容
	 */
	boolean sendSimpleMail(String[] toArr, String subject, String content);
	
	/**
	 * 发送 HTML 消息
	 *
	 * @param toArr   发送人， 之间用 ,号分隔
	 * @param subject 发送主题
	 * @param content 发送的内容 ,html 形式
	 */
	boolean sendHtmlMail(String[] toArr, String subject, String content);
	/**
	 * 发送 携带附件的邮件
	 * @param toArr   发送人， 之间用 ,号分隔
	 * @param subject 发送主题
	 * @param content 发送的内容 ,html 形式
	 * @param filePaths 附件路径地址集合
	 * @return
	 */
	boolean sendAttachmentsMail(String[] toArr, String subject, String content,
								List<String> filePaths);

	/**
	 * 发送邮件 velocity 模板邮件
	 *  @param toArr                发送人
	 * @param subject              主题
	 * @param dataMap              发送模板邮件填充数据
	 * @param templateName 模板名称
	 */
	boolean sendVelocityMail(String[] toArr, String subject, Map<String, Object> dataMap, String templateName);
}
