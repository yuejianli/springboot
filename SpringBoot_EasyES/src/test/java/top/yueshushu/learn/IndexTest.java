package top.yueshushu.learn;
import cn.easyes.annotation.rely.FieldType;
import cn.easyes.core.conditions.LambdaEsIndexWrapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yueshushu.learn.esmapper.EsDepartMapper;
import top.yueshushu.learn.pojo.Depart;

import javax.annotation.Resource;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-04-11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class IndexTest {
    @Resource
    private EsDepartMapper esDepartMapper;
    /**
   创建索引
     */
    @Test
    public void createIndexTest() {
        LambdaEsIndexWrapper<Depart> departLambdaEsIndexWrapper = new LambdaEsIndexWrapper<>();
        // 设置索引名称
        departLambdaEsIndexWrapper.indexName("depart");

        // 设置分片及副本信息， 可以缺省
        departLambdaEsIndexWrapper.settings(1,1);

        // 设置别名信息, 可以缺省
        departLambdaEsIndexWrapper.createAlias("departAlias");

        // 设置映射信息，重点
        departLambdaEsIndexWrapper
                .mapping(Depart::getName, FieldType.TEXT)
                .mapping(Depart::getAddress, FieldType.KEYWORD, 2.0f)
                .mapping(Depart::getDescription, FieldType.TEXT);

        //4. 创建索引
        Boolean createFlag = esDepartMapper.createIndex(departLambdaEsIndexWrapper);
        log.info("创建索引 {}" ,createFlag);
    }
    /**
     更新索引
     */
    @Test
    public void updateIndexTest() {
        LambdaEsIndexWrapper<Depart> departLambdaEsIndexWrapper = new LambdaEsIndexWrapper<>();
        departLambdaEsIndexWrapper.indexName("depart");
        departLambdaEsIndexWrapper.mapping(Depart::getDescription, FieldType.KEYWORD);
        // 更新索引
        Boolean updateFlag = esDepartMapper.updateIndex(departLambdaEsIndexWrapper);
        log.info("更新索引 {}" ,updateFlag);
    }
    /**
    查询索引
     */
    @Test
    public void existIndexTest() {
        Boolean existFlag = esDepartMapper.existsIndex("depart");
        log.info("是否存在索引: {}", existFlag);

        GetIndexResponse departResponse = esDepartMapper.getIndex("depart");

        log.info(">>> 获取索引响应: {}" ,departResponse);
    }

    /**
      删除索引
     */
    @Test
    public void dropIndexTest() {
        Boolean deleteIndexFlag = esDepartMapper.deleteIndex("depart");
        log.info("删除索引 {}" ,deleteIndexFlag);
    }

}
