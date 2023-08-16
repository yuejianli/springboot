package top.yueshushu;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import top.yueshushu.model.User;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2022-08-11
 */
@SpringBootTest
@Slf4j
public class DocumentTest {
	
	private RestHighLevelClient restHighLevelClient;
	
	@Before
	public void initClient() {
		RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"));
		restHighLevelClient = new RestHighLevelClient(restClientBuilder);
	}
	
	/**
	 * 添加文档
	 */
	@Test
	public void createDocumentTest() throws Exception {
		//1. 创建索引请求
		IndexRequest indexRequest = new IndexRequest();
		// 进行绑定,并指定 id
		indexRequest.index("user").id("1");
		
		// 构建数据,序列化成 json
		User user = generateUser();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String userJson = objectMapper.writeValueAsString(user);
		
		// 请求中写入 source
		indexRequest.source(userJson, XContentType.JSON);
		
		IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		
		log.info("获取信息:{}", indexResponse.toString());
		log.info("获取 _index:{}", indexResponse.getIndex());
		log.info("获取 _id:{}", indexResponse.getId());
		log.info("获取结果:{}", indexResponse.getResult().toString());
	}
	
	/**
	 * 更新文档操作
	 */
	@Test
	public void updateDocumentTest() throws Exception {
		UpdateRequest updateRequest = new UpdateRequest();
		// .id 为指定文档的 id
		updateRequest.index("user").id("1");
		
		updateRequest.doc(XContentType.JSON, "name", "两个蝴蝶飞", "age", 29);
		
		// 进行更新操作
		UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
		log.info("获取信息:{}", updateResponse.toString());
		log.info("获取 _index:{}", updateResponse.getIndex());
		log.info("获取 _id:{}", updateResponse.getId());
		log.info("获取结果:{}", updateResponse.getResult().toString());
	}
	
	/**
	 * 删除文档
	 */
	@Test
	public void deleteDocumentTest() throws Exception {
		DeleteRequest deleteRequest = new DeleteRequest();
		deleteRequest.index("user").id("1");
		
		DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
		log.info(">>> 删除响应结果:{}", deleteResponse.toString());
	}
	
	/**
	 * 查询文档
	 */
	@Test
	public void getDocumentTest() throws Exception {
		
		GetRequest getRequest = new GetRequest();
		getRequest.index("user").id("1");
		
		//获取请求信息
		GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
		// 获取信息
		log.info(">>获取响应信息:{}", getResponse);
		log.info(">> 获取 _id:{}", getResponse.getId());
		log.info(">>>获取 _index:{}", getResponse.getIndex());
		log.info(">>>获取类型 :{}", getResponse.getType());
		log.info(">>>获取资源信息:{}", getResponse.getSourceAsString());
	}
	
	
	/**
	 * 批量添加操作
	 */
	@Test
	public void batchAddTest() throws Exception {
		//1. 定义 BulkRequest 用于封装
		BulkRequest bulkRequest = new BulkRequest();
		bulkRequest.add(
				new IndexRequest().index("user").id("3")
						.source(XContentType.JSON, "name", "岳建立")
		);
		bulkRequest.add(
				new IndexRequest()
						.index("user").id("4")
						.source(XContentType.JSON, "name", "泽霖")
		);
		bulkRequest.add(
				new IndexRequest()
						.index("user").id("5")
						.source(XContentType.JSON, "name", "建立")
		);
		
		// 响应信息
		BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
		
		log.info(">>> 获取响应:{}", bulkResponse);
		log.info("获取 took 花费时长:{}", bulkResponse.getTook());
		Arrays.stream(bulkResponse.getItems()).forEach(
				n -> log.info(">>>>> {}", n)
		);
	}
	
	/**
	 * 批量删除操作
	 */
	@Test
	public void batchDeleteTest() throws Exception {
		BulkRequest bulkRequest = new BulkRequest();
		bulkRequest.add(new DeleteRequest().index("user").id("3"));
		bulkRequest.add(new DeleteRequest().index("user").id("4"));
		bulkRequest.add(new DeleteRequest().index("user").id("5"));
		
		BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
		
		log.info(">>> 花费时长:{}", bulkResponse.getTook());
		
		//设置信息:
		Arrays.stream(bulkResponse.getItems()).forEach(
				n -> log.info(">>>>> {}", n.getResponse())
		);
	}
	
	private User generateUser() {
		User user = new User();
		user.setId(1);
		user.setName("岳泽霖");
		user.setNickName("岳泽霖");
		user.setAge(28);
		user.setSex("男");
		user.setDescription("一个快乐的程序员呀呀");
		return user;
	}
	
	
}
