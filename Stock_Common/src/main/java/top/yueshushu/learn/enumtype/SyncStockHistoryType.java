package top.yueshushu.learn.enumtype;

import org.springframework.util.Assert;

/**
 * 同步股票的历史信息时,目前支持的处理时间范围
 * @author 两个蝴蝶飞
 */
public enum SyncStockHistoryType {
    SELF(0,"自定义"),
    WEEK(1,"一周内"),
    MONTH(2,"一年内"),
    YEAR(3,"一年内"),
    THREE_YEAR(4,"三年内"),
    FIVE_YEAR(5,"五年内"),
    TEN_YEAR(6,"十年内"),
    ALL(7,"全部日期");

    private Integer code;

    private String desc;

    private SyncStockHistoryType(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }

    /**
     * 获取对应的交易所的类型
     * @param code
     * @return
     */
    public static SyncStockHistoryType getSyncRangeType(int code){
        Assert.notNull(code,"code编号不能为空");
        for(SyncStockHistoryType exchangeType: SyncStockHistoryType.values()){
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
