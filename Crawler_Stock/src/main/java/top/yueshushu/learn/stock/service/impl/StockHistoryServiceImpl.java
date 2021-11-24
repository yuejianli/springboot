package top.yueshushu.learn.stock.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.page.PageResponse;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.ro.stock.StockRo;
import top.yueshushu.learn.stock.mapper.StockHistoryMapper;
import top.yueshushu.learn.stock.pojo.StockHistory;
import top.yueshushu.learn.stock.service.StockHistoryService;
import top.yueshushu.learn.vo.stock.StockHistoryVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:StockHistoryServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/14 11:24
 * @Version 1.0
 **/
@Service
public class StockHistoryServiceImpl extends ServiceImpl<StockHistoryMapper,
        StockHistory>  implements StockHistoryService {
    @Autowired
    private StockHistoryMapper stockHistoryMapper;
    @Override
    public OutputResult getStockHistory(StockRo stockRo) {
        PageHelper.startPage(stockRo.getPageNum(),stockRo.getPageSize());
       List<StockHistoryVo> stockHistoryList= stockHistoryMapper.getStockHistory(stockRo.getCode());
       PageInfo pageInfo=new PageInfo<StockHistoryVo>(stockHistoryList);
       return OutputResult.success(new PageResponse<>(
               pageInfo.getTotal(),pageInfo.getList()
       ));
    }

    @Override
    public void deleteAsyncData(StockRo stockRo) {
        stockHistoryMapper.deleteAsyncData(stockRo.getCode(),
                DateUtil.parse(
                        stockRo.getStartDate(),"yyyyMMdd"
                ),
                DateUtil.parse(
                        stockRo.getEndDate(),"yyyyMMdd"
                ));
    }
}
