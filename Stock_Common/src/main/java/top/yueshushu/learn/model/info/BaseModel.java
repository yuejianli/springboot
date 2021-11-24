package top.yueshushu.learn.model.info;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
/**
 * 基础的信息
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id 编号
     */
    private int id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
