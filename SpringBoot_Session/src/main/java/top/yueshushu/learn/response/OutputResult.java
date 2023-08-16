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
public class OutputResult implements Serializable {
    /**
     * @param code 响应代码
     * @param message 响应信息
     * @param data 响应的数据
     */
    private Integer code;
    private String message;
    private Map<String,Object> data=new HashMap<String,Object>();

    /**
     * 构造方法 私有。 避免外部构造
     */
    private OutputResult(){

    }
    /**
     * 成功
     * @return
     */
    public static  OutputResult fail(){
        OutputResult outputResult=new OutputResult();
        outputResult.code=500;
        outputResult.message="失败";
        return outputResult;
    }

    /**
     * 成功
     * @return
     */
    public static  OutputResult success(){
        OutputResult outputResult=new OutputResult();
        outputResult.code=200;
        outputResult.message="成功";
        return outputResult;
    }

    /**
     * 成功
     * @param data  要响应的数据
     * @return
     */
    public static  OutputResult success(Object data){
        OutputResult outputResult=new OutputResult();
        outputResult.code=200;
        outputResult.message="成功";
        outputResult.data.put("result",data);
        return outputResult;
    }
}
