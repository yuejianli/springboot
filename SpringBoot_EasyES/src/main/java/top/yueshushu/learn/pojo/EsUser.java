package top.yueshushu.learn.pojo;

import cn.easyes.annotation.*;
import cn.easyes.annotation.rely.FieldStrategy;
import cn.easyes.annotation.rely.FieldType;
import cn.easyes.annotation.rely.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-04-10
 */
@Data
@IndexName("es")
public class EsUser implements Serializable {
    // value 默认为 _id
    @IndexId(type = IdType.CUSTOMIZE)
    private Integer id;
    @IndexField(strategy = FieldStrategy.NOT_EMPTY, fieldType = FieldType.TEXT, analyzer = "ik_max_word")
    /**
     * 需要被高亮的字段
     */
    @HighLight(mappingField = "nameHighlightContent", preTag = "<font color='red'>", postTag = "</font>")
    private String name;
    @IndexField(strategy = FieldStrategy.NOT_EMPTY,fieldType = FieldType.TEXT, analyzer = "ik_max_word")
    private String nickName;
    @IndexField(fieldType = FieldType.INTEGER)
    private Integer age;
    @IndexField(fieldType = FieldType.KEYWORD_TEXT)
    private String sex;
    @Score
    private Float score;
    // 不存在
    @IndexField(exist = false)
    private String description;

    // 不存在
    @IndexField(exist = false)
    private Integer maxAge;
    @IndexField(exist = false)
    private Integer minAge;

    @IndexField(exist = false)
    private String nameHighlightContent;
}
