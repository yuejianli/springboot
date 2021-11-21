package top.yueshushu.learn.mp.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.log4j.Log4j2;
import top.yueshushu.learn.mp.dto.CodeGeneratorDto;
import top.yueshushu.learn.mp.ro.DBConfigRo;
import top.yueshushu.learn.mp.ro.FileConfigRo;
import top.yueshushu.learn.mp.ro.ProjectConfigRo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:MybatisPlusCodeGeneratorSelfUtil
 * @Description 自定义配置文件
 * @Author zk_yjl
 * @Date 2021/11/18 20:09
 * @Version 1.0
 * @Since 1.0
 **/
@Log4j2
public class MybatisPlusCodeGeneratorSelfUtil {
    /**
      输出文件的目录
     */
    private String outFilePath;

    private CodeGeneratorDto codeGeneratorDto;


    public MybatisPlusCodeGeneratorSelfUtil(CodeGeneratorDto codeGeneratorDto, String outFilePth){
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
        log.info(">>>>开始自动生成代码,生成后的目录放置在: {}",outFilePath);
        AutoGenerator autoGenerator = new AutoGenerator();
        //处理全局配置
        handlerGlobalConfig(autoGenerator);
        log.info(">>>>处理全局配置成功");
        //处理数据源
        handlerDataSourceConfig(autoGenerator);
        log.info(">>>>处理数据源成功");
        //处理包配置
        handlerPackageConfig(autoGenerator);
        log.info(">>>>处理包配置成功");
        //自定义输出文件的配置
        handlerSelfFileConfig(autoGenerator);
        log.info(">>>>处理自定义输出文件成功");
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //注意，上面 handlerSelfFileConfig() 使用了哪些自定义的配置文件，这儿就需要进行相应的设置为 null的处理。否则，会覆盖的。
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setEntity(null);
        templateConfig.setMapper(null);
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        //处理策略
        handlerStrategyConfig(autoGenerator);
        log.info(">>>>处理策略成功");
        //生成代码
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        log.info(">>>>开始执行代码");
        autoGenerator.execute();
        log.info(">>>>执行代码,生成文件成功");
    }
    /**
     * 自定义输出配置,主要针对的是配置文件.
     * @date 2021/11/19 17:05
     * @author zk_yjl
     * @param autoGenerator
     * @return void
     */
    private void handlerSelfFileConfig(AutoGenerator autoGenerator) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        /**
           处理 controller 文件
         */
       String templatePath="/ftl/self/controller.java.ftl";
       fileOutConfigList.add(
               new FileOutConfig(templatePath) {
                   @Override
                   public String outputFile(TableInfo tableInfo) {
                       // 自定义输出文件名 + pc.getModuleName()
                       String expand = outFilePath + "src/main/java/" +packageConvert(codeGeneratorDto.getFileConfigRo()
                       .getPackageName())+"/"+codeGeneratorDto.getFileConfigRo().getPackageController();
                       return expand+"/"+tableInfo.getControllerName()+StringPool.DOT_JAVA;
                   }
               }
       );
        /**
         处理 service 文件
         */
         templatePath="/ftl/self/service.java.ftl";
        fileOutConfigList.add(
                new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名 + pc.getModuleName()
                        String expand = outFilePath + "src/main/java/" +packageConvert(codeGeneratorDto.getFileConfigRo()
                                .getPackageName())+"/"+codeGeneratorDto.getFileConfigRo().getPackageService();
                        return expand+"/"+tableInfo.getServiceName()+StringPool.DOT_JAVA;
                    }
                }
        );
        /**
         处理 serviceImpl 文件
         */
        templatePath="/ftl/self/serviceImpl.java.ftl";
        fileOutConfigList.add(
                new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名 + pc.getModuleName()
                        String expand = outFilePath + "src/main/java/" +packageConvert(codeGeneratorDto.getFileConfigRo()
                                .getPackageName())+"/"+packageConvert(codeGeneratorDto.getFileConfigRo().getPackageServiceImpl());
                        return expand+"/"+tableInfo.getServiceImplName()+StringPool.DOT_JAVA;
                    }
                }
        );

        /**
         处理 entity 文件
         */
        templatePath="/ftl/self/entity.java.ftl";
        fileOutConfigList.add(
                new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名 + pc.getModuleName()
                        String expand = outFilePath + "src/main/java/" +packageConvert(codeGeneratorDto.getFileConfigRo()
                                .getPackageName())+"/"+codeGeneratorDto.getFileConfigRo().getPackageEntity();
                        return expand+"/"+tableInfo.getEntityName()+StringPool.DOT_JAVA;
                    }
                }
        );

        /**
         处理 mapper 文件
         */
        templatePath="/ftl/self/mapper.java.ftl";
        fileOutConfigList.add(
                new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名 + pc.getModuleName()
                        String expand = outFilePath + "src/main/java/" +packageConvert(codeGeneratorDto.getFileConfigRo()
                                .getPackageName())+"/"+codeGeneratorDto.getFileConfigRo().getPackageMapper();
                        return expand+"/"+tableInfo.getMapperName()+StringPool.DOT_JAVA;
                    }
                }
        );

        /**
         处理 xml 文件
         */
        templatePath="/ftl/self/mapper.xml.ftl";
        fileOutConfigList.add(
                new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名 + pc.getModuleName()
                        String expand = outFilePath + "src/main/resource/" +packageConvert(codeGeneratorDto.getFileConfigRo()
                                .getPackageName())+"/"+codeGeneratorDto.getFileConfigRo().getPackageMapper();
                        return expand+"/"+tableInfo.getMapperName()+StringPool.DOT_XML;
                    }
                }
        );

       cfg.setFileOutConfigList(fileOutConfigList);
       autoGenerator.setCfg(cfg);
    }

    private static String packageConvert(String path){
        return path.replaceAll("\\.","/");
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
        ProjectConfigRo projectConfigRo = codeGeneratorDto.getProjectConfigRo();
        if(null!=projectConfigRo){
            if("1".equals(projectConfigRo.getBaseMapper())){
                gc.setBaseResultMap(true);
            }else{
                gc.setBaseResultMap(false);
            }
            if("1".equals(projectConfigRo.getBaseColumn())){
                gc.setBaseColumnList(true);
            }else{
                gc.setBaseColumnList(false);
            }
        }
        gc.setIdType(IdType.ASSIGN_ID);
        autoGenerator.setGlobalConfig(gc);
    }

}
