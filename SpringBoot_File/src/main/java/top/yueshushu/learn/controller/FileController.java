package top.yueshushu.learn.controller;

import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.yueshushu.learn.pojo.User;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:FileController
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/4 20:36
 * @Version 1.0
 * @Since 1.0
 **/
@Controller
public class FileController {

    @Value("${uploadFilePath:D:}")
    private String uploadFilePath;

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        // 获得 classpath 的绝对路径
       // String realPath = request.getServletContext().getRealPath("static/files");
      //  String realPath = ResourceUtils.getURL("classpath:").getPath()+"static/files";
        String realPath =uploadFilePath;
        File newFile = new File(realPath);
        // 如果文件夹不存在、则新建
        if (!newFile.exists()){
            newFile.mkdirs();
        }
        // 上传
        file.transferTo(new File(newFile, file.getOriginalFilename()));
        String uploadPath=realPath+"/"+file.getOriginalFilename();
        return "上传文件成功,地址为:"+uploadPath;
    }


    @GetMapping("download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        // 获得待下载文件所在文件夹的绝对路径
        String realPath =uploadFilePath;
        // 获得文件输入流
        FileInputStream inputStream = new FileInputStream(new File(realPath, fileName));
        // 设置响应头、以附件形式打开文件
        response.setHeader("content-disposition", "attachment; fileName=" + fileName);
        ServletOutputStream outputStream = response.getOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        while ((len = inputStream.read(data)) != -1) {
            outputStream.write(data, 0, len);
        }
        outputStream.close();
        inputStream.close();
    }

    /**
     * 上传文件时，同时传入参数的信息.
     * @date 2021/11/4 21:12
     * @author zk_yjl
     * @return
     */
    @PostMapping("/uploadParam")
    @ResponseBody
    public String uploadParam(@RequestParam MultipartFile file,@RequestParam String name,
                              @RequestParam Integer age) throws IOException {
        String realPath =uploadFilePath;
        File newFile = new File(realPath);
        System.out.println("传入的name参数值:"+name+",传入的age参数值:"+age);
        // 如果文件夹不存在、则新建
        if (!newFile.exists()){
            newFile.mkdirs();
        }
        // 上传
        file.transferTo(new File(newFile, file.getOriginalFilename()));
        String uploadPath=realPath+"/"+file.getOriginalFilename();
        return "上传文件成功,地址为:"+uploadPath;
    }


    /**
     * 上传Excel文件,获取文件里面的内容.
     * @date 2021/11/4 21:12
     * @author zk_yjl
     * @return
     */
    @PostMapping("/uploadExcel")
    @ResponseBody
    public String uploadExcel(@RequestParam MultipartFile file) throws IOException {
        String realPath =uploadFilePath;
        File newFile = new File(realPath);
        // 如果文件夹不存在、则新建
        if (!newFile.exists()){
            newFile.mkdirs();
        }
        // 上传
        File uploadFile=new File(newFile, file.getOriginalFilename());
        file.transferTo(uploadFile);

        InputStream inputStream=new FileInputStream(uploadFile);
        System.out.println(">>>>>>>>>>>>>>读取到List里面");
        //同步进行读取数据，会放置在内存里面.
        List<User> userList=EasyExcel.read(inputStream).head(User.class).
                sheet(0).doReadSync();
        for(User user:userList){
            System.out.println(">>>读取记录:"+user);
        }
        String uploadPath=realPath+"/"+file.getOriginalFilename();
        return "上传文件成功,地址为:"+uploadPath;
    }



    /**
     * 下载Excel文件
     * @date 2021/11/4 21:12
     * @author zk_yjl
     * @return
     */
    @GetMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletResponse response,String name) throws IOException {
        //1. 通过传入的参数，和相关的业务代码逻辑处理，获取相应的数据.
        //2. 将数据进行转换，转换成 List<User> 的形式.
        List<User> dataList=createDataList(name);
        //将数据进行下载.
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("员工表", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), User.class).autoCloseStream(Boolean.FALSE).sheet("员工")
                    .doWrite(dataList);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            JSONObject jsonObject=new JSONObject();;
            response.getWriter().println(jsonObject.toString());
        }

    }

    private List<User> createDataList(String name) {
        List<User> result=new ArrayList<>();
        for(int i=1;i<=10;i++){
          User user=new User();
          user.setId(i);
          user.setName(name+"_"+i);
          user.setSex(i%2==0?"女":"男");
          user.setAge(20+i);
          user.setDescription("我是第"+i+"个，我的名字是:"+user.getName());
          result.add(user);
        }
        return result;
    }


}
