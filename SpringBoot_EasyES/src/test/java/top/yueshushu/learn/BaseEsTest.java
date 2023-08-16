package top.yueshushu.learn;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import cn.easyes.core.conditions.LambdaEsUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yueshushu.learn.esmapper.EsUserMapper;
import top.yueshushu.learn.pojo.EsUser;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 基础的测试
 *
 * @author yuejianli
 * @date 2023-04-10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class BaseEsTest {
    @Resource
    private EsUserMapper esUserMapper;

    /**
    单个插入
     */
    @Test
    public void insertTest() {
        EsUser esUser = new EsUser();
        esUser.setId(1);
        esUser.setName("岳泽霖");
        esUser.setNickName("小泽霖");
        esUser.setAge(28);
        esUser.setSex("男");
        esUserMapper.insert(esUser);
    }
    /**
    批量插入
     */
    @Test
    public void batchInsertTest() {
        EsUser esUser = new EsUser();
        esUser.setId(2);
        esUser.setName("岳建立");
        esUser.setNickName("小建立");
        esUser.setAge(25);
        esUser.setSex("男");
        // 批量插入
        esUserMapper.insertBatch(Collections.singletonList(esUser));
    }

    @Test
    public void getByIdTest() {
        EsUser esUser = esUserMapper.selectById(1);
        log.info(">> 查询用户: {}",esUser );
    }

    @Test
    public void selectAllTest() {
        List<EsUser> esUserList = esUserMapper.selectList(new LambdaEsQueryWrapper<>());
        esUserList.forEach(
                n->{
                    log.info("用户信息: {}",n);
                }
        );
    }
    /**
    根据id 批量查询
     */
    @Test
    public void getByIdsTest() {
        List<EsUser> esUserList = esUserMapper.selectBatchIds(Arrays.asList(1,2));
        esUserList.forEach(
                n->{
                    log.info("用户信息: {}",n);
                }
        );
    }
    /**
   查询数量
     */
    @Test
    public void countTest() {

        Long count = esUserMapper.selectCount(new LambdaEsQueryWrapper<>());
        log.info(">>> 总数是: {}", count);
    }

    /**
     更新操作
     */
    @Test
    public void updateTest() {

        log.info(">>> 之前的数据是: {}" ,esUserMapper.selectById(1));

        EsUser esUser = esUserMapper.selectById(1);
        esUser.setAge(29);
        esUser.setNickName("两个蝴蝶飞");
        // 进行更新
        esUserMapper.updateById(esUser);

        log.info(">>> 修改后的数据是: {}" ,esUserMapper.selectById(1));
    }
    /**
        批量更新
     */
    @Test
    public void batchUpdateTest() {
        EsUser esUser = esUserMapper.selectById(1);
        esUser.setAge(30);
        esUser.setNickName("批量更新两个蝴蝶飞");

        esUserMapper.updateBatchByIds(Collections.singletonList(esUser));

        log.info(">>> 修改后的数据是: {}" ,esUserMapper.selectById(1));

    }
    /**
      根据条件进行更新
     */
    @Test
    public void updateByWrapperTest() throws Exception{
        LambdaEsUpdateWrapper<EsUser> esUserLambdaEsUpdateWrapper = new LambdaEsUpdateWrapper<>();
        esUserLambdaEsUpdateWrapper.le(EsUser::getAge,300);

        EsUser esUser = new EsUser();
        esUser.setNickName("根据条件更新2");
        esUser.setAge(33);
        esUserMapper.update(esUser,esUserLambdaEsUpdateWrapper);
        selectAllTest();

    }

    @Test
    public void deleteByIdTest() {
        // 根据id 进行删除
        esUserMapper.deleteById(1);
    }
    /**
     根据id 批量删除
     */
    @Test
    public void deleteBatchTest() {
        esUserMapper.deleteBatchIds(Collections.singletonList(2));
    }

    /**
    根据条件批量删除
     */
    @Test
    public void deleteByWrapperTest() throws Exception{
        LambdaEsQueryWrapper<EsUser> esUserLambdaEsQueryWrapper = new LambdaEsQueryWrapper<>();
        esUserLambdaEsQueryWrapper.le(EsUser::getAge,300);

        esUserMapper.delete(esUserLambdaEsQueryWrapper);

        TimeUnit.SECONDS.sleep(2);
        selectAllTest();

    }

}
