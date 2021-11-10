
var MONEY_URL="../money/calcMoney";

$(function(){
    //清空展示信息
   // $(".info").text("　　　　　　　　　　　　　　　　　");
})

//重置
$("#reset").click(function(){
    $("#code").val("sh002415");
    $("#price").val("50.810");
    $("#number").val("100");
    $("#make_money").val("56.85");
    $("#make_price").val("51.530");
    $("#make_proportion").val("1.12");
    $("#platform_fee").val("0.03");
    $("#tradingArea").val("0");
    $("#tradingArea").selectpicker('refresh');

    $("#nameType").val("0");
    $("#nameType").selectpicker('refresh');

    //清空展示信息
    $(".info").text(" ");
});
//金额计算
$("#money_submit").click(function(){
    const info = getFillInfo(1);
    //进行验证
    if(!validateSubmit(info)){
        return ;
    }
    $.ajax({
        async:false,
        type:"post",
        url:MONEY_URL,
        data:JSON.stringify(info),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(data){
            showInfo(data.data.result);
        }
    });
});


//价格计算
$("#price_submit").click(function(){
    const info = getFillInfo(2);
    //进行验证
    if(!validateSubmit(info)){
        return ;
    }
    $.ajax({
        async:false,
        type:"post",
        url:MONEY_URL,
        data:JSON.stringify(info),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(data){
            showInfo(data.data.result);
        }
    });
});

//比例计算
$("#proportion_submit").click(function(){
    const info = getFillInfo(3);
    //进行验证
    if(!validateSubmit(info)){
        return ;
    }
    $.ajax({
        async:false,
        type:"post",
        url:MONEY_URL,
        data:JSON.stringify(info),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(data){
            showInfo(data.data.result);
        }
    });
});
// 具体的功能
$("#show").click(function(){
    Flavr.falert("暂未实现");
})

/**
 * 获取填充的用户信息
 * @param type
 * @returns 返回填充信息的对象
 */
function getFillInfo(type){
    //获取用户填入的信息
    const code = $("#code").val();
    const price = $("#price").val();
    const number = $("#number").val();
    const platformFee = $("#platform_fee").val();
    const tradingArea = $("#tradingArea").val();
    const nameType = $("#nameType").val();
    const makeMoney = $("#make_money").val();
    const makePrice = $("#make_price").val();
    const makeProportion = $("#make_proportion").val();


    const info = {
        "code": code,
        "price": price,
        "number": number,
        "platformFee":platformFee,
        "tradingArea":tradingArea,
        "nameType":nameType,
        "makeMoney": makeMoney,
        "makePrice": makePrice,
        "makeProportion":makeProportion,
        "type":type
    };
    return info;
}

/**
 * 验证金额提示信息
 * @param info
 * @returns {boolean}
 */
function validateSubmit(info){
   if(!validateCode(info.code)){
       return false;
   }
    if(!validatePrice(info.price)){
        return false;
    }
    if(!validateNumber(info.number)){
        return false;
    }
    if(!validatePlatformFee(info.platformFee)){
        return false;
    }
    if(!validateTradingArea(info.tradingArea)){
        return false;
    }
    if(info.type==1&&!validateMakeMoney(info.makeMoney)){
        return false;
    }
    if(info.type==2&&!validateMakePrice(info.makePrice)){
        return false;
    }
    if(info.type==3&&!validateMakeProportion(info.makeProportion)){
        return false;
    }
    return true;
}
/**
 * 验证股票的编号
 * @param code 股票的编号
 * @returns {boolean} 验证通过，返回 true, 验证不通过，返回false
 */
function validateCode(code){
    //验证股票代码
    if(isEmpty(code)){
        Flavr.falert("股票代码不能为空");
        return false;
    }
    //获取前两位的值
    const prefix = code.substr(0, 2);
    const realCode = code.substr(2);
    if(!(prefix=="sh"||prefix=="sz")){
        Flavr.falert("股票代码前缀地区必须以 sh(上海) 或者 sz(深圳) 开头");
        return false;
    }
    if(isEmpty(realCode)||realCode.length!=6||!/^\d{6}$/.test(realCode)){
        Flavr.falert("股票的实际代码编号必须为六位纯数字");
        return false;
    }
    return true;
}

/**
 * 验证股票的价格
 * @param price
 * @returns {boolean}
 */
function validatePrice(price){
    //验证股票价格
    if(isEmpty(price)){
        Flavr.falert("买入时的股票价格不能为空");
        return false;
    }
    const regs = /^(\d+)(\.\d+)?$/;
    if(!regs.test(price)){
        Flavr.falert("买入时的股票价格必须为正数");
        return false;
    }
    return true;
}

