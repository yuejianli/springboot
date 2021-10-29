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
    private final String DEFAULT_ANNOTATIONS="top.yueshushu.log.MyLog";
    private final String DEFAULT_PACKAGES="";
   /**
    @param packages 类包 用,号分隔, 这里面可以放置一些配置信息。
    */
    private String packages=DEFAULT_PACKAGES;

    public String getAnnotations() {
        return DEFAULT_ANNOTATIONS;
    }
    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }
}
