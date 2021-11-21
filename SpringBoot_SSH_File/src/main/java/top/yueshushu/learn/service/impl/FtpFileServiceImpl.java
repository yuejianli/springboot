package top.yueshushu.learn.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.util.StringUtils;
import top.yueshushu.learn.service.FileService;
import top.yueshushu.learn.util.FtpUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description ftp 连接服务器，实现上传和下载
 * @Author zk_yjl
 * @Date 2021/11/5 11:40
 * @Version 1.0
 * @Since 1.0
 **/
@Log4j2
public class FtpFileServiceImpl implements FileService {

    private FtpUtil ftpUtil;
    public FtpFileServiceImpl(FtpUtil ftpUtil){
        this.ftpUtil=ftpUtil;
    }
    @Override
    public String upload(String dest,String fileName, File file) {
        if(StringUtils.isEmpty(fileName)){
            fileName=file.getName();
        }
        FTPClient ftpClient=null;
        //切换到相应的目录
       try{
           ftpClient=ftpUtil.createFileClient();
           boolean isExistDir = ftpClient.changeWorkingDirectory(dest);
           if(!isExistDir){
               ftpClient.makeDirectory(dest);
               ftpClient.changeWorkingDirectory(dest);
           }
           //保存文件
           ftpClient.storeFile(fileName,new FileInputStream(file));
           log.info("上传文件成功");
           return dest+fileName;
       }catch (Exception e){
           log.error("上传文件失败,{}",e);
       }finally{
           ftpUtil.close(ftpClient);
       }
       return null;
    }

    @Override
    public String pathUpload(String path, File file) {
        //切换到相应的目录
        String[] split = path.split(File.separator);
        int size=split.length;
        FTPClient ftpClient=null;
        try{
            ftpClient=ftpUtil.createFileClient();
            for (int i=0;i<size-2;i++) {
                String tempPath=split[i];
                if (StringUtils.isEmpty(tempPath)) {
                    continue;
                }
                if (!ftpClient.changeWorkingDirectory(tempPath)) {
                    ftpClient.makeDirectory(tempPath);
                    ftpClient.changeWorkingDirectory(tempPath);
                }
            }
            //保存文件
            ftpClient.storeFile(split[size-1],new FileInputStream(file));
            log.info("上传文件成功");
            return path;
        }catch (Exception e){
            log.error("上传文件失败,{}",e);
        }finally {
            ftpUtil.close(ftpClient);
        }
        return null;
    }

    @Override
    public void download(String dest,String fileName, File outFile) {
        pathDownload(dest+fileName,outFile);
    }
    @Override
    public void pathDownload(String path, File outFile) {
        FTPClient ftpClient=null;
        try {
            ftpClient=ftpUtil.createFileClient();
            ftpClient.retrieveFile(path,new FileOutputStream(outFile));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            ftpUtil.close(ftpClient);
        }
    }
}
