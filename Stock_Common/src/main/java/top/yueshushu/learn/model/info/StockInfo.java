package top.yueshushu.learn.model.info;
import lombok.Data;

@Data
/**
 * 股票的相关信息
 */
public class StockInfo extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 股票的代码 如 002415
     */
    private String code;
    /**
     * 股票的名称 ，如 海康威视
     */
    private String name;
    /**
     * 所属的交易所类型
     */
    private Integer exchange;
    /**
     * 股票的类型
     */
    private String fullCode;

}
