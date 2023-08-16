package top.yueshushu.learn.mp.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import top.yueshushu.learn.mp.dto.CodeGeneratorDto;
import top.yueshushu.learn.mp.ro.DBConfigRo;
import top.yueshushu.learn.mp.ro.FileConfigRo;

/**
 * @ClassName:MybatisPlusCodeGeneratorUtil
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/18 20:09
 * @Version 1.0
 * @Since 1.0
 **/
public class MybatisPlusCodeGeneratorUtil {
    /**
      输出文件的目录
     */
    private String outFilePath;

    private CodeGeneratorDto codeGeneratorDto;


    public MybatisPlusCodeGeneratorUtil(CodeGeneratorDto codeGeneratorDto,String outFilePth){
       this.outFilePath=outFilePth;
       this.codeGeneratorDto=codeGeneratorDto;
    }
    /**
     * 进行自动生成代码逻辑处理
     * @date 2021/11/18 20:12
     * @author zk_yjl
     * @return
     */
    public void generator() throws Exception{
        AutoGenerator autoGenerator = new AutoGenerator();
        //处理全局配置
        handlerGlobalConfig(autoGenerator);
        //处理数据源
        handlerDataSourceConfig(autoGenerator);
        //处理包配置
        handlerPackageConfig(autoGenerator);
        //处理策略
        handlerStrategyConfig(autoGenerator);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        //templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);
        //生成代码
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }
    /**
     * 处理策略相关配置
     * @date 2021/11/18 20:44
     * @author zk_yjl
     * @param autoGenerator
     * @return void
     */
    private void handlerStrategyConfig(AutoGenerator autoGenerator) {
        StrategyConfig strategyConfig=new StrategyConfig();
        //处理字段信息
        tableStrategy(strategyConfig);
        //风格设置
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);
        autoGenerator.setStrategy(strategyConfig);

    }

    private void tableStrategy(StrategyConfig strategyConfig) {
        strategyConfig.setInclude(codeGeneratorDto.getTableNamesArr());
        strategyConfig.setFieldPrefix(codeGeneratorDto.getFieldPrefixesArr());
        strategyConfig.setTablePrefix(codeGeneratorDto.getTablePrefixesArr());
        strategyConfig.setExclude(codeGeneratorDto.getExcludeTableNamesArr());
        strategyConfig.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        strategyConfig.setEntitySerialVersionUID(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        strategyConfig.isSkipView();
    }

    /**
     * 处理包命名
     * @date 2021/11/18 20:43
     * @author zk_yjl
     * @param autoGenerator
     * @return void
     */
    private void handlerPackageConfig(AutoGenerator autoGenerator) {
        PackageConfig packageConfig=new PackageConfig();
        FileConfigRo fileConfigRo = codeGeneratorDto.getFileConfigRo();
        //设置主包
        packageConfig.setParent(fileConfigRo.getPackageName());
        packageConfig.setController(fileConfigRo.getPackageController());
        packageConfig.setService(fileConfigRo.getPackageService());
        packageConfig.setServiceImpl(fileConfigRo.getPackageServiceImpl());
        packageConfig.setMapper(fileConfigRo.getPackageMapper());
        //放置在同一目录下
        packageConfig.setXml(fileConfigRo.getPackageMapper());
        packageConfig.setEntity(fileConfigRo.getPackageEntity());

        autoGenerator.setPackageInfo(packageConfig);
    }
    /**
     * 处理数据库配置
     * @date 2021/11/18 20:44
     * @author zk_yjl
     * @param autoGenerator
     * @return void
     */
    private void handlerDataSourceConfig(AutoGenerator autoGenerator) {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        DBConfigRo dbConfigRo = codeGeneratorDto.getDbConfigRo();
        dsc.setUrl(dbConfigRo.getUrl());
        dsc.setDriverName(dbConfigRo.getDriver());
        dsc.setUsername(dbConfigRo.getUsername());
        dsc.setPassword(dbConfigRo.getPassword());
        autoGenerator.setDataSource(dsc);
    }

    /**
      处理全局配置信息
     * @date 2021/11/18 20:23
     * @author zk_yjl
     * @param autoGenerator
     * @return void
     */
    private void handlerGlobalConfig(AutoGenerator autoGenerator) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outFilePath + "src/main/java");
        gc.setAuthor(codeGeneratorDto.getAuthor());
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        // gc.setSwagger2(true);
        //进行覆盖
        gc.setFileOverride(true);
        //设置名称
        FileConfigRo fileConfigRo = codeGeneratorDto.getFileConfigRo();

        gc.setMapperName(fileConfigRo.getFileNamePatternMapper());
        gc.setXmlName(fileConfigRo.getFileNamePatternMapperXml());
        gc.setServiceName(fileConfigRo.getFileNamePatternService());
        gc.setServiceImplName(fileConfigRo.getFileNamePatternServiceImpl());
        gc.setEntityName(fileConfigRo.getFileNamePatternEntity());
        gc.setControllerName(fileConfigRo.getFileNamePatternController());
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setIdType(IdType.ASSIGN_ID);
        autoGenerator.setGlobalConfig(gc);
    }

}
