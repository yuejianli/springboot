package top.yueshushu.message.weixin.model;

import lombok.Data;

/**
 * @Description 普通微信文本信息
 * @Author yuejianli
 * @Date 2022/6/4 16:16
 **/
@Data
public class WxText {
    /**
     * 是    消息内容，最长不超过2048个字节
     */
    private String content;
}
