package top.yueshushu.learn.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.yueshushu.learn.model.info.StockInfo;
import top.yueshushu.learn.page.PageResponse;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.ro.stock.StockRo;
import top.yueshushu.learn.stock.mapper.StockMapper;
import top.yueshushu.learn.stock.pojo.Stock;
import top.yueshushu.learn.stock.service.StockService;

import java.util.List;

/**
 * @ClassName:StockBaseServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/14 0:04
 * @Version 1.0
 **/
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {
    @Autowired
    private StockMapper stockMapper;
    @Override
    public OutputResult list(StockRo stockRo) {
        PageHelper.startPage(stockRo.getPageNum(),stockRo.getPageSize());
        List<Stock> stockInfoList= stockMapper.selectByCodeAndType(stockRo.getCode(),stockRo.getExchange());
        PageInfo pageInfo=new PageInfo<Stock>(stockInfoList);
       return OutputResult.success(new PageResponse<Stock>(pageInfo.getTotal(),
               pageInfo.getList()));
    }

    @Override
    public void deleteAll() {
       stockMapper.deleteAll();
    }

    @Override
    public Stock selectByCode(String code) {
        List<Stock> stockList = stockMapper.selectByCodeAndType(code, null);
        if(CollectionUtils.isEmpty(stockList)){
            return null;
        }
        return stockList.get(stockList.size()-1);
    }
}
