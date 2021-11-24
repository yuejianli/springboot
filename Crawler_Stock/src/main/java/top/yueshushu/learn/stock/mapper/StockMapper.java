package top.yueshushu.learn.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.yueshushu.learn.model.info.StockInfo;
import top.yueshushu.learn.stock.pojo.Stock;

import java.util.List;

/**
 * @ClassName:StockMapper
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/14 0:03
 * @Version 1.0
 **/
public interface StockMapper extends BaseMapper<Stock> {
    /**
     * 根据编码和类型进行查询
     *
     * @param code
     * @param exchange
     * @return
     */
    List<Stock> selectByCodeAndType(@Param("code") String code, @Param("exchange") Integer exchange);

    /**
     * 全部删除
     */
    void deleteAll();
}
