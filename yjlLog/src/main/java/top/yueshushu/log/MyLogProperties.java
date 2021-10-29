package top.yueshushu.log;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName:MyLogProperties
 * @Description 日志配置类
 * @Author zk_yjl
 * @Date 2021/10/25 17:07
 * @Version 1.0
 * @Since 1.0
 **/
@ConfigurationProperties("mylog")
public class MyLogProperties {
    /**
     定义默认的信息
     */
    public static final Long DEFAULT_RUNTIME=0L;
    public static Boolean DEFAULT_EXC_FULL_SHOW=true;
    public static Integer DEFAULT_RESULT_LENGTH=0;
    /**
     * @runTime 方法的运行时长  当方法的运行时间> 设置的值时，才记录。 默认为0
     */
    private Long runTime=DEFAULT_RUNTIME;
    /**
     * @excFullShow 异常的信息 是否全部展示 保存
     */
    private Boolean excFullShow=DEFAULT_EXC_FULL_SHOW;
    /**
     * @resultLength 输出结果的长度  0 表示全部输出
     */
    private Integer resultLength=DEFAULT_RESULT_LENGTH;
    // ...... 其他的默认的信息，后期可以补充其他的


    public MyLogProperties() {
    }

    public MyLogProperties(Long runTime, Boolean excFullShow, Integer resultLength) {
        this.runTime = runTime;
        this.excFullShow = excFullShow;
        this.resultLength = resultLength;
    }

    public Long getRunTime() {
        return runTime;
    }

    public Boolean getExcFullShow() {
        return excFullShow;
    }

    public Integer getResultLength() {
        return resultLength;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public void setExcFullShow(Boolean excFullShow) {
        this.excFullShow = excFullShow;
    }

    public void setResultLength(Integer resultLength) {
        this.resultLength = resultLength;
    }
}
