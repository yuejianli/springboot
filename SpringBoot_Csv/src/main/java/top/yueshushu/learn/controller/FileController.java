package top.yueshushu.learn.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.util.DataUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
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
@Log4j2
public class FileController {

    @Value("${uploadFilePath:D:}")
    private String uploadFilePath;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 上传Excel文件,获取文件里面的内容.
     * @date 2021/11/4 21:12
     * @author zk_yjl
     * @return
     */
    @PostMapping("/uploadCsv")
    @ResponseBody
    public String uploadCsv(@RequestParam MultipartFile file) throws IOException {
        String realPath =uploadFilePath;
        File newFile = new File(realPath);
        // 如果文件夹不存在、则新建
        if (!newFile.exists()){
            newFile.mkdirs();
        }
        // 上传
        File uploadFile=new File(newFile, file.getOriginalFilename());
        file.transferTo(uploadFile);
        //读取文件信息
        List<User> userList = CsvUtil.getReader().read(new FileReader(uploadFile), User.class);
        userList.forEach(n->{
            log.info("输出内容:>>>"+n);
        });
        String uploadPath=realPath+"/"+file.getOriginalFilename();
        return "上传文件成功,地址为:"+uploadPath;
    }



    /**
     * 下载Excel文件
     * @date 2021/11/4 21:12
     * @author zk_yjl
     * @return
     */
    @GetMapping("/downloadCsv")
    @ResponseBody
    public void downloadCsv(HttpServletResponse response) throws IOException {
        //1. 通过传入的参数，和相关的业务代码逻辑处理，获取相应的数据.
        //2. 将数据进行转换，转换成 List<User> 的形式.
        List<User> dataList= DataUtil.createDataList("两个蝴蝶飞");
        //将数据进行下载.
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            //响应类型
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            //进行下载
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("员工表", "UTF-8").replaceAll("\\+", "%20");
            //响应的是  .csv 文件的后缀
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".csv");
            // 这里需要设置不关闭流
            String filePath=uploadFilePath+File.separator+fileName+".csv";
            File file=new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
            //将数据，写入到 文件里面。 主要是这一行代码逻辑
            CsvUtil.getWriter(file, Charset.forName("UTF-8")).writeBeans(dataList).close();
            downloadFile(response,file);
            //将该文件删除
            file.delete();
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

    /**
     * @return boolean
     * @Description 下载文件
     * @Param response，file
     **/
    public boolean downloadFile(HttpServletResponse response, File file) {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        OutputStream os = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            os = response.getOutputStream();
            //MS产本头部需要插入BOM
            //如果不写入这几个字节，会导致用Excel打开时，中文显示乱码
            os.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
            byte[] buffer = new byte[1024];
            int i = bufferedInputStream.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            //关闭流
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            file.delete();
        }
        return false;
    }
}
