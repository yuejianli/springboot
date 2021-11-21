package top.yueshushu.learn.mp.service;

import top.yueshushu.learn.mp.response.OutputResult;
import top.yueshushu.learn.mp.ro.CodeGeneratorRequestRo;
import top.yueshushu.learn.mp.ro.DBConfigRo;

import java.io.File;

/**
 * @ClassName:CodeGeneratorService
 * @Description 代码自动生成的接口
 * @Author zk_yjl
 * @Date 2021/11/18 19:04
 * @Version 1.0
 * @Since 1.0
 **/
public interface CodeGeneratorService {
    /**
     * 自动生成代码的主逻辑
     * @date 2021/11/18 19:06
     * @author zk_yjl
     * @param codeGeneratorRequestRo
     * @return void
     */
    String generator(CodeGeneratorRequestRo codeGeneratorRequestRo) throws Exception;

    /**
     * 数据库连接配置
     * @param dbConfigRo
     * @return
     */
    OutputResult sqlConnection(DBConfigRo dbConfigRo) throws Exception;
}
