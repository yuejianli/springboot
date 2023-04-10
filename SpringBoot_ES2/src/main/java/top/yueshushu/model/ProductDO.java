package top.yueshushu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yuejianli
 * @since 2022-08-18 17:39
 */
@Data
@TableName("product")
public class ProductDO {
	/**
	 * id编号
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	/**
	 * 书名
	 */
	@TableField
	private String title;
	
	/**
	 * 作者
	 */
	@TableField
	private String author;
	
	/**
	 * 类别
	 */
	@TableField
	private String category;
	
	/**
	 * 价格
	 */
	@TableField
	private BigDecimal price;
	
	/**
	 * 图片地址
	 */
	@TableField
	private String image;
}