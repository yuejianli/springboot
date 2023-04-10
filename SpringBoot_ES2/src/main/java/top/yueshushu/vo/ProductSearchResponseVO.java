package top.yueshushu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import org.springframework.stereotype.Service;

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
public class ProductSearchResponseVO implements Serializable {
	private Integer id;
	private String title;
	private String author;
	private String category;
	private BigDecimal price;
	private String image;
	private String showTitle;
}
