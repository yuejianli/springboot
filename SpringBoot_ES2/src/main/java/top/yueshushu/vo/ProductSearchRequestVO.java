package top.yueshushu.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2022-08-18
 */
@Data
public class ProductSearchRequestVO implements Serializable {
	private String keyword;
}
