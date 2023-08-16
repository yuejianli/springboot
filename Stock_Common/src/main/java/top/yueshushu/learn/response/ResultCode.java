package top.yueshushu.learn.response;

/**
 * @ClassName:ResultCode
 * @Description 返回结果处理
 * @Author 岳建立
 * @Date 2021/11/6 16:28
 * @Version 1.0
 **/
public enum ResultCode {
    SUCCESS(20000,"操作成功"),
    ALERT(30000,"传入信息有误"),
    FAIL(50000,"操作失败"),
    /*关于股票的相关操作*/
    STOCK_ASYNC_FAIL(1000,"同步股票信息失败"),
    STOCK_ASYNC_SUCCESS(1001,"同步股票信息成功"),
    STOCK_HIS_ASYNC_FAIL(1000,"同步股票历史交易信息失败"),
    STOCK_HIS_ASYNC_SUCCESS(1001,"同步股票历史交易信息成功"),
    STOCK_CODE_ERROR(1001,"填入的股票代码错误");

    private Integer code;

    private String desc;

    private ResultCode(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
