package top.yueshushu.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import lombok.Data;

/**
 * 商品
 *
 * @author yuejianli
 * @date 2022-08-18
 */
@Data
@Document(indexName = "product")
public class Product {
	@Id
	private Integer id;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String title;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String author;
	@Field(type = FieldType.Keyword)
	private String category;
	@Field(type = FieldType.Double)
	private Double price;
	@Field(type = FieldType.Keyword, index = false)
	private String image;
}
