package top.yueshushu.learn.page;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName:PageRo
 * @Description 分页参数
 * @Author 岳建立
 * @Date 2021/11/19 19:15
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
public class PageRo implements Serializable {
    /**
     * @param pageNum 页数
     */
    private Integer pageNum=1;
    /**
     * @param pageSize 每页显示的最大条数
     */
    private Integer pageSize=10;
    /**
     * @param startNum 起始的位置
     */
    private Integer startNum;

    public Integer getStartNum(){
        return (pageNum-1)*pageSize;
    }
}
