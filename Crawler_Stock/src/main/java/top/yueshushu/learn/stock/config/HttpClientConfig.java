package top.yueshushu.learn.stock.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName:HttpClientConfig
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/7 16:17
 * @Version 1.0
 **/
@Configuration
public class HttpClientConfig {
    @Bean
    public CloseableHttpClient closeableHttpClient() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(30);
        cm.setMaxTotal(100);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000 * 5)
                .setSocketTimeout(1000 * 5).build();
        return HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(cm).build();
    }

    @Bean
    public RestTemplate getRestTemplate(){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

}
