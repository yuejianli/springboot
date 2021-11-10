package top.yueshushu.learn.response;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:OutputResult
 * @Description 返回的响应实体信息
 * @Author 岳建立
 * @Date 2021/1/1 10:09
 * @Version 1.0
 **/
@Data
public class OutputResult<T> implements Serializable {
    /**
     * @param code 响应代码
     * @param message 响应信息
     * @param exceptionMessage 异常信息
     * @param data 响应的数据
     */
    private Integer code;
    private String message;
    private String exceptionMessage;
    private Map<String,T> data=new HashMap<String,T>();

    private static ResultCode DEFAULT_SUCCESS=ResultCode.SUCCESS;
    private static ResultCode DEFAULT_ALERT=ResultCode.ALERT;
    private static ResultCode DEFAULT_FAIL=ResultCode.FAIL;

    /**
     * 构造方法 私有。 避免外部构造
     */
    private OutputResult(){

    }

    /**
     * 成功，不响应数据
     * @return
     */
    public static OutputResult success(){
        OutputResult outputResult=new OutputResult();
        outputResult.code=DEFAULT_SUCCESS.getCode();
        outputResult.message=DEFAULT_SUCCESS.getDesc();
        outputResult.data.put("result","{}");
        return outputResult;
    }

    /**
     * 成功
     * @param data  要响应的数据
     * @return
     */
    public static  OutputResult success(Object data){
        OutputResult outputResult=new OutputResult();
        outputResult.code=DEFAULT_SUCCESS.getCode();
        outputResult.message=DEFAULT_SUCCESS.getDesc();
        outputResult.data.put("result",data);
        return outputResult;
    }

    /**
     * 警告，不响应数据
     * @return
     */
    public static OutputResult alert(){
        OutputResult outputResult=new OutputResult();
        outputResult.code=DEFAULT_ALERT.getCode();
        outputResult.message=DEFAULT_ALERT.getDesc();
        outputResult.data.put("result","{}");
        return outputResult;
    }

    /**
     * 警告，响应提示消息
     * @param data
     * @return
     */
    public  static OutputResult alert(Object data){
        OutputResult outputResult=new OutputResult();
        outputResult.code=DEFAULT_ALERT.getCode();
        outputResult.message=DEFAULT_ALERT.getDesc();
        outputResult.data.put("result",data);
        return outputResult;
    }

    /**
     * 错误，不响应提示消息
     * @return
     */
    public static OutputResult error(){
        OutputResult outputResult=new OutputResult();
        outputResult.code=DEFAULT_FAIL.getCode();
        outputResult.code=DEFAULT_FAIL.getCode();
        outputResult.data.put("result","{}");
        return outputResult;
    }

    /**
     * 错误，响应提示消息
     * @param data
     * @return
     */
    public static OutputResult error(Object data){
        OutputResult outputResult=new OutputResult();
        outputResult.code=DEFAULT_FAIL.getCode();
        outputResult.code=DEFAULT_FAIL.getCode();
        outputResult.data.put("result",data);
        return outputResult;
    }

    /**
     * 自定义状态码
     * @param resultCode  自定义的状态码
     */
    public OutputResult statusCode(ResultCode resultCode){
        this.code=resultCode.getCode();
        return this;
    }

    /**
     * 自定义响应的消息
     * @param resultCode
     */
    public OutputResult message(ResultCode resultCode){
        this.message=resultCode.getDesc();
        return this;
    }
    /**
     * 自定义状态码和信息
     * @param resultCode  自定义的状态码
     */
    public OutputResult resultCode(ResultCode resultCode){
        this.code=resultCode.getCode();
        this.message=resultCode.getDesc();
        return this;
    }
}
