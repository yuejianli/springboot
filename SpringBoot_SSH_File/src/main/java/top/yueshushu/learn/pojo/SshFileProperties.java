package top.yueshushu.learn.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName:SshFilProperties
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/5 10:19
 * @Version 1.0
 * @Since 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix ="file.ssh" )
public class SshFileProperties {
    private static final String DEFAULT_HOST="127.0.0.1";
    private static final Integer DEFAULT_PORT=22;
    private static final String DEFAULT_USERNAME="";
    private static final String DEFAULT_PASSWORD="";
    private static final String DEFAULT_UPLOAD_FILEPATH="/usr/local/";
    private static final String DEFAULT_DOWNLOAD_FILEPATH="/usr/local/";

    private String host=DEFAULT_HOST;
    private Integer port=DEFAULT_PORT;
    private String username=DEFAULT_USERNAME;
    private String password=DEFAULT_PASSWORD;
    private String uploadFilePath=DEFAULT_UPLOAD_FILEPATH;
    private String downloadFilePath=DEFAULT_DOWNLOAD_FILEPATH;
}
