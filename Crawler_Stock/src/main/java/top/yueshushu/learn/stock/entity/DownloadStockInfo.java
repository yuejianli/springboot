package top.yueshushu.learn.stock.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:DownloadStockInfo
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/13 21:07
 * @Version 1.0
 **/
@Data
public class DownloadStockInfo implements Serializable {
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
     * 股票编码的全称
     */
    private String fullCode;
}
