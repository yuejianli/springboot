package top.yueshushu.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yueshushu.learn.exception.BusinessException;

import java.util.List;

/**
 * @ClassName:ExceptionController
 * @Description 异常处理信息
 * @Author zk_yjl
 * @Date 2021/11/9 20:49
 * @Version 1.0
 * @Since 1.0
 **/
@Controller
public class ExceptionController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    /**
     * 会出现除 0异常
     * @date 2021/11/9 20:54
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/div")
    @ResponseBody
    public String div(){
        int result=10/0;
        return "相除后的结果是:"+result;
    }
    /**
     * 会出现空指针异常
     * @date 2021/11/9 20:54
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/npe")
    @ResponseBody
    public String npe(){
        String str=null;
        return "字符串的长度是:"+str.length();
    }
    /**
     * 会出现下标越界异常
     * @date 2021/11/9 20:54
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/array")
    @ResponseBody
    public String array(){
        String[] arr=new String[]{"岳泽霖","两个蝴蝶飞"};
        return "获取值:"+arr[arr.length];
    }


    /**
     * 会出现下业务型异常
     * @date 2021/11/9 20:54
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/bus")
    @ResponseBody
    public String bus() throws BusinessException {
       //去查询数据库
        throw new BusinessException("查询数据库失败了");
    }



    /**
     * 会出现下业务型异常
     * @date 2021/11/9 20:54
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/other")
    @ResponseBody
    public String other() throws Exception {
        //去查询数据库
        throw new Exception("其他的异常信息");
    }
}
