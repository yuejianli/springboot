package top.yueshushu.learn;
import cn.easyes.core.biz.EsPageInfo;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.yueshushu.learn.esmapper.EsUserMapper;
import top.yueshushu.learn.pojo.EsUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-04-11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SearchTest {
    @Resource
    private EsUserMapper esUserMapper;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void batchInsertTest() {
        EsUser esUser1 = new EsUser();
        esUser1.setId(1);
        esUser1.setName("岳泽霖");
        esUser1.setNickName("小泽霖");
        esUser1.setAge(28);
        esUser1.setSex("男");

        EsUser esUser2 = new EsUser();
        esUser2.setId(2);
        esUser2.setName("岳建立");
        esUser2.setNickName("小建立");
        esUser2.setAge(26);
        esUser2.setSex("男");

        EsUser esUser3 = new EsUser();
        esUser3.setId(3);
        esUser3.setName("张三");
        esUser3.setNickName("张三");
        esUser3.setAge(24);
        esUser3.setSex("男");


        EsUser esUser4 = new EsUser();
        esUser4.setId(4);
        esUser4.setName("李四");
        esUser4.setNickName("李四");
        esUser4.setAge(24);
        esUser4.setSex("女");

        EsUser esUser5 = new EsUser();
        esUser5.setId(5);
        esUser5.setName("王二");
        esUser5.setNickName("王二");
        esUser5.setAge(16);
        esUser5.setSex("女");

        List<EsUser> userList = new ArrayList<>();
        userList.add(esUser1);
        userList.add(esUser2);
        userList.add(esUser3);
        userList.add(esUser4);
        userList.add(esUser5);
        esUserMapper.insertBatch(userList);
    }

    @Test
    public void equalsTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
       //  String name = "岳泽霖";
        String name = "霖";
        lambdaEsQueryWrapper.eq(StringUtils.hasText(name), EsUser::getName,name);
        List<EsUser> esUserList = esUserMapper.selectList(lambdaEsQueryWrapper);
        printInfo(esUserList);
    }

    @Test
    public void andTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        String name = "霖";
        String sex = "男";
        lambdaEsQueryWrapper.eq(StringUtils.hasText(name), EsUser::getName,name);
        lambdaEsQueryWrapper.eq(StringUtils.hasText(sex), EsUser::getSex,sex);
        List<EsUser> esUserList = esUserMapper.selectList(lambdaEsQueryWrapper);
        printInfo(esUserList);
    }

    @Test
    public void orTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        String name = "霖";
        String sex = "男";
        lambdaEsQueryWrapper.eq(StringUtils.hasText(name), EsUser::getName,name);
        lambdaEsQueryWrapper.or().eq(StringUtils.hasText(sex), EsUser::getSex,sex);
        List<EsUser> esUserList = esUserMapper.selectList(lambdaEsQueryWrapper);
        printInfo(esUserList);
    }

    /**
        原先查询
     */
    @Test
    public void originTest() throws Exception{
        SearchRequest searchRequest = new SearchRequest();
        // 设置索引
        searchRequest.indices("es");

        /**
         构建条件
         */
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        // 进行请求
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits result = searchResponse.getHits();
        log.info(">>> 花费的时间:{}", searchResponse.getTook());
        log.info(">>>是否超时:{}", searchResponse.isTimedOut());
        log.info(">>>> 总的数量:{}", result.getTotalHits());
        log.info(">>>>最大的匹配分数值:{}", result.getMaxScore());
        log.info(">>>>查询结果输出开始");
        Arrays.stream(result.getHits()).forEach(
                n -> log.info(">>>获取内容:{}", n.getSourceAsString())
        );
        log.info(">>>> 查询结果输出结束");
    }
    /**
    分页查询
     */
    @Test
    public void pageTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        EsPageInfo<EsUser> esUserPageInfo = esUserMapper.pageQuery(lambdaEsQueryWrapper, 1, 2);
        log.info(">>> 总数是: {}" ,esUserPageInfo.getTotal());
        printInfo(esUserPageInfo.getList());
    }

    /**
     排序查询
     */
    @Test
    public void orderTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        // 进行排序
        lambdaEsQueryWrapper.orderByDesc(EsUser::getId);
        EsPageInfo<EsUser> esUserPageInfo = esUserMapper.pageQuery(lambdaEsQueryWrapper, 1, 5);
        log.info(">>> 总数是: {}" ,esUserPageInfo.getTotal());
        printInfo(esUserPageInfo.getList());
    }

    /**
      获取 Dsl 数据
     */
    @Test
    public void getDslTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        // 进行排序
        lambdaEsQueryWrapper.orderByDesc(EsUser::getId);
        // from size
        lambdaEsQueryWrapper.limit((1-1) * 5,5);

        String source = esUserMapper.getSource(lambdaEsQueryWrapper);

        log.info(">>> 执行语句: {}", source);
    }
    /**
    只查询字段
     */
    @Test
    public void selectTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        String name = "霖";
        String sex = "男";
        lambdaEsQueryWrapper.eq(StringUtils.hasText(name), EsUser::getName,name);
        lambdaEsQueryWrapper.eq(StringUtils.hasText(sex), EsUser::getSex,sex);

        lambdaEsQueryWrapper.select(EsUser::getId,EsUser::getName);

        List<EsUser> esUserList = esUserMapper.selectList(lambdaEsQueryWrapper);
        printInfo(esUserList);
    }

    /**
     单字段去重
     */
    @Test
    public void dictTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        lambdaEsQueryWrapper.distinct(EsUser::getAge);
        List<EsUser> esUserList = esUserMapper.selectList(lambdaEsQueryWrapper);
        printInfo(esUserList);
    }

    /**
   不查询字段
     */
    @Test
    public void notSelectTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        String name = "霖";
        String sex = "男";
        lambdaEsQueryWrapper.eq(StringUtils.hasText(name), EsUser::getName,name);
        lambdaEsQueryWrapper.eq(StringUtils.hasText(sex), EsUser::getSex,sex);

        lambdaEsQueryWrapper.notSelect(EsUser::getId,EsUser::getAge);

        List<EsUser> esUserList = esUserMapper.selectList(lambdaEsQueryWrapper);
        printInfo(esUserList);
    }


    /**
     匹配
     */
    @Test
    public void matchTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        lambdaEsQueryWrapper.match(EsUser::getName,"霖",2.0f);
        List<EsUser> esUserList = esUserMapper.selectList(lambdaEsQueryWrapper);
        printInfo(esUserList);
    }

    /**
     匹配
     */
    @Test
    public void queryTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        // lambdaEsQueryWrapper.queryStringQuery("霖",2.0f);
        lambdaEsQueryWrapper.prefixQuery(EsUser::getName,"岳",2.0f);
        List<EsUser> esUserList = esUserMapper.selectList(lambdaEsQueryWrapper);
        printInfo(esUserList);
    }

    /**
     聚合查询

     {"took":18,"timed_out":false,"_shards":{"total":1,"successful":1,"skipped":0,"failed":0},
     "aggregations":{"lterms#ageTerms":{"doc_count_error_upper_bound":0,"sum_other_doc_count":0,
     "buckets":[{"key":24,"doc_count":2},{"key":16,"doc_count":1},{"key":26,"doc_count":1},
     {"key":28,"doc_count":1}]}}}
     */
    @Test
    public void groupTest() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        lambdaEsQueryWrapper.groupBy(EsUser::getAge);
        SearchResponse searchResponse = esUserMapper.search(lambdaEsQueryWrapper);
        log.info(">>> 查询数据: {}" ,searchResponse);
    }

    @Test
    public void group2Test() {
        LambdaEsQueryWrapper<EsUser> lambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        lambdaEsQueryWrapper.groupBy(EsUser::getAge);
        lambdaEsQueryWrapper.max(EsUser::getMaxAge);
        lambdaEsQueryWrapper.min(EsUser::getMinAge);
        SearchResponse searchResponse = esUserMapper.search(lambdaEsQueryWrapper);
        log.info(">>> 查询数据: {}" ,searchResponse);
    }


    public void printInfo( List<EsUser> esUserList) {
        if (CollectionUtils.isEmpty(esUserList)){
            log.info(">>>> 未查询出用户信息");
            return ;
        }
        esUserList.forEach(
                n->{
                    log.info("用户信息: {}" ,n);
                }
        );
    }
}
