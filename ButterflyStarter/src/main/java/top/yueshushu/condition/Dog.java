package top.yueshushu.condition;

/**
 * @ClassName:Dog
 * @Description 狗的实现类
 * @Author zk_yjl
 * @Date 2021/10/25 20:49
 * @Version 1.0
 * @Since 1.0
 **/

public class Dog implements Animal {

    @Override
    public void voice() {
        System.out.println(">>>>汪汪汪");
    }
}
