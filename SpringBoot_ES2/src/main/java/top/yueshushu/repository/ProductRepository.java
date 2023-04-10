package top.yueshushu.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import top.yueshushu.model.Product;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2022-08-18
 */
@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Integer> {

}
