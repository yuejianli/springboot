package top.yueshushu.learn.service;

/**
 * @ClassName:ExceptionService
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/22 19:46
 * @Version 1.0
 * @Since 1.0
 **/
public interface ExceptionService {
    /**
     普通的两个异常信息,不补获
     */
    public void div();
    /**
     普通的两个异常信息,内部进行补获
     */
    public void tryDiv();

    /**
     往外抛出异常
     */

    public void throwDivExcepiton() throws Exception;
    public void throwBusExcepiton() throws Exception;




}
