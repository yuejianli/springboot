package top.yueshushu.log;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName:LogVo
 * @Description 自定义日志的输出展示，或者保存到数据库里面。
 * @Author zk_yjl
 * @Date 2021/10/25 10:22
 * @Version 1.0
 * @Since 1.0
 **/
@Data
public class LogVo implements Serializable {
    /**
     @param className 请求的类
     @param methodName 请求的方法名称
     @param params 请求的参数
     @param returnValue 返回值
     @param model 模块  从Log 注解里面拿
     @param optType 操作类型 从Log 注解里面拿
     @param description 操作说明  从 Log 注解里面拿
     @param reqUrl 请求的路径
     @param reqIp 请求的ip地址
     @param reqTime 请求的时间
     @param execTime 执行的时长
     @param excName 异常名称
     @param excInfo 异常的信息
     */
    private String className;
    private String methodName;
    private String params;
    private String returnValue;
    private String model;
    private String optType;
    private String description;
    private String reqUrl;
    private String reqIp;
    private String reqTime;
    private Long execTime;
    private String excName;
    private String excInfo;

    @Override
    public String toString() {
        return "LogVo{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", returnValue='" + returnValue + '\'' +
                ", model='" + model + '\'' +
                ", optType='" + optType + '\'' +
                ", description='" + description + '\'' +
                ", reqUrl='" + reqUrl + '\'' +
                ", reqIp='" + reqIp + '\'' +
                ", reqTime=" + reqTime +
                ", execTime=" + execTime +
                ", excName='" + excName + '\'' +
                ", excInfo='" + excInfo + '\'' +
                '}';
    }
}
