package top.yueshushu.learn.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.enumtype.ExchangeType;
import top.yueshushu.learn.enumtype.NameOperationType;
import top.yueshushu.learn.enumtype.TradingAreaType;
import top.yueshushu.learn.mode.ro.MoneyRo;
import top.yueshushu.learn.mode.vo.MoneyVo;
import top.yueshushu.learn.model.dto.PoundageCalcDto;
import top.yueshushu.learn.model.dto.PoundageDto;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.service.MoneyService;
import top.yueshushu.learn.system.SystemConst;
import top.yueshushu.learn.util.BigDecimalUtil;
import top.yueshushu.learn.util.MathUtil;
import top.yueshushu.learn.util.PoundageCalcUtil;

import java.math.BigDecimal;

/**
 * @ClassName:MoneyServiceImpl
 * @Description 金额的实现类
 * @Author 岳建立
 * @Date 2021/11/6 16:01
 * @Version 1.0
 **/
@Service
@Log4j2
public class MoneyServiceImpl implements MoneyService {
    @Override
    public OutputResult calcMoney(MoneyRo moneyRo) {
        //1. 进行验证
        OutputResult validateResponse=validateRo(moneyRo);
        if(null!=validateResponse){
            return validateResponse;
        }
        //2. 组装数据,处理预期的价格,也就是卖的价格.
        Double makePrice=handlerPrice(moneyRo);
        moneyRo.setMakePrice(makePrice);
        //3. 获取数据
        MoneyVo moneyVo=assemblyMoneyVo(moneyRo);
        return OutputResult.success(moneyVo);
    }

    /**
     * 处理预期的价格
     * @param moneyRo
     * @return
     */
    private Double handlerPrice(MoneyRo moneyRo) {
        Double result=0.0d;
        switch (moneyRo.getType()){
            case 2:{
                result=moneyRo.getMakePrice();
                break;
            }
            case 1:{
                result=moneyToPrice(moneyRo);
                break;
            }
            case 3:{
                result=patternToPrice(moneyRo);
                break;
            }
            default:{
                break;
            }
        }
        return result;
    }

    /**
     * 比例转换
     * @param moneyRo
     * @return
     */
    private Double patternToPrice(MoneyRo moneyRo) {
        Double result=moneyRo.getPrice();
        //先求出比例,这是最大的比例.
        Double old = moneyRo.getMakePrice();
        //进行循环处理
        for(Double i=moneyRo.getMakeProportion();i<=100.00;i=i+ SystemConst.DEFAULT_PATTERN_STEP){
            //临时的 makePrice 即临时的期望价格
            Double temp= BigDecimalUtil.toDouble(MathUtil.getNow(moneyRo.getPrice(),i));
            //进行计算
            moneyRo.setMakePrice(temp);
            MoneyVo moneyVo=assemblyMoneyVo(moneyRo);
            if(BigDecimalUtil.toBigDecimal(moneyRo.getMakeProportion())
            .compareTo(BigDecimalUtil.toBigDecimal(moneyVo.getRealProportion()
            .replace("%","")))<=0){
                //往上走一步
                result=temp;
                break;
            }
        }
        moneyRo.setMakePrice(old);
        return result;
    }

    /**
     * 处理，转换.
     * 将金额，转换成对应的卖出价格
     * @param moneyRo
     * @return
     */
    private Double moneyToPrice(MoneyRo moneyRo) {
        Double result=moneyRo.getPrice();
        //先求出比例,这是最大的比例.
       Double maxProportion= (moneyRo.getMakeMoney()/(moneyRo.getPrice()*moneyRo.getNumber()))*100;
        Double old = moneyRo.getMakePrice();
        //进行循环处理
        for(Double i=maxProportion;i<=100.00d;i=i+SystemConst.DEFAULT_PATTERN_STEP){
            //临时的钱
            Double temp=BigDecimalUtil.toDouble(MathUtil.getNow(moneyRo.getPrice(),i));
            //进行计算
            moneyRo.setMakePrice(temp);
            MoneyVo moneyVo=assemblyMoneyVo(moneyRo);
            if(BigDecimalUtil.toBigDecimal(moneyVo.getRealMoney())
                    .compareTo(BigDecimalUtil.toBigDecimal(moneyRo.getMakeMoney()))>=0){
                //往上走一步
                result=temp;
                break;
            }

        }
        moneyRo.setMakePrice(old);
        return result;
    }
    /**
     * 组装数据
     * @param moneyRo
     * @return
     */
    private MoneyVo assemblyMoneyVo(MoneyRo moneyRo) {
        MoneyVo result=new MoneyVo();
        //1. 组装基本的数据
        basicInfo(moneyRo,result);
        //2.计算手续费等相关的信息.
        PoundageCalcDto poundageCalcDto=calcPoundageCalcDto(moneyRo);
        //3. 封装手续费等信息
        chargeInfo(poundageCalcDto,result);
        //4. 处理计算值
        calcInfo(result);
        return result;
    }

