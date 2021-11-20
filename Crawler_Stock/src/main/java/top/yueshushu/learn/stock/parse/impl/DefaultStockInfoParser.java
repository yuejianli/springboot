package top.yueshushu.learn.stock.parse.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import top.yueshushu.learn.model.info.StockInfo;
import top.yueshushu.learn.stock.entity.DownloadStockInfo;
import top.yueshushu.learn.stock.parse.StockInfoParser;
import top.yueshushu.learn.stock.util.StockUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 股票转换信息实现
 * @author 12905
 */
@Component("defaultStockInfoParser")
public class DefaultStockInfoParser implements StockInfoParser {

    @Override
    public List<DownloadStockInfo> parseStockInfoList(String content) {
        //将内容转换成json
        JSONObject jsonObject = JSONObject.parseObject(content);
        //获取里面的data.diff 内容，是个列表对象
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("diff");
        //处理内容
        List<DownloadStockInfo> result = new ArrayList<>(32);
        jsonArray.stream().forEach(
                n->{
                    JSONObject tempObject = JSONObject.parseObject(n.toString());
                    DownloadStockInfo downloadStockInfo = new DownloadStockInfo();
                    downloadStockInfo.setCode(tempObject.getString("f12"));
                    downloadStockInfo.setName(tempObject.getString("f14"));

                    //处理类型  1为上海   0为深圳
                    int type=tempObject.getInteger("f13");
                    //进行处理
                    downloadStockInfo.setExchange(type);
                    //设置股票的全称
                    downloadStockInfo.setFullCode(StockUtil.getFullCode(type,downloadStockInfo.getCode()));

                    result.add(downloadStockInfo);
                }
        );
        return result;
    }
}
