package top.yueshushu.learn.page;

import lombok.Data;

import java.util.List;

/**
 * @ClassName:PageResponse
 * @Description 分页响应的信息
 * @Author 岳建立
 * @Date 2021/11/19 21:06
 * @Version 1.0
 **/
@Data
public class PageResponse<T> {
    private Long total;
    private List<T> list;
    public PageResponse(Long total,List<T> list){
        this.total=total;
        this.list=list;
    }
}
