package top.yueshushu.learn.service;

import org.springframework.stereotype.Service;
import top.yueshushu.learn.exception.BusinessException;

/**
 * @ClassName:ExceptionServiceImpl
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/22 19:48
 * @Version 1.0
 * @Since 1.0
 **/
@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Override
    public void div() {
        int a=10/0;
    }
    @Override
    public void tryDiv() {
        try{
            int a=10/0;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void throwDivExcepiton() throws Exception {
        try{
            int a=10/0;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void throwBusExcepiton() throws Exception {
        throw new BusinessException("数据库查询出现异常");
    }
}
