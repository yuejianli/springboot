package top.yueshushu.learn.mp.util;

/**
 * @ClassName:MyUtils
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/18 20:16
 * @Version 1.0
 * @Since 1.0
 **/
public class MyUtil {
    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}
