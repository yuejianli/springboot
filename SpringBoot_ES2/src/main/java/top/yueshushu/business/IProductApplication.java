package top.yueshushu.business;

import java.util.Map;

import top.yueshushu.vo.ProductRequestVO;
import top.yueshushu.vo.ProductSearchRequestVO;

/**
 * TODO 用途描述
 *
 * @author Yue Jianli
 * @date 2022-08-18
 */

public interface IProductApplication {
	
	Map<String, Object> add(ProductRequestVO productRequestVO);
	
	Map<String, Object> esSearch(ProductSearchRequestVO productSearchRequestVO);
	
	Map<String, Object> dbSearch(ProductSearchRequestVO productSearchRequestVO);
}
