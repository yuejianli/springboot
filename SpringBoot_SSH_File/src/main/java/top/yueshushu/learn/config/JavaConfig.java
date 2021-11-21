package top.yueshushu.learn.config;

import cn.hutool.extra.ssh.Sftp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import top.yueshushu.learn.service.FileService;
import top.yueshushu.learn.service.impl.FtpFileServiceImpl;
import top.yueshushu.learn.service.impl.SftpFileServiceImpl;
import top.yueshushu.learn.util.FtpUtil;

/**
 * @ClassName:JavaConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/11 9:10
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
public class JavaConfig {

    @Autowired(required = false)
    private Sftp sftp;

    @Autowired(required = false)
    private FtpUtil ftpUtil;

    /**
     条件符合时，创建
     */
    @Bean("fileService")
    @Profile("ftp")
    FileService ftp(){
        return new FtpFileServiceImpl(ftpUtil);
    }
    /**
     条件符合时，创建
     */
    @Bean("fileService")
    @Profile("sftp")
    FileService cat(){
        return new SftpFileServiceImpl(sftp);
    }
}

