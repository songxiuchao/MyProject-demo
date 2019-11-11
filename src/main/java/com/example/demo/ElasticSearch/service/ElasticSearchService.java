package com.example.demo.ElasticSearch.service;

import com.example.demo.ElasticSearch.po.ESDocument;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-04 15:06
 **/
public interface ElasticSearchService {
    /**
     * 保存
     * @param esDocument
     */
    void save(ESDocument esDocument);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 根据id查询
     * @param id
     */
    void getById(String id);

    /**
     * 根据姓名，项目id查询
     * @param name
     * @param projectId
     */
    void getByName(String name,String projectId);
}
