package top.yueshushu;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.junit4.SpringRunner;
import top.yueshushu.model.Product;
import top.yueshushu.repository.ProductRepository;

/**
 * 文档处理
 *
 * @author yuejianli
 * @date 2022-08-18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DocumentTest {
	@Resource
	private ProductRepository productRepository;

	@Test
	public void createIndexTest() {
		log.info(">>>> 创建索引成功");
	}


	/**
	 * 新增文档操作
	 */
	@Test
	public void addTest() {
		Product product = new Product();
		product.setId(1);
		product.setTitle("两个蝴蝶飞学习 ES");
		product.setCategory("图书");
		product.setPrice(40.0d);
		product.setImage("https://www.yueshushu.top");
		productRepository.save(product);
		log.info(">>> 创建生成单个文档成功");
	}
	
	/**
	 * 根据id 查询文档
	 */
	@Test
	public void searchByIdTest() {
		Optional<Product> productOptional = productRepository.findById(1);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			log.info(">>> 查询文档信息:{}", product);
		} else {
			log.info(">>> 没有此文档");
		}
	}
	
	/**
	 * 更新文档
	 */
	@Test
	public void updateByIdTest() {
		// 更新文档
		Optional<Product> productOptional = productRepository.findById(1);
		if (!productOptional.isPresent()) {
			return;
		}
		Product product = productOptional.get();
		product.setPrice(38.0d);
		product.setCategory("Java");
		
		//进行更新
		productRepository.save(product);
		
		log.info(">>> 更新文档成功");
	}
	
	/**
	 * 根据id 删除文档
	 */
	@Test
	public void deleteByIdTest() {
		productRepository.deleteById(1);
		log.info(">>> 删除文档成功");
		searchByIdTest();
	}
	
	/**
	 * 批量添加文档
	 */
	@Test
	public void batchInsertTest() {
		List<Product> list = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			Product product = new Product();
			product.setId(i + 1);
			product.setTitle("两个蝴蝶飞学习 ES" + i);
			product.setCategory("图书");
			product.setPrice(40.0d * i);
			product.setImage("https://www.yueshushu.top");
			list.add(product);
		}
		//批量添加
		productRepository.saveAll(list);
		log.info(">>>批量添加文档成功");
	}
	
	/**
	 * 批量查询文档
	 */
	@Test
	public void findAllTest() {
		Iterable<Product> allList = productRepository.findAll();
		allList.forEach(
				n -> log.info("文档:{}", n)
		);
	}
	
	/**
	 * 根据id 集合批量查询记录
	 */
	@Test
	public void findAllByIdListTest() {
		Iterable<Product> allList = productRepository.findAllById(Arrays.asList(1, 5, 6));
		allList.forEach(
				n -> log.info("文档:{}", n)
		);
	}
	
	/**
	 * 批量更新, id一样，则更新。没有id,则添加。
	 */
	@Test
	public void batchUpdateTest() {
		Iterable<Product> allList = productRepository.findAllById(Arrays.asList(1, 5, 6));
		allList.forEach(
				n -> {
					n.setPrice(10.d);
					n.setImage("http://被修改了");
				}
		);
		List<Product> list = new ArrayList<>();
		for (int i = 20; i <= 22; i++) {
			Product product = new Product();
			product.setId(i + 1);
			product.setTitle("两个蝴蝶飞学习 Java " + i);
			product.setCategory("图书");
			product.setPrice(40.0d * i);
			product.setImage("http://url1");
			list.add(product);
		}
		ArrayList<Product> findALlList = Lists.newArrayList(allList);
		list.addAll(findALlList);
		
		//批量处理
		productRepository.saveAll(list);
		
		log.info(">>>>批量修改成功");
		findAllTest();
	}
	
	/**
	 * 批量删除
	 */
	@Test
	public void batchDeleteTest() {
		Iterable<Product> allList = productRepository.findAllById(Arrays.asList(1, 2, 3));
		ArrayList<Product> findALlList = Lists.newArrayList(allList);
		productRepository.deleteAll(findALlList);
		log.info(">>>> 批量删除成功");
		findAllTest();
	}
	
}