    /**
     * 封装基本的数据信息
     * @param moneyRo
     * @param result
     */
    private void basicInfo(MoneyRo moneyRo, MoneyVo result) {
        result.setCode(moneyRo.getCode());
        result.setPrice(moneyRo.getPrice()+"");
        result.setNumber(moneyRo.getNumber());
        result.setMakeMoney(moneyRo.getMakeMoney()+"");
        result.setMakePrice(moneyRo.getMakePrice()+"");
        result.setMakeProportion(moneyRo.getMakeProportion()+"");
        result.setType(moneyRo.getType());
    }

    /**
     * 处理计算的信息
     * @param result
     */
    private void calcInfo(MoneyVo result) {
        BigDecimal buyMoney=BigDecimalUtil.toBigDecimal(
                result.getPrice(),
                result.getNumber()+""
        );
        result.setBuyMoney(
                BigDecimalUtil.toString(
                        buyMoney
                )
        );
        BigDecimal sellMoney=BigDecimalUtil.toBigDecimal(
                result.getMakePrice(),
                result.getNumber()+""
        );

        result.setSellMoney(
                BigDecimalUtil.toString(
                      sellMoney
                )
        );
        BigDecimal destMoney=
                sellMoney.subtract(
                        BigDecimalUtil.toBigDecimal(result.getTotalSellCharge())
                );
        result.setDestMoney(
                BigDecimalUtil.toString(destMoney)
        );
        //实际买入发生金额
        BigDecimal buyActualMoney=
                buyMoney.add(
                        BigDecimalUtil.toBigDecimal(result.getTotalBuyCharge())
                );

        result.setBuyActualMoney(BigDecimalUtil.toString(buyActualMoney));



        BigDecimal realMoney=destMoney.subtract(buyActualMoney);
        result.setRealMoney(BigDecimalUtil.toString(realMoney));

        //不卖的话，现在的赚钱数
        BigDecimal noSellMoney=realMoney.add(
                 BigDecimalUtil.toBigDecimal(result.getTotalSellCharge())
        );
        //不卖的话，赚钱数
        result.setNoSellMoney(BigDecimalUtil.toString(noSellMoney));

        result.setRealPrice(
                BigDecimalUtil.toString(
                     new BigDecimal(result.getMakePrice())
                ));
        //比例
        result.setRealProportion(
                BigDecimalUtil.divPattern(
                        realMoney,buyActualMoney
                )
        );
    }

    private void chargeInfo(PoundageCalcDto poundageCalcDto, MoneyVo result) {
        result.setPlatformFee(BigDecimalUtil.toString(poundageCalcDto.getPlatformFee()));
        result.setTradingArea(poundageCalcDto.getTradingAreaType().getCode());
        //买的属性
        result.setBuyCharge(BigDecimalUtil.toString(poundageCalcDto.getBuyCharge()));
        result.setBuyTransferFee(BigDecimalUtil.toString(poundageCalcDto.getBuyTransferFee()));
        result.setBuyCommunications(BigDecimalUtil.toString(poundageCalcDto.getBuyCommunications()));
        //卖的属性
        result.setSellStampDuty(BigDecimalUtil.toString(poundageCalcDto.getSellStampDuty()));
        result.setSellCharge(BigDecimalUtil.toString(poundageCalcDto.getSellCharge()));
        result.setSellTransferFee(BigDecimalUtil.toString(poundageCalcDto.getSellTransferFee()));
        result.setSellCommunications(BigDecimalUtil.toString(poundageCalcDto.getSellCommunications()));
        //合计
        result.setTotalBuyCharge(BigDecimalUtil.toString(poundageCalcDto.getTotalBuyCharge()));
        result.setTotalSellCharge(BigDecimalUtil.toString(poundageCalcDto.getTotalSellCharge()));
        result.setTotalCharge(BigDecimalUtil.toString(poundageCalcDto.getTotalCharge()));
    }

    /**
     * 获取相应的手续费等信息
     * @param moneyRo
     * @return
     */
    private PoundageCalcDto calcPoundageCalcDto(MoneyRo moneyRo) {
        //1.先构建对应的 PoundageDto 信息
        PoundageDto poundageDto=new PoundageDto();
        poundageDto.setPlatformFee(BigDecimalUtil.toBigDecimal(moneyRo.getPlatformFee()));
        poundageDto.setTradingAreaType(TradingAreaType.getExchangeType(moneyRo.getTradingArea()));
        poundageDto.setBuyPrice(BigDecimalUtil.toBigDecimal(moneyRo.getPrice()));
        poundageDto.setSellPrice(BigDecimalUtil.toBigDecimal(moneyRo.getMakePrice()));
        poundageDto.setBuyNumber(moneyRo.getNumber());
        poundageDto.setSellNumber(moneyRo.getNumber());
        poundageDto.setExchangeType(ExchangeType.prefix(moneyRo.getCode().substring(0,2)));
        poundageDto.setNameOperationType(NameOperationType.getNameType(moneyRo.getNameType()));
        return PoundageCalcUtil.calc(poundageDto);
    }

    /**
     * 进行验证信息  缺少
     * @param moneyRo
     * @return
     */
    private OutputResult validateRo(MoneyRo moneyRo) {

        return null;
    }
}
