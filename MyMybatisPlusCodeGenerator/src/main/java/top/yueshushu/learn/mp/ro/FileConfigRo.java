package top.yueshushu.learn.mp.ro;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:FileConfigRo
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/17 17:53
 * @Version 1.0
 * @Since 1.0
 **/
@Data
public class FileConfigRo implements Serializable {
    //项目的名称
    private String projectName="MPGenerator";
    // 包名
    private String packageName;
    // 实体类包名
    private String packageEntity;
    // mapper包名
    private String packageMapper;
    // mapper XML目录名
    private String packageMapperXml;
    // service包名
    private String packageService;
    // serviceImpl包名
    private String packageServiceImpl;
    // controller包名
    private String packageController;

    // 实体类文件名格式
    private String fileNamePatternEntity;
    // mapper文件名格式
    private String fileNamePatternMapper;
    // mapper XML文件名格式
    private String fileNamePatternMapperXml;
    // service文件名格式
    private String fileNamePatternService;
    // serviceImpl文件名格式
    private String fileNamePatternServiceImpl;
    // controller文件名格式
    private String fileNamePatternController;

}
