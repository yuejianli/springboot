package top.yueshushu.learn.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.ro.stock.StockRo;
import top.yueshushu.learn.stock.pojo.Stock;

/**
 * @InterfaceName StockBaseService
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/14 0:04
 * @Version 1.0
 **/
public interface StockService extends IService<Stock>{
    /**
     * 查询股票的相关信息
     * @param stockRo
     * @return
     */
    OutputResult list(StockRo stockRo);

    /**
     * 删除所有的数据
     */
    void deleteAll();

    /**
     * 查询股票的信息
     * @param code
     * @return
     */
    Stock selectByCode(String code);
}
