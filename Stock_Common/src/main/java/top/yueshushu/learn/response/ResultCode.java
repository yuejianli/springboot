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
    FAIL(50000,"操作失败");


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
