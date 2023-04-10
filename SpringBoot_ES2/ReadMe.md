1. 添加依赖

~~~xml
      <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
~~~~

2. 进行配置 elasticsearch:
   host: 127.0.0.1 port: 9200

3. config 配置

~~~java
@EqualsAndHashCode(callSuper = true)
   @Component
   @ConfigurationProperties("elasticsearch")
   @Data
   public class EsConfig extends AbstractElasticsearchConfiguration {
   private String host;
   private Integer port;

   @Override
   public RestHighLevelClient elasticsearchClient() {
   RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(host, port));
   return new RestHighLevelClient(restClientBuilder);
   }
   }
~~~

4. 处理Repository

~~~java

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Integer> {

}
~~~~

6 引入资源

~~~java
    @Resource
private ElasticsearchRestTemplate elasticsearchRestTemplate;
~~~

7. 操作

~~~java
	/**
	 * 新增文档操作
	 */
	@Test
	public void addTest() {
		Product product = new Product();
		product.setId(1);
		product.setTitle("Java 入门到放弃");
		product.setCategory("图书");
		product.setPrice(40.0d);
		product.setImage("http://url1");
		productRepository.save(product);
		log.info(">>> 创建生成单个文档成功");
	}
~~~

查询:

~~~java
NativeSearchQuery nativeSearchQuery=new NativeSearchQuery(
		QueryBuilders.matchAllQuery()
		);
		Sort sort=Sort.by(Sort.Order.desc("price"));
		Pageable pageable=PageRequest.of(1,4,sort);
		nativeSearchQuery.setPageable(pageable);
		
		SearchHits<Product> search=elasticsearchRestTemplate.search(nativeSearchQuery,Product.class);
		showInfo(search);
~~~