package top.yueshushu.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.yueshushu.message.service.WeiXinService;
import top.yueshushu.message.weixin.model.TextCardMessage;
import top.yueshushu.message.weixin.model.TextMessage;
import top.yueshushu.message.weixin.model.WxText;
import top.yueshushu.message.weixin.model.WxTextCard;
import top.yueshushu.message.weixin.util.WeChatUtil;

import javax.annotation.PostConstruct;
import java.io.StringWriter;
import java.text.MessageFormat;
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
public class WeiXinServiceImpl implements WeiXinService {
	private static String sendMessage_url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={0}";
	@Value("${weixin.corpId}")
	private String corpId;
	@Value("${weixin.coprsecret}")
	private String coprsecret;
	@Value("${weixin.agentId}")
	private Integer agentId;

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
	public String sendSimpleText(String[] toArr, String content) {
		if (ArrayUtils.isEmpty(toArr)){
			return null;
		}
		// 1. 获取 token
		String accessToken = getWeiXinToken();
		// 2 构建普通文本对象
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		TextMessage message = new TextMessage();
		// 1.1非必需
		// 不区分大小写
		message.setTouser(StringUtils.join(toArr,"|"));
		// 1.2必需
		message.setMsgtype("text");
		message.setAgentid(agentId);
		WxText wxText = new WxText();
		wxText.setContent(content);
		message.setText(wxText);
		String jsonMessage = gson.toJson(message);
		// 3. 发送 json 形式的获取，获取响应信息
		return messageResponse(accessToken, jsonMessage);
	}

	private String getVelocityMailText(Map<String, Object> dataMap, String templateName) {
		VelocityContext velocityContext = new VelocityContext(dataMap);
		StringWriter writer = new StringWriter();
		velocityEngine.mergeTemplate(templateName, "UTF-8", velocityContext, writer);
		return writer.toString();
	}

	@Override
	public String sendVelocityText(String title, String[] toArr, Map<String, Object> dataMap, String templateName) {
		if (ArrayUtils.isEmpty(toArr)){
			return null;
		}
		// 1.获取access_token:根据企业id和应用密钥获取access_token,并拼接请求url
		String accessToken = getWeiXinToken();
		// 2.获取发送对象，并转成json
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		TextCardMessage textCardMessage = new TextCardMessage();
		// 1.1非必需
		// 不区分大小写
		textCardMessage.setTouser(StringUtils.join(toArr,"|"));
		//message.setToparty("1");
		//message.getTouser(totag);
		// 1.2必需
		textCardMessage.setMsgtype("textcard");
		textCardMessage.setAgentid(agentId);
		WxTextCard wxTextCard = new WxTextCard();
		wxTextCard.setTitle(title);
		wxTextCard.setDescription(getVelocityMailText(dataMap,templateName));
		wxTextCard.setUrl("https://www.yueshushu.top");
		textCardMessage.setTextcard(wxTextCard);
		String jsonMessage = gson.toJson(textCardMessage);
		// 3.获取请求的url
		return messageResponse(accessToken, jsonMessage);
	}

	/**
	 * 获取微信登录的token
	 * 最好放置在缓存里面，避免重复获取.
	 */
	public String getWeiXinToken() {
		return WeChatUtil.getAccessToken(corpId, coprsecret).getToken();
	}

	/**
	 * 将消息通过企业微信发送给相应的用户
	 *
	 * @param accessToken token信息
	 * @param jsonMessage 发送的消息
	 */
	public String messageResponse(String accessToken, String jsonMessage) {
		String url = MessageFormat.format(sendMessage_url, accessToken);
		// 4.调用接口，发送消息
		JSONObject jsonObject = WeChatUtil.httpRequest(url, "POST", jsonMessage);
		// 4.错误消息处理
		if (null != jsonObject) {
			if (0 != jsonObject.getInteger("errcode")) {
				log.info("消息发送失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return jsonObject.toString();
	}
}
