package top.yueshushu;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

/**
 * 索引的测试使用
 *
 * @author yuejianli
 * @date 2022-08-11
 */
@SpringBootTest
@Slf4j
public class IndexTest {
	
	
	public static final String ES_HOST = "127.0.0.1";
	public static final int ES_PORT = 9200;
	public static final String ES_SCHEMA = "http";
	
	/**
	 * 创建连接
	 */
	@Test
	public void createClient() throws IOException {
		// 创建restClient 连接
		RestClientBuilder httpClient = RestClient.builder(new HttpHost(ES_HOST, ES_PORT, ES_SCHEMA));
		//引用
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(httpClient);
		
		// 成功
		log.info(">>>创建连接 成功,{}", restHighLevelClient);
		
		restHighLevelClient.close();
		
	}
	
	private RestHighLevelClient restHighLevelClient;
	
	@Before
	public void initClient() {
		restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(ES_HOST, ES_PORT, ES_SCHEMA)));
	}
	
	/**
	 * 创建索引信息
	 */
	@Test
	public void createIndexTest() throws Exception {
		//创建索引
		CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
		//发送请求,获取响应
		CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
		//获取响应信息
		log.info("创建索引响应数据 :{}", createIndexResponse.toString());
		boolean acknowledged = createIndexResponse.isAcknowledged();
		log.info(">>> 操作是否成功 :{}", acknowledged ? "成功" : "失败");
	}
	
	/**
	 * 查询索引
	 */
	@Test
	public void showIndexTest() throws Exception {
		GetIndexRequest getIndexRequest = new GetIndexRequest("user");
		// 进行请求
		GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
		// 获取请求响应
		log.info("获取索引别名:{}", getIndexResponse.getAliases());
		log.info("获取索引的设置:{}", getIndexResponse.getSettings());
		log.info("获取索引的映射:{}", getIndexResponse.getMappings());

		// 查询之前的 student 索引
		GetIndexRequest getIndexRequest2 = new GetIndexRequest("student");
		// 进行请求
		GetIndexResponse getIndexResponse2 = restHighLevelClient.indices().get(getIndexRequest2, RequestOptions.DEFAULT);
		// 获取请求响应
		log.info("获取索引别名:{}", getIndexResponse2.getAliases());
		log.info("获取索引的设置:{}", getIndexResponse2.getSettings());
		log.info("获取索引的映射:{}", getIndexResponse2.getMappings());
	}
	
	/**
	 * 删除索引
	 */
	@Test
	public void deleteIndexTest() throws Exception {
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("user");
		
		AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
		
		boolean acknowledged = acknowledgedResponse.isAcknowledged();
		
		log.info("删除索引 {}", acknowledged ? "成功" : "失败");
		
	}
}
