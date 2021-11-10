package top.yueshushu.learn.util;

import org.springframework.util.Assert;
import top.yueshushu.learn.enumtype.ExchangeType;
import top.yueshushu.learn.enumtype.NameOperationType;
import top.yueshushu.learn.enumtype.TradingAreaType;
import top.yueshushu.learn.model.dto.PoundageCalcDto;
import top.yueshushu.learn.model.dto.PoundageDto;
import top.yueshushu.learn.system.SystemConst;

import java.math.BigDecimal;

/**
 * @ClassName:PoundageCalcUtil
 * @Description 交易费用计算工具类
 * @Author 岳建立
 * @Date 2021/11/6 16:36
 * @Version 1.0
 **/
public class PoundageCalcUtil {
    /**
     * 计算交易的手续费
     * @param poundageDto
     * @return
     */
    public static PoundageCalcDto calc(PoundageDto poundageDto){
        PoundageCalcDto result=new PoundageCalcDto();
        result.setTradingAreaType(poundageDto.getTradingAreaType());
        result.setPlatformFee(poundageDto.getPlatformFee());
        //计算买入的
        calcBuy(poundageDto,result);
        //计算卖出的手续费
        calcSell(poundageDto,result);

        //处理合计
        calcTotal(result);
        return result;
    }

    /**
     * 计算合计的信息
     * @param result
     */
    private static void calcTotal(PoundageCalcDto result) {
        //计算总的买入手续费
        BigDecimal totalBuyCharge=
                result.getBuyCharge().add(
                        result.getBuyCommunications()
                ).add(result.getBuyTransferFee());
        BigDecimal totalSellCharge=
                result.getSellCharge().add(
                        result.getSellStampDuty()
                ).add(result.getSellCommunications())
                .add(result.getSellTransferFee());
        //计算总计
        result.setTotalBuyCharge(totalBuyCharge);
        result.setTotalSellCharge(totalSellCharge);
        result.setTotalCharge(
                totalBuyCharge.add(totalSellCharge)
        );
    }

    /**
     * 计算卖出的费用
     * @param poundageDto
     * @param result
     */
    private static void calcSell(PoundageDto poundageDto, PoundageCalcDto result) {
        //计算卖出的印花税
        result.setSellStampDuty(calcSellStampDuty(poundageDto));
        //计算卖出的佣金费用
        result.setSellCharge(calcSellCharge(poundageDto));
        //计算卖出过户费
        result.setSellTransferFee(calcSellTransferFee(poundageDto.getSellNumber(),
                poundageDto.getNameOperationType(),
                poundageDto.getExchangeType()));
        //计算卖出通讯费
        result.setSellCommunications(calcSellCommunications(poundageDto.getTradingAreaType()));
    }

    /**
     * 计算卖出的印花税
     * @param poundageDto
     * @return
     */
    private static BigDecimal calcSellStampDuty(PoundageDto poundageDto) {
        //1. 获取应该收取的费用
        return poundageDto.getSellPrice().multiply(
                new BigDecimal(poundageDto.getSellNumber())
        ).multiply(SystemConst.DEFAULT_STAMP_DUTY);
    }
    /**
     * 计算卖出通讯费
     * @param tradingAreaType
     * @return
     */
    private static BigDecimal calcSellCommunications(TradingAreaType tradingAreaType) {
        return calcBuyCommunications(tradingAreaType);
    }

    /**
     * 计算卖出过户费
     * @param number
     * @return
     */
    private static BigDecimal calcSellTransferFee(Integer number,
                                                  NameOperationType nameOperationType,
                                                  ExchangeType exchangeType) {
        return calcBuyTransferFee(number,nameOperationType,exchangeType);
    }

    /**
     * 计算卖出手续费
     * @param poundageDto
     * @return
     */
    private static BigDecimal calcSellCharge(PoundageDto poundageDto) {
        //1. 获取应该收取的费用
        BigDecimal result = poundageDto.getSellPrice().multiply(
                new BigDecimal(poundageDto.getSellNumber())
        ).multiply(poundageDto.getPlatformFee().divide(SystemConst.DEFAULT_FULL));
        //没有最低收费标准高
        if(SystemConst.DEFAULT_SELL_BLOW.compareTo(result)>0){
            return SystemConst.DEFAULT_SELL_BLOW;
        }
        return result;
    }

    /**
     * 计算买入的费用
     * @param poundageDto
     * @param result
     */
    private static void calcBuy(PoundageDto poundageDto, PoundageCalcDto result) {
        //计算买入的佣金费用
        result.setBuyCharge(calcBuyCharge(poundageDto));
        //计算买入过户费
        result.setBuyTransferFee(calcBuyTransferFee(poundageDto.getBuyNumber(),poundageDto.getNameOperationType(),
                poundageDto.getExchangeType()));
        //计算买入通讯费
        result.setBuyCommunications(calcBuyCommunications(poundageDto.getTradingAreaType()));
    }

    /**
     * 计算买入的通讯费
     * @param tradingAreaType
     * @return
     */
    private static BigDecimal calcBuyCommunications(TradingAreaType tradingAreaType) {
        Assert.notNull(tradingAreaType,"传入的交易地区不能为空");
        BigDecimal result=null;
        switch(tradingAreaType){
            case AREA:{
                result=SystemConst.DEFAULT_Communications;
                break;
            }
            case NO_AREA:{
                result=SystemConst.DEFAULT_OTHER_Communications;
                break;
            }
            default:{
                result=SystemConst.DEFAULT_EMPTY;
                break;
            }
        }
        return result;
    }
    /**
     * 计算买入的过户费
     * @param number
     * @param exchangeType 类型
     * @return
     */
    private static BigDecimal calcBuyTransferFee(Integer number, NameOperationType nameOperationType,
                                                 ExchangeType exchangeType) {
        Assert.notNull(exchangeType,"交易所的类型不能为空");
        Assert.notNull(nameOperationType,"交易的类型不能为空");
        if(NameOperationType.NO_UPDATE.getCode().equals(nameOperationType.getCode())){
            return SystemConst.DEFAULT_EMPTY;
        }
        if(!(ExchangeType.SH.getCode().equals(exchangeType.getCode()))){
            return SystemConst.DEFAULT_EMPTY;
        }
        return SystemConst.DEFAULT_TRANSFER_FEE
                .multiply(
                        BigDecimal.valueOf((number/1000)+1)
                );
    }

    /**
     * 计算买入手续费
     * @param poundageDto
     * @return
     */
    private static BigDecimal calcBuyCharge(PoundageDto poundageDto) {
        //1. 获取应该收取的费用
        BigDecimal result = poundageDto.getBuyPrice().multiply(
                new BigDecimal(poundageDto.getBuyNumber())
        ).multiply(poundageDto.getPlatformFee().divide(SystemConst.DEFAULT_FULL));
        //没有最低收费标准高
        if(SystemConst.DEFAULT_BUY_BLOW.compareTo(result)>0){
            return SystemConst.DEFAULT_BUY_BLOW;
        }
        return result;
    }
}
