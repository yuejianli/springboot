package top.yueshushu.message.weixin.model;

import lombok.Data;

/**
 * @Description 微信通用接口凭证, 获取token信息
 * @Author yuejianli
 * @Date 2022/6/4 16:13
 **/
@Data
public class AccessToken {
    /**
     * 获取到的凭证
     */
    private String token;
    /**
     * 凭证有效时间，单位：秒
     */
    private int expiresIn;

}