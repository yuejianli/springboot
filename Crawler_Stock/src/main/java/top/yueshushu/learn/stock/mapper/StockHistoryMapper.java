package top.yueshushu.learn.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.yueshushu.learn.ro.stock.StockRo;
import top.yueshushu.learn.stock.pojo.StockHistory;
import top.yueshushu.learn.vo.stock.StockHistoryVo;

import java.util.Date;
import java.util.List;

/**
 * @ClassName:StockHistoryMapper
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/14 11:23
 * @Version 1.0
 **/
public interface StockHistoryMapper extends BaseMapper<StockHistory> {
    /**
     * 根据股票的code 查询股票的相关信息
     *
     * @param code
     * @return
     */
    List<StockHistoryVo> getStockHistory(@Param("code") String code);

    void deleteAsyncData(@Param("code") String code, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
