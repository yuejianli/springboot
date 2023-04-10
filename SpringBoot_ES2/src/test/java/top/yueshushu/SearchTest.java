package top.yueshushu;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SimpleField;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import top.yueshushu.model.Product;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文档查询
 *
 * @author yuejianli
 * @date 2022-08-18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SearchTest {
	
	@Resource
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	/**
	 * 全部查询
	 */
	@Test
	public void findAllTest() {
		NativeSearchQuery nativeSearchQuery =
				new NativeSearchQuery(QueryBuilders.matchAllQuery());
		
		SearchHits<Product> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
		showInfo(searchHits);
	}
	
	/**
	 * 一个简单的查询
	 * id =4
	 */
	@Test
	public void simpleQueryTest() {
		NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(
				QueryBuilders.termQuery("id", "4"));
		SearchHits<Product> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
		showInfo(searchHits);
	}
	
	/**
	 * 分页查询
	 * 从 0开始
	 */
	@Test
	public void pageTest() {
		NativeSearchQuery nativeSearchQuery =
				new NativeSearchQuery(QueryBuilders.matchAllQuery());
		Pageable pageable = PageRequest.of(2, 4);
		nativeSearchQuery.setPageable(pageable);
		
		// 进行查询
		SearchHits search = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
		showInfo(search);
		
	}
	
	/**
	 * 分页排序
	 */
	@Test
	public void sortTest() {
		NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(
				QueryBuilders.matchAllQuery()
		);
		Sort sort = Sort.by(Sort.Order.desc("price"));
		Pageable pageable = PageRequest.of(1, 4, sort);
		nativeSearchQuery.setPageable(pageable);
		
		SearchHits<Product> search = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
		showInfo(search);
	}
	
	/**
	 * bool 查询， 类型必须是图片， id不能为5.
	 * name 可以为
	 */
	@Test
	public void boolTest() {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(QueryBuilders.termQuery("category", "图书"));
		boolQueryBuilder.mustNot(QueryBuilders.termQuery("id", 5));
		boolQueryBuilder.should(QueryBuilders.termQuery("price", 360.0d));
		NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(boolQueryBuilder);
		SearchHits<Product> search = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
		showInfo(search);
	}

	@Test
	public void criteriaTest() {
		// 条件查询
		Criteria criteria = Criteria.where(new SimpleField("title")).contains(
				"学习"
		).or( new SimpleField("id")).greaterThan("5");
		CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
		SearchHits<Product> search = elasticsearchRestTemplate.search(criteriaQuery, Product.class);
		showInfo(search);
	}


	
	@Test
	public void rangeTest() {
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price");
		rangeQueryBuilder.gte(100.0);
		rangeQueryBuilder.lte(300.0d);
		
		NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(rangeQueryBuilder);
		
		SearchHits<Product> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
		showInfo(searchHits);
	}
	
	@Test
	public void likeTest() {
		FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("title", "学哈");
		fuzzyQueryBuilder.fuzziness(Fuzziness.ONE);
		
		NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(fuzzyQueryBuilder);
		
		SearchHits<Product> search = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
		showInfo(search);
	}
	
	/**
	 * 高亮显示
	 */
	@Test
	public void hightTest() {
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.preTags("<font color='red'>");
		highlightBuilder.postTags("</font>");
		highlightBuilder.field("title");
		
		FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("title", "学哈");
		fuzzyQueryBuilder.fuzziness(Fuzziness.ONE);
		NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(fuzzyQueryBuilder, null, null, highlightBuilder, null);
		
		SearchHits<Product> search = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
		showInfo(search);
		
	}
	
	/**
	 * 展示信息
	 */
	public void showInfo(SearchHits<Product> searchResult) {
		long totalHits = searchResult.getTotalHits();
		log.info(">>> 总数:{}", totalHits);
		List<SearchHit<Product>> searchHitList = searchResult.getSearchHits();
		searchHitList.forEach(
				n -> {
					Product product = n.getContent();
					log.info(">>> 输出信息:{}", product);
					if (!CollectionUtils.isEmpty(n.getHighlightFields())){
						log.info("高亮:{}", n.getHighlightFields());
					}
				}
		);
	}
}
