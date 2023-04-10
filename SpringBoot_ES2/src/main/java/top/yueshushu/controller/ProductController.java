package top.yueshushu.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.annotation.Resource;

import top.yueshushu.business.IProductApplication;
import top.yueshushu.model.ProductDO;
import top.yueshushu.vo.ProductRequestVO;
import top.yueshushu.vo.ProductSearchRequestVO;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2022-08-18
 */
@RestController
@RequestMapping("/product")
public class ProductController {
	@Resource
	private IProductApplication iProductApplication;
	
	@RequestMapping("/add")
	public Map<String, Object> add(@RequestBody ProductRequestVO productRequestVO) {
		return iProductApplication.add(productRequestVO);
	}
	
	@RequestMapping("/esSearch")
	public Map<String, Object> esSearch(@RequestBody ProductSearchRequestVO productSearchRequestVO) {
		return iProductApplication.esSearch(productSearchRequestVO);
	}
	
	@RequestMapping("/dbSearch")
	public Map<String, Object> dbSearch(@RequestBody ProductSearchRequestVO productSearchRequestVO) {
		return iProductApplication.dbSearch(productSearchRequestVO);
	}
}
