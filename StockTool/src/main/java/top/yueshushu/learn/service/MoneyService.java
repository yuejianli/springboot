package top.yueshushu.learn.service;

import top.yueshushu.learn.mode.ro.MoneyRo;
import top.yueshushu.learn.mode.vo.MoneyVo;
import top.yueshushu.learn.response.OutputResult;

/**
 * @ClassName:MoneyService
 * @Description 金额计算服务
 * @Author 岳建立
 * @Date 2021/11/6 16:00
 * @Version 1.0
 **/
public interface MoneyService {
    /**
     * 计算金额
     * @param moneyRo
     * @return
     */
    OutputResult calcMoney(MoneyRo moneyRo);
}
