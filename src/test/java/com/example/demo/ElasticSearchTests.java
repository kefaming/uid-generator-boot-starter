package com.example.demo;

import com.example.demo.mapper.ProductDao;
import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@SpringBootTest
class ElasticSearchTests {
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Resource
    private ProductDao productDao;

//    @Test
    void createIndexByNameAndClass() {
        String indexName = "product";
        IndexOperations ops = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));
        if (ops.exists()) ops.delete();
        ops.create();
        ops.refresh();
        ops.putMapping(ops.createMapping(Product.class));
    }

//    @Test
    public void createIndexByClass() {
        Class clazz = Product.class;
        Annotation documentAnnotation = clazz.getDeclaredAnnotation(Document.class);
        String indexName = ((Document) documentAnnotation).indexName();
        IndexOperations ops = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));
        if (ops.exists()) ops.delete();
        IndexOperations indexOps = elasticsearchRestTemplate.indexOps(clazz);
        boolean result1 = indexOps.create(); //创建索引
        boolean result2 = indexOps.putMapping(indexOps.createMapping(clazz));
    }

//    @Test
    void deleteIndexByName() {
        String indexName = "product";
        elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName)).delete();
    }

//    @Test
    void deleteIndexByClass() {
        Class clazz = Product.class;
        elasticsearchRestTemplate.indexOps(clazz).delete();
    }

//    @Test
    void deleteById() {
        elasticsearchRestTemplate.delete("1", Product.class);
    }

//    @Test
    void add () {
        Product product = new Product("1", "可口可乐", 3.5,"可乐型汽水", 100, "饮料", "https://img14.360buyimg.com/n0/jfs/t1/190117/31/20376/80404/612cbe42E244d516e/82bca6e0a709321d.jpg");
        this.productDao.save(product);
    }

//    @Test
    void addList() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("2", "百事可乐", 3.8,"可乐型汽水", 200, "饮料", "https://img14.360buyimg.com/n0/jfs/t1/159536/5/16696/125154/6066d380Eb5e37d06/53dee5edae64df9c.jpg"));
        list.add(new Product("3", "非常可乐", 3.0,"可乐型汽水", 180, "饮料", "https://img14.360buyimg.com/n0/jfs/t1/135246/4/1930/545552/5ee18dfbE352e5676/46c1257d7eb7a80e.png"));
        list.add(new Product("4", "崂山可乐", 5.0,"可乐型汽水", 150, "饮料", "https://img14.360buyimg.com/n0/jfs/t1/117381/18/19990/116947/5f82ee10Eb70643e4/91c49e5494ccb54d.jpg"));
        list.add(new Product("5", "天府可乐", 5.5,"可乐型汽水", 240, "饮料", "https://img14.360buyimg.com/n0/jfs/t1/194253/3/20816/193733/612d83c6E0e3585bb/f77981b6c522bb75.jpg"));
        this.productDao.saveAll(list);
    }

    @Test
    void search() {
        int page = 1;
        int rows = 10;
        String query = "崂山";
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery(query, "productName", "productCategory", "productDescription"))
                .withPageable(PageRequest.of(page - 1, rows))
                .build();
        SearchHits<Product> hits = elasticsearchRestTemplate.search(searchQuery, Product.class);
        hits.forEach(s-> System.out.println(s.getContent()));
    }

}
