package top.yueshushu.learn.util;

import cn.hutool.extra.ftp.Ftp;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import sun.net.ftp.FtpClient;
import top.yueshushu.learn.pojo.SshFileProperties;

import java.io.IOException;

/**
 * @ClassName:FtpConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/5 11:44
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
@Log4j2
public class FtpUtil {
    @Autowired
    private SshFileProperties sshFileProperties;
    /**
     * 创建连接
     * @date 2021/11/5 17:21
     * @author zk_yjl
     * @param
     * @return org.apache.commons.net.ftp.FTPClient
     */
    public FTPClient createFileClient(){
       FTPClient ftpClient=new FTPClient();
       try{
           ftpClient.setControlEncoding("gbk");
           ftpClient.connect(sshFileProperties.getHost(), 21);
           ftpClient.login(sshFileProperties.getUsername(), sshFileProperties.getPassword());
           ftpClient.enterLocalPassiveMode();
           // 设置上传目录
           ftpClient.setBufferSize(1024);
           ftpClient.setConnectTimeout(10 * 1000);
           ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
           return ftpClient;
       }catch (Exception e){
           log.error("创建FTP服务器失败,{}",e);
       }
        return ftpClient;
    }
    /**
     * 关闭连接
     * @date 2021/11/5 17:21
     * @author zk_yjl
     * @param ftpClient
     * @return void
     */
    public void close(FTPClient ftpClient){
        try {
           if(ftpClient!=null){
               ftpClient.disconnect();
           }
        } catch (IOException e) {
            throw new RuntimeException("关闭FTP连接发生异常！", e);
        }
    }
}
