package top.yueshushu.learn.mp.util;

/**
 * @ClassName:DbUtil
 * @Description 主要用于封装数据库的连接配置信息
 * @Author zk_yjl
 * @Date 2021/11/18 19:38
 * @Version 1.0
 * @Since 1.0
 **/
public class DbUtil {
    /**
     * 获取Mysql 数据库的url
     * @date 2021/11/18 19:39
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    public static String getMysqlUrl(){
        return "jdbc:mysql://{0}:{1}/{2}?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
    }
    /**
     * 获取Mysql 数据库的url  5版本，使用这一个
     * @date 2021/11/18 19:39
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    public static String getMysql5Driver(){
        return "com.mysql.jdbc.Driver";
    }
    /**
     * 获取Mysql 数据库的url  8版本
     * @date 2021/11/18 19:39
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    public static String getMysql6Driver(){
        return "com.mysql.cj.jdbc.Drive";
    }

    /**
     * 获取Oracle 数据库的url
     * @date 2021/11/18 19:39
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    public static String getOracleUrl(){
        return "jdbc:oracle:thin:@//{0}:{1}/{2}";
    }
    /**
     * 获取Oracle 数据库的url
     * @date 2021/11/18 19:39
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    public static String getOracleDriver(){
        return "oracle.jdbc.OracleDriver";
    }

    /**
     * 获取Oracle 数据库的url
     * @date 2021/11/18 19:39
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    public static String getSqlServerUrl(){
        return "jdbc:sqlserver://{0}:{1};DatabaseNames={2}";
    }
    /**
     * 获取Oracle 数据库的url
     * @date 2021/11/18 19:39
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    public static String getSqlServerDriver(){
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }
}
