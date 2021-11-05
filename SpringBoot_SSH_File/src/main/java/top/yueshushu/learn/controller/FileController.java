package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.yueshushu.learn.pojo.SshFileProperties;
import top.yueshushu.learn.service.FileService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    @Resource(name="ftpFileService")
    private FileService fileService;

    @Autowired
    private SshFileProperties sshFileProperties;


    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        // 获得 classpath 的绝对路径
        String realPath = ResourceUtils.getURL("classpath:").getPath()+"static/files";
        File newFile = new File(realPath);
        // 如果文件夹不存在、则新建
        if (!newFile.exists()){
            newFile.mkdirs();
        }
        // 上传
        File uploadFile=new File(newFile, file.getOriginalFilename());
        file.transferTo(uploadFile);
        String uploadPath = fileService.upload(sshFileProperties.getUploadFilePath(),file.getOriginalFilename(), uploadFile);
        //将以前的文件删除掉
        uploadFile.delete();
        return "上传文件成功,地址为:"+uploadPath;
    }


    @GetMapping("download")
    @ResponseBody
    public String download(String fileName) throws IOException {
        // 获得待下载文件所在文件夹的绝对路径
        String realPath =sshFileProperties.getUploadFilePath();
        //构建新的文件
        File downloadDirFile=new File(sshFileProperties.getDownloadFilePath());
        // 如果文件夹不存在、则新建
        if (!downloadDirFile.exists()){
            downloadDirFile.mkdirs();
        }
        // 上传
        File downloadFile=new File(downloadDirFile,fileName);
        String downloadPath=sshFileProperties.getDownloadFilePath()+fileName;
        fileService.download(realPath,fileName,downloadFile);
        return "文件下载成功，下载后的目录为:"+downloadPath;
    }
}
