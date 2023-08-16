package top.yueshushu.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yueshushu.learn.exception.BusinessException;
import top.yueshushu.learn.response.OutputResult;

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
    public OutputResult div(){
        int result=10/0;
        return OutputResult.success(result);
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
    public OutputResult npe(){
        String str=null;
        return OutputResult.success(str.length());
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
    public OutputResult array(){
        String[] arr=new String[]{"岳泽霖","两个蝴蝶飞"};
        return OutputResult.success(arr[arr.length]);
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
    public OutputResult bus() throws BusinessException {
       try{
           int aa=10/0;
       }catch (Exception e){
           //去查询数据库
           throw new BusinessException("查询数据库失败了");
       }
        return OutputResult.success("查询数据库成功");

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
    public OutputResult other() throws Exception {
        //去查询数据库
        try{
            int aa=10/0;
        }catch (Exception e){
            //去查询数据库
            throw new Exception("其他的异常信息");
        }
        return OutputResult.success("查询数据库成功");
    }
}
