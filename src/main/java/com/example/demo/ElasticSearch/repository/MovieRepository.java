package com.example.demo.ElasticSearch.repository;

import com.example.demo.ElasticSearch.po.ESDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends ElasticsearchRepository<ESDocument, String> {

}
