package top.yueshushu.learn.mp.ro;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:DBConfigRo
 * @Description 数据库配置的相关信息
 * @Author zk_yjl
 * @Date 2021/11/17 17:50
 * @Version 1.0
 * @Since 1.0
 **/
@Data
public class DBConfigRo implements Serializable {
    // 数据库类型
    private String dbTypeName;
    // 数据库的来源  1表示用户定义的数据库，2表示服务器本地的数据库，都是默认的.
    private String databaseSource="1";
    // 数据库服务器
    private String host;
    // 端口号
    private String port;
    // 数据库用户名称
    private String username;
    // 数据库密码
    private String password;
    // 数据库驱动
    private String driver;
    // 数据库名称
    private String dbName;
    //配置url
    private String url;
}
