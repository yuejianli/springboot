package top.yueshushu.message.weixin.model;

import lombok.Data;

/**
 * @Description 基本消息处理
 * @Author yuejianli
 * @Date 2022/6/4 16:15
 **/
@Data
public class BaseMessage {
    /**
     * 否 成员ID列表（消息接收者，多个接收者用‘|'分隔，最多支持1000个）。
     * 特殊情况：指定为@all，则向该企业应用的全部成员发送
     */
    private String touser;
    /**
     * 否 部门ID列表，多个接收者用‘|'分隔，最多支持100个。
     * 当touser为@all时忽略本参数
     */
    private String toparty;
    /**
     * 否 标签ID列表，多个接收者用‘|'分隔，最多支持100个。当touser为@all时忽略本参数
     */
    private String totag;
    /**
     * 是 消息类型
     */
    private String msgtype;
    /**
     * 是 企业应用的id，整型。可在应用的设置页面查看
     */
    private int agentid;
}
