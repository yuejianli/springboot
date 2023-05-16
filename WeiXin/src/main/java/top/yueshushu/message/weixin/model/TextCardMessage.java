package top.yueshushu.message.weixin.model;

import lombok.Data;

/**
 * @Description TextCard 类型，支持 html 标签
 * @Author yuejianli
 * @Date 2022/6/4 16:17
 **/
@Data
public class TextCardMessage extends BaseMessage {
	/**
	 * 放置内容
	 */
	private WxTextCard textcard;
}
