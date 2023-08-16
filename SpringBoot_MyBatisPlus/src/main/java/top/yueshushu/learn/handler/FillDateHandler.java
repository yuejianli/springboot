package top.yueshushu.learn.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName:FillDateHandler
 * @Description 日期填充处理器
 * @Author zk_yjl
 * @Date 2021/9/1 10:29
 * @Version 1.0
 * @Since 1.0
 **/
@Component
@Log4j2
public class FillDateHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("createTime 字段插入开始日期");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        //处理 flag 逻辑删除位。
        this.strictInsertFill(metaObject, "flag", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("updateTime 字段更新修改日期");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
