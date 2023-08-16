package top.yueshushu.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2022-08-18
 */
@Data
public class ProductRequestVO implements Serializable {
	/**
	 * 书名
	 */
	@Field
	private String title;
	
	/**
	 * 作者
	 */
	@Field
	private String author;
	
	/**
	 * 类别
	 */
	@Field
	private String category;
	
	/**
	 * 价格
	 */
	@Field
	private BigDecimal price;
}
