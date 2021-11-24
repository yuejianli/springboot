package top.yueshushu.learn.mp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.yueshushu.learn.mp.mapper.YjlMapper;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:SqlServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/21 17:14
 * @Version 1.0
 **/
@Service
public class SqlServiceImpl implements SqlService {
    @Autowired
    private YjlMapper yjlMapper;
    /**
     * 执行SQL语句
     * @param sql
     */
    @Override
    public void execSelfSql(String sql){
       try{
           //查询目前数据库里面所拥有的表
           List<String> tableNames=yjlMapper.selectTableNames(
                   "mpcode", Arrays.asList("yjltest")
           );
           //删除表
           if(!CollectionUtils.isEmpty(tableNames)){
               yjlMapper.deleteTables(tableNames);
           }
           //执行新的sql语句
           yjlMapper.createTables(sql);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
