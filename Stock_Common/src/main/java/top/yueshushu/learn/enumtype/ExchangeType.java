package top.yueshushu.learn.enumtype;

import org.springframework.util.Assert;

/**
 * 交易所的类型
 * @author 两个蝴蝶飞
 */
public enum ExchangeType {
    /**
     * 上海
     */
    SH(1,"sh"),
    /**
     * 深圳
     */
    SZ(0,"sz"),
    /**
     * 北京
     */
    BJ(2,"bj"),
    /**
     * 其它的非上述地区
     */
    OTHER(10,"other");

    private Integer code;

    private String desc;

    private ExchangeType(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }

    /**
     * 获取对应的交易所的类型
     * @param code
     * @return
     */
    public static ExchangeType getExchangeType(int code){
        Assert.notNull(code,"code编号不能为空");
        for(ExchangeType exchangeType:ExchangeType.values()){
            if(exchangeType.code.equals(code)){
                return exchangeType;
            }
        }
        return null;
    }

    public static ExchangeType prefix(String prefix){
        Assert.notNull(prefix,"股票代码前缀不能为空");
        for(ExchangeType exchangeType:ExchangeType.values()){
            if(exchangeType.getDesc().equalsIgnoreCase(prefix)){
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
