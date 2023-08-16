package top.yueshushu.learn.mp.service;

import ch.qos.logback.core.db.dialect.DBUtil;
import cn.hutool.core.util.ZipUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.javassist.compiler.CodeGen;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.yueshushu.learn.mp.dto.CodeGeneratorDto;
import top.yueshushu.learn.mp.enumtype.DataBaseTypeEnum;
import top.yueshushu.learn.mp.exception.BusinessException;
import top.yueshushu.learn.mp.response.OutputResult;
import top.yueshushu.learn.mp.ro.CodeGeneratorRequestRo;
import top.yueshushu.learn.mp.ro.DBConfigRo;
import top.yueshushu.learn.mp.ro.DBTableRo;
import top.yueshushu.learn.mp.util.DbUtil;
import top.yueshushu.learn.mp.util.MyUtil;
import top.yueshushu.learn.mp.util.MybatisPlusCodeGeneratorSelfUtil;
import top.yueshushu.learn.mp.util.MybatisPlusCodeGeneratorUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipFile;

/**
 * @ClassName:CodeGeneratorServiceImpl
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/18 19:05
 * @Version 1.0
 * @Since 1.0
 **/
@Service
@Log4j2
public class CodeGeneratorServiceImpl implements CodeGeneratorService {
    @Value("${outFilePath}")
    private String outFilePath;
    @Override
    @Transactional
    public String generator(CodeGeneratorRequestRo codeGeneratorRequestRo) throws Exception{
        //将数据转换成相应的Dto
        CodeGeneratorDto codeGeneratorDto=convertToDto(codeGeneratorRequestRo);
        //处理文件的输出路径
        String filePath=handlerOutFilePath(outFilePath);
        String projectPath=filePath+codeGeneratorDto.getFileConfigRo().getProjectName()+"/";
        //进行自动自成
       // new MybatisPlusCodeGeneratorUtil(codeGeneratorDto,projectPath).generator();
        new MybatisPlusCodeGeneratorSelfUtil(codeGeneratorDto,projectPath).generator();
        log.info(">>>>生成文件成功");
        //生成好之后,将文件进行打包
        File zipFile=new File(projectPath.substring(0,projectPath.length()-1));
        try{
            File file=ZipUtil.zip(zipFile, Charset.forName("UTF-8"));
            return filePath+file.getName();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OutputResult sqlConnection(DBConfigRo dbConfigRo) throws Exception {
        //连接数据库配置信息
        handlerDb(dbConfigRo);
        //获取数据库连接
        try{
            Class.forName(dbConfigRo.getDriver());
            Connection connection = DriverManager.getConnection(dbConfigRo.getUrl(),
                    dbConfigRo.getUsername(),dbConfigRo.getPassword());
            if(null==connection){
                return OutputResult.fail("连接失败");
            }
            return OutputResult.success("连接成功");
        }catch (Exception e){
            return OutputResult.fail("连接失败");
        }
    }

    private String handlerOutFilePath(String outFilePath) {
       if(StringUtils.hasText(outFilePath)){
           return outFilePath;
       }
        if(MyUtil.isLinux()){
           return "/temp/TempMP/";
        }
        return "D:/temp/TempMP/";
    }

    /**
     将请求数据转换成相应的Dto,用于接收
     * @date 2021/11/18 19:17
     * @author zk_yjl
     * @param codeGeneratorRequestRo
     * @return top.yueshushu.learn.mp.dto.CodeGeneratorDto
     */
    private CodeGeneratorDto convertToDto(CodeGeneratorRequestRo codeGeneratorRequestRo) throws Exception{
        CodeGeneratorDto codeGeneratorDto=new CodeGeneratorDto();
        //先复制基本的信息.
        ObjectMapper mapper = new ObjectMapper();
        codeGeneratorDto= mapper.convertValue(codeGeneratorRequestRo,CodeGeneratorDto.class);

        //处理一下数据值信息.
        handlerDb(codeGeneratorDto.getDbConfigRo());
        //处理排除表那些信息
        handlerTable(codeGeneratorDto);
        return codeGeneratorDto;

    }
    /**
     * 处理表设置的那些信息
     * @date 2021/11/18 20:03
     * @author zk_yjl
     * @param codeGeneratorDto
     * @return void
     */
    private void handlerTable(CodeGeneratorDto codeGeneratorDto) throws Exception{
        DBTableRo dbTableRo = codeGeneratorDto.getDbTableRo();
        //如果为空，走默认配置
        if(null==dbTableRo){
            return ;
        }
        //进行处理
        codeGeneratorDto.setTableNamesArr(
                split(dbTableRo.getTableNames())
        );
        codeGeneratorDto.setTablePrefixesArr(
                split(dbTableRo.getTablePrefixes())
        );
        codeGeneratorDto.setFieldPrefixesArr(
                split(dbTableRo.getFieldPrefixes())
        );
        codeGeneratorDto.setExcludeTableNamesArr(
                split(dbTableRo.getExcludeTableNames())
        );
        codeGeneratorDto.setIgnoreColumnsArr(
                split(dbTableRo.getIgnoreColumns())
        );
    }
    /**
     * 将字符串进行拆分，兼容 \n 空格等信息
     * @date 2021/11/18 20:07
     * @author zk_yjl
     * @param value
     * @return java.lang.String[]
     */
    private String[] split(String value) {
        if (!StringUtils.hasText(value)) {
            return new String[]{};
        }
        List<String> valueList = new ArrayList<>();
        String[] values;
        if (value.contains(",")) {
            values = value.split(",");
        } else if (value.contains("\n")) {
            values = value.split("\n");
        } else {
            values = value.split(" ");
        }
        for (String str : values) {
            str = str.trim();
            if (StringUtils.hasText(str)) {
                valueList.add(str);
            }
        }
        String[] result = new String[valueList.size()];
        return  valueList.toArray(result);
    }

    /**
     * 处理数据库的相关信息
     * @date 2021/11/18 20:03
     * @author zk_yjl
     * @return void
     */
    private void handlerDb( DBConfigRo dbConfigRo) throws Exception {
        DataBaseTypeEnum dataBaseTypeEnum=DataBaseTypeEnum.getByValue(
                Integer.parseInt(dbConfigRo.getDbTypeName())
        );
        if(null==dataBaseTypeEnum){
             throw new BusinessException("数据库的类型不能为空");
        }
        //目前只运行数据库的是 : Mysql
        if(!DataBaseTypeEnum.DB_MYSQL.equalsByValue(dataBaseTypeEnum.getValue())){
            throw new BusinessException("目前只支持 Mysql 数据库");
        }
        if(!("1".equals(dbConfigRo.getDatabaseSource()))){
            //不为1的话，用默认的
            dbConfigRo.setHost("127.0.0.1");
            dbConfigRo.setPort("3306");
            dbConfigRo.setDbName("mpcode");
            dbConfigRo.setUsername("mpcode");
            dbConfigRo.setPassword("mpcode");
        }
        String host= Optional.ofNullable(dbConfigRo.getHost()).orElse("127.0.0.1");
        switch (dataBaseTypeEnum){
            case DB_MYSQL:{
                //修改对应的信息
                String port=Optional.ofNullable(dbConfigRo.getPort()).orElse("3306");
                String dbUrl = MessageFormat.format(DbUtil.getMysqlUrl(), host, port, dbConfigRo.getDbName());
                dbConfigRo.setUrl(dbUrl);
                dbConfigRo.setDriver(DbUtil.getMysql5Driver());
                break;
            }
            case DB_ORACLEL:{
                String port=Optional.ofNullable(dbConfigRo.getPort()).orElse("1521");
                String dbUrl = MessageFormat.format(DbUtil.getOracleUrl(), host, port, dbConfigRo.getDbName());
                dbConfigRo.setUrl(dbUrl);
                dbConfigRo.setDriver(DbUtil.getOracleDriver());
                break;
            }
            case DB_SQLSERVER:{
                String port=Optional.ofNullable(dbConfigRo.getPort()).orElse("1433");
                String dbUrl = MessageFormat.format(DbUtil.getSqlServerUrl(), host, port, dbConfigRo.getDbName());
                dbConfigRo.setUrl(dbUrl);
                dbConfigRo.setDriver(DbUtil.getSqlServerDriver());
                break;
            }
        }
    }
}