/**
 * 验证股票的买入数量 必须为100的倍数
 * @param price
 * @returns {boolean}
 */
function validateNumber(number){
    //验证股票买入数量
    if(isEmpty(number)){
        Flavr.falert("买入的股票数不能为空");
        return false;
    }
    const regs = /^\d+$/;
    if(!regs.test(number)){
        Flavr.falert("买入时的股票数必须为正整数");
        return false;
    }
    if(parseInt(number)%100!=0){
        Flavr.falert("买入时的股票数必须是100的倍数");
        return false;
    }
    return true;
}

/**
 * 验证平台手续费
 * @param platformFee
 */
function validatePlatformFee(platformFee){
    //验证想赚的钱数
    if(isEmpty(platformFee)){
        Flavr.falert("请输入平台佣金比例");
        return false;
    }
    const regs = /^(\d+)(\.\d+)?$/;
    if(!regs.test(platformFee)){
        Flavr.falert("平台佣金比例必须为正数");
        return false;
    }
    //转换成 double
    const platForm=parseFloat(platformFee);
    if(platForm<0.02||platForm>0.03){
        Flavr.falert("平台佣金比例在 0.02%~0.03%之间");
        return false;
    }
    return true;
}

/**
 * 验证交易地区，用于处理 通讯费
 * @param tradingArea
 * @returns {boolean}
 */
function validateTradingArea(tradingArea){
    //验证想赚的钱数
    if(isEmpty(tradingArea)){
        Flavr.falert("请选择交易的地区");
        return false;
    }
    return true;
}
/**
 * 验证想赚的钱数
 * @param makeMoney
 * @returns {boolean}
 */
function validateMakeMoney(makeMoney){
    //验证想赚的钱数
    if(isEmpty(makeMoney)){
        Flavr.falert("预期所赚金额不能为空");
        return false;
    }
    const regs = /^(\d+)(\.\d+)?$/;
    if(!regs.test(makeMoney)){
        Flavr.falert("预期所赚金额必须为正数,目前暂不支持亏钱计算");
        return false;
    }
    return true;
}

/**
 * 验证股票的价格
 * @param price
 * @returns {boolean}
 */
function validateMakePrice(price){
    //验证股票价格
    if(isEmpty(price)){
        Flavr.falert("时的股票价格不能为空");
        return false;
    }
    const regs = /^(\d+)(\.\d+)?$/;
    if(!regs.test(price)){
        Flavr.falert("买入时的股票价格必须为正数");
        return false;
    }
    return true;
}

/**
 * 验证赚钱的比例
 * @param price
 * @returns {boolean}
 */
function validateMakeProportion(proportion){
    //验证股票价格
    if(isEmpty(proportion)){
        Flavr.falert("预期比例不能为空");
        return false;
    }
    const regs = /^(\d+)(\.\d+)?$/;
    if(!regs.test(proportion)){
        Flavr.falert("预期比例必须为正数");
        return false;
    }
    return true;
}

/**
 * 展示结果的相关信息
 * @param data
 */
function showInfo(result){
    //展示手续费等信息
　　 $("#buyCharge").text(getText(result.buyCharge));
　　 $("#buyTransferFee").text(getText(result.buyTransferFee));
　　 $("#buyCommunications").text(getText(result.buyCommunications));
　　 $("#sellStampDuty").text(getText(result.sellStampDuty));
　　 $("#sellCharge").text(getText(result.sellCharge));
　　 $("#sellTransferFee").text(getText(result.sellTransferFee));
　　 $("#sellCommunications").text(getText(result.sellCommunications));
　　 $("#totalBuyCharge").text(getText(result.totalBuyCharge));
　　 $("#totalSellCharge").text(getText(result.totalSellCharge));
　　 $("#totalCharge").text(getText(result.totalCharge));
    //展示操作的信息
    $("#buyMoney").text(getText(result.buyMoney));
    $("#sellMoney").text(getText(result.sellMoney));
    $("#buyActualMoney").text(getText(result.buyActualMoney));
    $("#noSellMoney").text(getText(result.noSellMoney));
    $("#destMoney").text(getText(result.destMoney));
    $("#realMoney").text(getText(result.realMoney));
    $("#realPrice").text(getText(result.realPrice));
    $("#realProportion").text(getText(result.realProportion));
}

function getText(value){
    return "   "+value+"  ";
}



//判断字符是否为空的方法
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}