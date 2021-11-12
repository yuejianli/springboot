package top.yueshushu.learn.service;

import java.io.File;

/**
 * @ClassName:FileService
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/5 11:30
 * @Version 1.0
 * @Since 1.0
 **/
public interface FileService {
    /**
     * 上传文件
     * @date 2021/11/5 11:52
     * @author zk_yjl
     * @param file 上传的文件
     * @param path 上传的文件的名称
     * @return String 上传后文件的路径
     */
    default String upload(String dest,String name,File file){
        return dest+name;
    }
    /**
     * 上传文件
     * @date 2021/11/5 11:52
     * @author zk_yjl
     * @param file 上传的文件
     * @param path 上传的文件的名称
     * @return String 上传后文件的路径
     */
    default String pathUpload(String path,File file){
        return path;
    }
    /**
     * 下载文件
     * @date 2021/11/5 11:54
     * @author zk_yjl
     * @param fileName  下载的文件名称
     * @param outFile  下载到的文件
     * @return void
     */
    default void download(String dest,String fileName,File outFile){

    }
    /**
     * 下载文件,以绝对路径进行下载.
     * @date 2021/11/5 11:54
     * @author zk_yjl
     * @param path  下载的文件的路径
     * @param outFile  下载到的文件
     * @return void
     */
    default void pathDownload(String path,File outFile){

    }
}
