package top.yueshushu.learn.vo.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName:StockHistory
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/14 11:06
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockHistoryVo implements Serializable {
    private Integer id;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date currDate;
    private String code;
    private String name;
    private BigDecimal closingPrice;
    private BigDecimal highestPrice;
    private BigDecimal lowestPrice;
    private BigDecimal openingPrice;
    private BigDecimal yesClosingPrice;
    private BigDecimal amplitude;
    private BigDecimal amplitudeProportion;
    private long tradingVolume;
    private BigDecimal tradingValue;
    private Integer flag;
}
