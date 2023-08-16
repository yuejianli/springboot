package top.yueshushu.business;

import com.fasterxml.jackson.databind.util.BeanUtil;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import top.yueshushu.model.Product;
import top.yueshushu.model.ProductDO;
import top.yueshushu.repository.ProductRepository;
import top.yueshushu.service.ProductDOService;
import top.yueshushu.vo.ProductRequestVO;
import top.yueshushu.vo.ProductSearchRequestVO;
import top.yueshushu.vo.ProductSearchResponseVO;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2022-08-18
 */
@Service
@Slf4j
public class ProductApplicationImpl implements IProductApplication {
	@Resource
	private ProductDOService productDOService;
	@Resource
	private ProductRepository productRepository;
	@Resource
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	@Override
	public Map<String, Object> add(ProductRequestVO productRequestVO) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		ProductDO productDO = new ProductDO();
		BeanUtils.copyProperties(productRequestVO, productDO);
		productDO.setImage("默认地址");
		productDOService.save(productDO);
		
		// 进行保存到 es里面
		Product product = new Product();
		BeanUtils.copyProperties(productDO, product);
		product.setPrice(productDO.getPrice().doubleValue());
		productRepository.save(product);
		
		log.info(">>> 保存成功");
		stopWatch.stop();
		
		//获取查询时间
		long totalTimeMillis = stopWatch.getTotalTimeMillis();
		
		Map<String, Object> result = new HashMap<>(2, 1.0f);
		result.put("totalTimeMillis", totalTimeMillis);
		return result;
	}
	
	@Override
	public Map<String, Object> esSearch(ProductSearchRequestVO productSearchRequestVO) {
		// 进行查询，包括高亮显示。
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.preTags("<font color='red'>");
		highlightBuilder.postTags("</font>");
		highlightBuilder.field("title");
		
		
		FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("title", Optional.ofNullable(productSearchRequestVO.getKeyword()).orElse(""));
		fuzzyQueryBuilder.fuzziness(Fuzziness.ONE);
		NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(fuzzyQueryBuilder, null, null, highlightBuilder, null);
		
		SearchHits<Product> search = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
		
		//{title=[Java <font color='red'>入门</font>到放弃4]}
		
		List<ProductSearchResponseVO> result = new ArrayList<>();
		// 获取查询的结果
		search.getSearchHits().forEach(
				n -> {
					
					Product content = n.getContent();
					ProductSearchResponseVO single = new ProductSearchResponseVO();
					BeanUtils.copyProperties(content, single);
					single.setPrice(BigDecimal.valueOf(content.getPrice()));
					List<String> hightFields = n.getHighlightField("title");
					if (!CollectionUtils.isEmpty(hightFields)) {
						single.setShowTitle(hightFields.get(0));
					}
					result.add(single);
				}
		);
		stopWatch.stop();
		long totalTimeMillis = stopWatch.getTotalTimeMillis();
		Map<String, Object> resultMap = new HashMap<>(2, 1.0f);
		resultMap.put("totalTimeMillis", totalTimeMillis);
		resultMap.put("data", result);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> dbSearch(ProductSearchRequestVO productSearchRequestVO) {
		
		// 进行查询，包括高亮显示。
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		List<ProductDO> list = productDOService.lambdaQuery()
				.like(StringUtils.hasText(productSearchRequestVO.getKeyword()), ProductDO::getTitle, productSearchRequestVO.getKeyword())
				.list();
		
		List<ProductSearchResponseVO> result = list.stream().map(
				n -> {
					ProductSearchResponseVO single = new ProductSearchResponseVO();
					BeanUtils.copyProperties(n, single);
					single.setPrice(n.getPrice());
					single.setShowTitle(n.getTitle());
					return single;
				}
		).collect(Collectors.toList());
		stopWatch.stop();
		long totalTimeMillis = stopWatch.getTotalTimeMillis();
		Map<String, Object> resultMap = new HashMap<>(2, 1.0f);
		resultMap.put("totalTimeMillis", totalTimeMillis);
		resultMap.put("data", result);
		return resultMap;
	}
}
