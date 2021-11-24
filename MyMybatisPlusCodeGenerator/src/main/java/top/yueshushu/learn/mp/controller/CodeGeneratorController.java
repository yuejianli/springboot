package top.yueshushu.learn.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.yueshushu.learn.mp.response.OutputResult;
import top.yueshushu.learn.mp.ro.CodeGeneratorRequestRo;
import top.yueshushu.learn.mp.service.CodeGeneratorService;
import top.yueshushu.learn.mp.service.SqlService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName:CodeGeneratorController
 * @Description 代码自动生成 Controller
 * @Author zk_yjl
 * @Date 2021/11/17 17:40
 * @Version 1.0
 * @Since 1.0
 **/
@Controller
@RequestMapping("/mp")
public class CodeGeneratorController {
    @Autowired
    private CodeGeneratorService codeGeneratorService;
    @Autowired
    private SqlService sqlService;
    /**
     * 跳转到主页
     * @date 2021/11/17 17:40
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * sql连接测试
     * @param codeGeneratorRequestRo
     * @return
     */
    @RequestMapping("/sqlConnection")
    @ResponseBody
    public OutputResult sqlConnection(@RequestBody CodeGeneratorRequestRo codeGeneratorRequestRo)  {
        try{
            return codeGeneratorService.sqlConnection(codeGeneratorRequestRo.getDbConfigRo());
        }catch (Exception e){
            return OutputResult.fail("数据库连接失败");
        }
    }
    /**
     * 执行相应的sql信息，保存到临时数据库里面
     * @date 2021/11/17 17:45
     * @author zk_yjl
     * @param sql
     * @return top.yueshushu.learn.mp.response.OutputResult
     */
    @PostMapping("/execSql")
    @ResponseBody
    public OutputResult execSql(@RequestBody CodeGeneratorRequestRo codeGeneratorRequestRo){
       try{
           if(StringUtils.isEmpty(codeGeneratorRequestRo.getSql())){
               return OutputResult.success("没有sql语句,不执行");
           }
           //将sql语句，进行转换
           String sql=codeGeneratorRequestRo.getSql().replaceAll(
                   "\t|\r|\n",""
           );
           if(StringUtils.isEmpty(sql)){
               return OutputResult.success("无效sql语句，不执行");
           }
           sqlService.execSelfSql(sql);
           return OutputResult.success("执行SQL成功");
       }catch (Exception e){
           e.printStackTrace();
           return OutputResult.fail("执行SQL失败");
       }
    }
    /**
     * 代码自动生成相应接口
     * @date 2021/11/17 17:47
     * @author zk_yjl
     * @param
     * @return top.yueshushu.learn.mp.response.OutputResult
     */
    @PostMapping("/codeGenerator")
    @ResponseBody
    public OutputResult codeGenerator(@RequestBody CodeGeneratorRequestRo codeGeneratorRequestRo,
                              HttpServletResponse response){
        //自动生成代码
        try{
            String path=codeGeneratorService.generator(codeGeneratorRequestRo);
            //下载文件，用绝对路径
            return OutputResult.success(path);
        }catch (Exception e){
            return OutputResult.fail(e.getMessage());
        }
    }
    @GetMapping("download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        // 获得待下载文件所在文件夹的绝对路径
        // 获得文件输入流
        File resultFile=new File(fileName);
        FileInputStream inputStream = new FileInputStream(resultFile);
        // 设置响应头、以附件形式打开文件
        response.setHeader("content-disposition", "attachment; fileName=" + resultFile.getName());
        ServletOutputStream outputStream = response.getOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        while ((len = inputStream.read(data)) != -1) {
            outputStream.write(data, 0, len);
        }
        outputStream.close();
        inputStream.close();
    }
}
