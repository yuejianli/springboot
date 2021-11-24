package top.yueshushu.learn.mp;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.yueshushu.learn.mp.enumtype.DataBaseTypeEnum;
import top.yueshushu.learn.mp.ro.CodeGeneratorRequestRo;
import top.yueshushu.learn.mp.ro.DBConfigRo;
import top.yueshushu.learn.mp.ro.DBTableRo;
import top.yueshushu.learn.mp.ro.FileConfigRo;
import top.yueshushu.learn.mp.service.CodeGeneratorService;

import java.io.File;

/**
 * @ClassName:MpCodeTest
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/18 21:00
 * @Version 1.0
 * @Since 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MpCodeTest {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Test
    public void simpleCodeTest(){
        //生成对象
        CodeGeneratorRequestRo codeGeneratorRequestRo=createRequestRo();
        //进行生成文件
        try {
            String path=codeGeneratorService.generator(codeGeneratorRequestRo);
            FileUtil.copyFile(new File(path),new File("D:/abc/"+"a.zip"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(">>>生成文件成功");


    }

    private CodeGeneratorRequestRo createRequestRo() {
        CodeGeneratorRequestRo codeGeneratorRequestRo=new CodeGeneratorRequestRo();
        codeGeneratorRequestRo.setAuthor("yjl");

        DBConfigRo dbConfigRo=new DBConfigRo();
        dbConfigRo.setDbTypeName(DataBaseTypeEnum.DB_MYSQL.getValue()+"");
        dbConfigRo.setHost("127.0.0.1");
        dbConfigRo.setPort("3306");
        dbConfigRo.setUsername("mpcode");
        dbConfigRo.setPassword("mpcode");
        dbConfigRo.setDbName("mpcode");
        codeGeneratorRequestRo.setDbConfigRo(dbConfigRo);
        //设置排除配置
        DBTableRo dbTableRo = new DBTableRo();
        dbTableRo.setTableNames("user");
        codeGeneratorRequestRo.setDbTableRo(dbTableRo);
        //设置文件配置
         FileConfigRo fileConfigRo=new FileConfigRo();
        //项目的名称
        fileConfigRo.setPackageName("top.yueshushu");
        fileConfigRo.setPackageEntity("entity");
        fileConfigRo.setPackageMapper("mapper");
        fileConfigRo.setPackageService("service");
        fileConfigRo.setPackageServiceImpl("service.impl");
        fileConfigRo.setPackageController("controller");

        fileConfigRo.setFileNamePatternService("%sService");
        codeGeneratorRequestRo.setFileConfigRo(fileConfigRo);
        return codeGeneratorRequestRo;
    }
    /**
     * TODO
     * @date 2021/11/19 9:44
     * @author zk_yjl
     * @param null
     * @return
     * 缺少自定义模板
     */
}
