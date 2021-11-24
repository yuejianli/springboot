package top.yueshushu.learn.enumtype;

import org.springframework.util.Assert;

/**
 * K线类型
 * @author 两个蝴蝶飞
 */
public enum KType {
    /**
     * 分钟K线
     */
    MIN(1,"minly"),
    /**
     * 天K线
     */
    DAY(2,"dayly"),
    /**
     * 周K线
     */
    WEEK(3,"weekly"),
    /**
     * 月K线
     */
    MONTH(4,"monthly");

    private Integer code;

    private String desc;

    private KType(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }

    /**
     * 获取对应的交易所的类型
     * @param code
     * @return
     */
    public static KType getKType(int code){
        Assert.notNull(code,"code编号不能为空");
        for(KType exchangeType: KType.values()){
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
