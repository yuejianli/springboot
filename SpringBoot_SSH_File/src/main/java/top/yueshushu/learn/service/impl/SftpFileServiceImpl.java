package top.yueshushu.learn.service.impl;

import cn.hutool.extra.ssh.Sftp;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;
import top.yueshushu.learn.service.FileService;

import java.io.File;
import java.io.FileInputStream;

/**
 * @ClassName:SftpFileServiceImpl
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/5 14:22
 * @Version 1.0
 * @Since 1.0
 **/
@Log4j2
public class SftpFileServiceImpl implements FileService {
    private Sftp sftp;
    public SftpFileServiceImpl(Sftp sftp){
        this.sftp=sftp;
    }
    @Override
    public String upload(String dest,String fileName, File file) {
        if(StringUtils.isEmpty(fileName)){
            fileName=file.getName();
        }
        try {
            sftp.cd(dest);
        } catch (Exception e) {
            log.info("该文件夹不存在，自动创建");
            sftp.mkdir(dest);
        }
        try{
            sftp.getClient().put(
                    new FileInputStream(file),
                    dest+fileName
            );
            log.info(">>>文件上传成功");
            return dest+fileName;
        }catch (Exception e){
            log.error("文件上传失败,{}",e);
            return null;
        }
    }

    @Override
    public String pathUpload(String path, File file) {
        try{
            sftp.getClient().put(new FileInputStream(file),path);
        }catch (Exception e){
            log.error("文件上传失败,{}",e);
            return null;
        }
        return null;
    }

    @Override
    public void download(String dest,String fileName, File outFile) {
        sftp.download(dest+fileName,outFile);
    }
    @Override
    public void pathDownload(String path, File outFile) {
        sftp.download(path,outFile);
    }
}
