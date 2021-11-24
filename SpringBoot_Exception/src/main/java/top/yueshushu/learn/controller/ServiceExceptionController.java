package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.service.ExceptionService;

/**
 * @ClassName:ServiceExceptionController
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/22 19:46
 * @Version 1.0
 * @Since 1.0
 **/
@RestController
@RequestMapping("/exce")
public class ServiceExceptionController {
    @Autowired
    private ExceptionService exceptionService;

    @GetMapping("/div")
    public OutputResult div(){
        exceptionService.div();
        return OutputResult.success();
    }

    @GetMapping("/tryDiv")
    public OutputResult tryDiv(){
        exceptionService.tryDiv();
        return OutputResult.success();
    }
    @GetMapping("/throwDivExcepiton")
    public OutputResult throwDivExcepiton() throws Exception {
        exceptionService.throwDivExcepiton();
        return OutputResult.success();
    }

    @GetMapping("/throwBusExcepiton")
    public OutputResult throwBusExcepiton() throws Exception {
        exceptionService.throwBusExcepiton();
        return OutputResult.success();
    }


    @GetMapping("/throwDivExcepiton2")
    public OutputResult throwDivExcepiton2() {
      try{
          exceptionService.throwDivExcepiton();
          return OutputResult.success();
      }catch (Exception e){
          e.printStackTrace();
          return OutputResult.success("内部补获异常");
      }
    }

    @GetMapping("/throwBusExcepiton2")
    public OutputResult throwBusExcepiton2() {
        try{
            exceptionService.throwBusExcepiton();
            return OutputResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return OutputResult.success("内部补获异常");
        }
    }

    /*
    * 异常处理，
    * 出现的异常，没有显式的捕获，都会接收到。如果被try  catch 到，即不往外抛出， 抛出到 controller 层的话， 是不会接收到这个异常的。
    * 从而不触发异常机制信息.
    * */
}
