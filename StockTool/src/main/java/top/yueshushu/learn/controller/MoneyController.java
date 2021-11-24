package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.mode.ro.MoneyRo;
import top.yueshushu.learn.mode.vo.MoneyVo;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.service.MoneyService;

/**
 * @ClassName:MoneyController
 * @Description 小工具--计算金额
 * @Author 岳建立
 * @Date 2021/11/6 10:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/money")
public class MoneyController {
    @Autowired
    private MoneyService moneyService;
    /**
     * 获取展示相关的信息
      * @return
     */
    @PostMapping("/calcMoney")
    public OutputResult calcMoney(@RequestBody MoneyRo moneyRo){
        return moneyService.calcMoney(moneyRo);
    }
}
