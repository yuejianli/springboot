package top.yueshushu.learn.enumtype;

/**
 * 交易地的类型
 * @author 两个蝴蝶飞
 */
public enum TradingAreaType {
    /**
     * 深圳
     */
    WEB(0,"网络委托"),
    /**
     * 上海
     */
    AREA(1,"电话委托--上海,深圳等地"),
    /**
     * 上海
     */
    NO_AREA(2,"电话委托--上海,深圳等地");

    private Integer code;

    private String desc;

    private TradingAreaType(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }

    /**
     * 获取对应的交易所的类型
     * @param code
     * @return
     */
    public static TradingAreaType getExchangeType(int code){
        for(TradingAreaType exchangeType: TradingAreaType.values()){
            if(exchangeType.code==code){
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
