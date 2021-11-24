package top.yueshushu.learn.enumtype;

import org.springframework.util.Assert;

/**
 * 股票名称是否更换的类型
 * @author 两个蝴蝶飞
 */
public enum NameOperationType {
    /**
     * 上海
     */
    UPDATE(1,"update"),
    /**
     * 深圳
     */
    NO_UPDATE(0,"no_update");

    private Integer code;

    private String desc;

    private NameOperationType(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }

    /**
     * 获取对应的交易所的类型
     * @param code
     * @return
     */
    public static NameOperationType getNameType(int code){
        Assert.notNull(code,"code编号不能为空");
        for(NameOperationType exchangeType: NameOperationType.values()){
            if(exchangeType.code.equals(code)){
                return exchangeType;
            }
        }
        return null;
    }
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
