package com.example.demo.ElasticSearch.service.Impl;

import com.example.demo.ElasticSearch.po.ESDocument;
import com.example.demo.ElasticSearch.repository.MovieRepository;
import com.example.demo.ElasticSearch.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @program: demo
 * @description: 逻辑类
 * @author: xiuchao Song
 * @create: 2019-11-04 15:07
 **/
@Service("movieService")
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void save(ESDocument esDocument) {
        ESDocument save = movieRepository.save(esDocument);
        System.out.println(save.toString());
    }

    @Override
    public void delete(String id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void getById(String id) {
        ESDocument esDocument = movieRepository.findById(id).orElse(new ESDocument());
        System.out.println(esDocument.toString());

    }

    @Override
    public void getByName(String name, String projectId) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("name",name)).withQuery(matchQuery("projectId",projectId)).build();
        List<ESDocument> esDocuments = elasticsearchTemplate.queryForList(searchQuery, ESDocument.class);
        esDocuments.forEach(e-> System.out.println(e.toString()));
    }




}
